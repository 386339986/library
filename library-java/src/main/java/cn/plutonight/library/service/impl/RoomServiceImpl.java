package cn.plutonight.library.service.impl;

import cn.plutonight.library.dto.RoomSeatDto;
import cn.plutonight.library.entity.Room;
import cn.plutonight.library.entity.Seat;
import cn.plutonight.library.mapper.RoomMapper;
import cn.plutonight.library.service.IRoomService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 自习室 服务实现类
 * </p>
 *
 * @author LPH
 * @since 2020-10-18
 */
@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements IRoomService {

    /**
     * 根据输入参数返回指定座位状态
     * @Method checkSeatStatus
     * @param roomId
     * @param row
     * @param col
     * @Return int
     * @Author LPH
     * @Version 1.0
     */
    @Override
    public int checkSeatStatus(Long roomId, int row, int col) {
        Room room = this.getById(roomId);

        if (room == null) {
            return -1;
        }

        RoomSeatDto roomSeatDto = JSON.parseObject(room.getSeats(), RoomSeatDto.class);
        List<Integer[]> seatList = roomSeatDto.getSeats();

        // 查询得到的座位行数小于输入行数
        if (seatList.size() < row) {
            return -2;
        }
        Integer[] seatsList = seatList.get(row);

        // 查询得到的座位列数小于输入列数
        if (seatsList.length < col) {
            return -3;
        }

        return seatsList[col];
    }

    /**
     * 根据输入参数修改指定座位状态
     * @Method setSeatStatus
     * @param roomId
     * @param row
     * @param col
     * @param status
     * @Return int
     * @Author LPH
     * @Version 1.0
     */
    @Override
    public int setSeatStatus(Long roomId, int row, int col, Integer status) {
        if (!status.equals(SeatServiceImpl.SEAT.AVAILABLE) && !status.equals(SeatServiceImpl.SEAT.FULL)) {
            return -1;
        }

        Room room = this.getById(roomId);

        if (room == null) {
            return -1;
        }

        RoomSeatDto roomSeatDto = JSON.parseObject(room.getSeats(), RoomSeatDto.class);
        List<Integer[]> seatRowList = roomSeatDto.getSeats();

        // 查询得到的座位行数小于输入行数
        if (seatRowList.size() < row) {
            return -1;
        }
        Integer[] seatColList = seatRowList.get(row);

        // 查询得到的座位列数小于输入列数
        if (seatColList.length < col) {
            return -1;
        }

        // 输入的参数不是一个座位
        if (!seatColList[col].equals(SeatServiceImpl.SEAT.AVAILABLE) && !seatColList[col].equals(SeatServiceImpl.SEAT.FULL)) {
            return -1;
        }

        seatColList[col] = status;
        seatRowList.set(row, seatColList);
        roomSeatDto.setSeats(seatRowList);
        room.setSeats(JSON.toJSONString(roomSeatDto));
        if (status == SeatServiceImpl.SEAT.AVAILABLE) {
            room.setAvailable(room.getAvailable() + 1);
        } else if (status == SeatServiceImpl.SEAT.FULL) {
            room.setAvailable(room.getAvailable() - 1);
        }
        this.updateById(room);
        System.out.println("座位状态已更新");
        return 1;
    }




}
