package cn.plutonight.library.service.impl;

import cn.plutonight.library.entity.Room;
import cn.plutonight.library.entity.Seat;
import cn.plutonight.library.mapper.RoomMapper;
import cn.plutonight.library.mapper.SeatMapper;
import cn.plutonight.library.service.IRoomService;
import cn.plutonight.library.service.ISeatService;
import cn.plutonight.library.utils.Utils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LPH
 * @since 2020-10-18
 */
@Service
public class SeatServiceImpl extends ServiceImpl<SeatMapper, Seat> implements ISeatService {

    @Autowired
    IRoomService roomService;

    /**
     * 根据现在的用户, 检查现在状态为在坐的的数据, 返回已经使用的时间
     * @Method checkTime
     * @param studentId
     * @Return int
     * @Exception
     * @Author LPH
     * @Version 1.0
     */
    @Override
    public int checkTime(Long studentId) {
        Seat seat = this.getOne(new QueryWrapper<Seat>()
                .eq("student_id", studentId)
                .and(x -> x.eq("status", Seat.STATUS.IN).or().eq("status", Seat.STATUS.TEMP))
        );

        if (seat == null) {
            return 0;
        }

        int time = 0;
        Timestamp current_time = Utils.getTimeStamp();
        // 计算座位使用时间
        time = Math.toIntExact((current_time.getTime() - seat.getUse_time().getTime()) / 1000);

        return time;
    }

    public interface SEAT {
        int AVAILABLE = 3;
        int FULL = 4;
    }

    /**
     * 结合各表检查座位是否真正能够坐下
     * @Method checkSeat
     * @param row
     * @param col
     * @param roomId
     * @Return int (Seat.STATUS)
     * @Exception
     * @Author LPH
     * @Version 1.0
     */
    @Override
    public int checkSeat(Long roomId, int row, int col) {
        Room room = roomService.getById(roomId);

        // 检查传入参数是否正确
        if (room == null || row < 0 || col < 0) {
            return -1;
        }

        // 检查seat表该座位是否已被占用
        Seat seat = this.getOne(new QueryWrapper<Seat>()
                .eq("room_id", roomId)
                .and(x ->
                        x.eq("status", Seat.STATUS.ORDER)
                                .eq("status", Seat.STATUS.IN)
                                .eq("status", Seat.STATUS.TEMP)
                )
        );

        // 检查room表该座位是否已被占用
        int status = roomService.checkSeatStatus(roomId, row, col);

        // 座位可用
        if (status == SEAT.AVAILABLE) {
            if (seat == null) {
                // 座位真实可用
                return Seat.STATUS.OUT;
            }
            // 数据紊乱，room表和seat表不统一 修改room表该座位为占用状态
            roomService.setSeatStatus(roomId, row, col, SEAT.FULL);
            return seat.getStatus();
        } else if (status == SEAT.FULL) {
            if (seat == null) {
                // 数据紊乱，room表和seat表不统一 修改room表该座位为空闲状态
                roomService.setSeatStatus(roomId, row, col, SEAT.AVAILABLE);
                return Seat.STATUS.OUT;
            }
            // 座位真实可用
            return Seat.STATUS.OUT;
        } else {
            return -1;
        }
    }
}
