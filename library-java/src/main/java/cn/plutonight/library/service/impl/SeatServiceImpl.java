package cn.plutonight.library.service.impl;

import cn.plutonight.library.entity.Room;
import cn.plutonight.library.entity.Seat;
import javax.annotation.Resource;

import cn.plutonight.library.entity.Violation;
import cn.plutonight.library.mapper.SeatMapper;
import cn.plutonight.library.service.*;
import cn.plutonight.library.utils.ResponseCode;
import cn.plutonight.library.utils.ResponseMsg;
import cn.plutonight.library.utils.ToolUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

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

    @Resource
    private SeatMapper seatMapper;

    @Autowired
    IRoomService roomService;

    @Autowired
    ISeatService seatService;

    @Autowired
    ISchoolService schoolService;

    @Autowired
    IStudentService studentService;

    @Autowired
    IViolationService violationService;

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
        Timestamp current_time = ToolUtils.getTimeStamp();
        // 计算座位使用时间
        time = Math.toIntExact((current_time.getTime() - seat.getUseTime().getTime()) / 1000);

        return time;
    }

    public interface SEAT {
        Integer AVAILABLE = 3;
        Integer FULL = 4;
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

    /**
     * 查看指定学生ID当前是否有座
     * @Method studentSeat
     * @param studentId
     * @Return int (Seat.STATUS)
     * @Exception
     * @Author LPH
     * @Version 1.0
     */
    @Override
    public Seat studentSeat(Long studentId) {
        Seat checkSeat = this.getOne(new QueryWrapper<Seat>()
                .eq("student_id", studentId)
                .and(x ->
                        x.eq("status", Seat.STATUS.ORDER)
                                .or().eq("status", Seat.STATUS.IN)
                                .or().eq("status", Seat.STATUS.TEMP))
                .last("LIMIT 1")
        );

        return checkSeat;
    }

    /**
     * 查找所有预约座位中超时的
     * @Method findOverTimeSeat
     * @param roomId
     * @Return List
     * @Exception
     * @Author LPH
     * @Version 1.0
     */
    @Override
    public List<Seat> findOverTimeSeat(Long roomId) {

        List<Seat> seatList = seatMapper.selectList(new QueryWrapper<Seat>()
                .apply("room_id = "+ roomId +" AND status = 2 AND ((CURRENT_TIMESTAMP - `create_time`) / (60) > `rest_time`)")
        );

        return seatList;
    }

    /**
     * 检查指定座位是否超时
     * @Method checkSeatOverTime
     * @param seatId
     * @Return int
     * @Exception
     * @Author LPH
     * @Version 1.0
     */
    @Override
    public int checkSeatOverTime(Long seatId) {
        Seat seat = this.getById(seatId);

        if (seat == null) {
            return -1;
        }

        if (seat.getStatus().equals(Seat.STATUS.IN)) {
            // 当前已入座
            return 1;
        } else if (seat.getStatus().equals(Seat.STATUS.ORDER)) {
            // 处理预约座位
            if ((ToolUtils.getLongTimeStamp() - seat.getCreateTime().getTime()) / (1000 * 60) > seat.getRestTime()) {
                // 如果该座位已超时，记录违规并释放
                seat.setStatus(Seat.STATUS.OUT);
                seatService.updateById(seat);
                roomService.setSeatStatus(seat.getRoomId(), seat.getSeatRow(), seat.getSeatCol(), SeatServiceImpl.SEAT.AVAILABLE);
                // 对应用户增加违规次数
                studentService.addStudentViolationTime(seat.getStudentId());
                Violation violation = new Violation();
                violation.setReason("预约超时未签到");
                violation.setSeatId(seat.getId());
                violation.setStudentId(seat.getStudentId());
                // 保存违规记录
                violationService.save(violation);
                return 2;
            } else {
                return 1;
            }
        } else if (seat.getStatus().equals(Seat.STATUS.TEMP)) {
            // 处理暂离座位
            if ((ToolUtils.getLongTimeStamp() - seat.getTempTime().getTime()) / (1000 * 60) > ToolUtils.TEMP_TIME) {
                // 座位离开时间过长，记录违规并释放
                seat.setStatus(Seat.STATUS.OUT);
                seat.setEndTime(ToolUtils.getTimeStamp());
                seatService.updateById(seat);
                roomService.setSeatStatus(seat.getRoomId(), seat.getSeatRow(), seat.getSeatCol(), SeatServiceImpl.SEAT.AVAILABLE);
                // 对应用户增加违规次数
                studentService.addStudentViolationTime(seat.getStudentId());
                Violation violation = new Violation();
                violation.setReason("暂离超时未入座");
                violation.setSeatId(seat.getId());
                violation.setStudentId(seat.getStudentId());
                // 保存违规记录
                violationService.save(violation);
                return 3;
            } else {
                return 1;
            }
        } else {
            return -1;
        }
    }


}
