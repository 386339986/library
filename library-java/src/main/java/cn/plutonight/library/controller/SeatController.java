package cn.plutonight.library.controller;


import cn.plutonight.library.config.MyUserDetails;
import cn.plutonight.library.config.UserLoginToken;
import cn.plutonight.library.dto.RoomSeatDto;
import cn.plutonight.library.entity.Room;
import cn.plutonight.library.entity.Seat;
import cn.plutonight.library.service.IRoomService;
import cn.plutonight.library.service.ISeatService;
import cn.plutonight.library.service.impl.SeatServiceImpl;
import cn.plutonight.library.utils.ResponseCode;
import cn.plutonight.library.utils.ResponseGenerator;
import cn.plutonight.library.utils.ResponseMsg;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LPH
 * @since 2020-10-18
 */
@RestController
@RequestMapping("/seat")
public class SeatController {

    @Autowired
    IRoomService roomService;

    @Autowired
    ISeatService seatService;

    @ApiOperation(value = "select", notes = "用户正常选座接口")
    @PostMapping("/select/{room_id}/{row}/{col}")
    public ResponseMsg select(
            @PathVariable Long room_id,
            @PathVariable int row,
            @PathVariable int col
    ) {
        ResponseMsg responseMsg;
        //MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 检查用户是否已经选座
        Seat checkSeat = seatService.getOne(new QueryWrapper<Seat>()
                .eq("student_id", 1)

                .last("LIMIT 1")
        );

        if (checkSeat != null) {
            responseMsg = new ResponseMsg(ResponseCode.FAIL, "已选座");
            return responseMsg;
        }

        Room room = roomService.getById(room_id);
        if (room.getAvailable() == 0) {
            responseMsg = new ResponseMsg(ResponseCode.FAIL, "当前座位不可选");
        } else {
            RoomSeatDto roomSeatDto = JSON.parseObject(room.getSeats(), RoomSeatDto.class);
            List<Integer[]> seatList = roomSeatDto.getSeats();
            Integer[] seatsList = seatList.get(row);
            if (seatsList[col] == SeatServiceImpl.SEAT.AVAILABLE) {
                responseMsg = new ResponseMsg(ResponseCode.SUCCESS, "选座成功");
                seatsList[col] = SeatServiceImpl.SEAT.FULL;
                seatList.set(row, seatsList);
                roomSeatDto.setSeats(seatList);

                Seat seat = new Seat();
                seat.setStudentId((long) 1);
                seat.setSchoolId(room.getSchoolId());
                seat.setRoomId(room_id);
                seat.setRow(row);
                seat.setCol(col);
                seat.setStatus(2);

                room.setSeats(JSON.toJSONString(roomSeatDto));
                room.setAvailable(room.getAvailable() - 1);
                seatService.save(seat);
                roomService.updateById(room);
                System.out.println("所选座位为空");
            } else {
                responseMsg = new ResponseMsg(ResponseCode.FAIL, "当前座位不可选");
                System.out.println("当前座位不可选");
            }
        }

        return responseMsg;
    }

    @ApiOperation(value = "checkTime", notes = "查看学生当前座位已用时间（只统计3入座 4暂离状态）")
    @GetMapping("/check/time")
    public ResponseMsg checkTime() {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 返回座位已用时间


        int time = seatService.checkTime(userDetails.getId());

        HashMap<String, Integer> map = new HashMap<>();
        map.put("time", time);

        return ResponseGenerator.getSuccessResponse(map);

    }

}
