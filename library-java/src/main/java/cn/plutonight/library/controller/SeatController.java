package cn.plutonight.library.controller;


import cn.plutonight.library.config.MyUserDetails;
import cn.plutonight.library.dto.RecordDto;
import cn.plutonight.library.dto.RoomSeatDto;
import cn.plutonight.library.entity.Room;
import cn.plutonight.library.entity.Seat;
import cn.plutonight.library.service.*;
import cn.plutonight.library.service.impl.SeatServiceImpl;
import cn.plutonight.library.utils.ResponseCode;
import cn.plutonight.library.utils.ResponseGenerator;
import cn.plutonight.library.utils.ResponseMsg;
import cn.plutonight.library.utils.ToolUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @Autowired
    ISchoolService schoolService;

    @Autowired
    IStudentService studentService;

    @Autowired
    IViolationService violationService;

    @ApiOperation(value = "select", notes = "用户正常选座接口")
    @PostMapping("/select/{room_id}/{row}/{col}")
    public ResponseMsg select(
            @PathVariable Long room_id,
            @PathVariable int row,
            @PathVariable int col
    ) {
        ResponseMsg responseMsg;
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 检查用户是否已经选座
        Seat checkSeat = seatService.studentSeat(userDetails.getId());

        if (checkSeat != null) {
            responseMsg = new ResponseMsg(ResponseCode.FAIL, "选座失败：您当前已有座");
            return responseMsg;
        }

        Room room = roomService.getById(room_id);
        if (room.getAvailable() == 0) {
            responseMsg = new ResponseMsg(ResponseCode.FAIL, "选座失败：当前座位不可选");
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
                seat.setStudentId(userDetails.getId());
                seat.setSchoolId(room.getSchoolId());
                seat.setRoomId(room_id);
                seat.setSeatRow(row);
                seat.setSeatCol(col);
                seat.setStatus(2);
                seat.setName(room.getCampus() + room.getName() + "室" + roomService.getSeatName(room, row, col) + "号");

                room.setSeats(JSON.toJSONString(roomSeatDto));
                room.setAvailable(room.getAvailable() - 1);
                seatService.save(seat);
                roomService.updateById(room);
                // System.out.println("所选座位为空");
            } else {
                responseMsg = new ResponseMsg(ResponseCode.FAIL, "当前座位不可选");
                // System.out.println("当前座位不可选");
            }
        }

        return responseMsg;
    }

    @ApiOperation(value = "checkTime", notes = "查看学生当前座位已用时间（只统计3入座 4暂离状态）")
    @GetMapping("/check/time")
    public ResponseMsg checkTime() {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 查找当前学生是否有座
        Seat seat = seatService.studentSeat(userDetails.getId());

        if (seat == null) {
            return new ResponseMsg(ResponseCode.FAIL, "您当前没有座位");
        }

        // 检查座位是否有超时情况
        if (seatService.checkSeatOverTime(seat.getId()) != 1) {
            return new ResponseMsg(ResponseCode.FAIL, "您当前没有座位");
        }

        // 返回座位已用时间
        int time = seatService.checkTime(userDetails.getId());

        HashMap<String, Integer> map = new HashMap<>();
        map.put("time", time);

        return ResponseGenerator.getSuccessResponse(map);

    }

    @ApiOperation(value = "mySeat", notes = "查找我的座位信息")
    @GetMapping("/my/seat")
    public ResponseMsg mySeat() {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 查找当前学生是否有座
        Seat seat = seatService.studentSeat(userDetails.getId());

        if (seat != null) {
            // 检查座位是否有超时情况
            if (seatService.checkSeatOverTime(seat.getId()) != 1) {
                return ResponseGenerator.getFailureResponse();
            }

            JSONObject resultJSON = new JSONObject();
            Room room = roomService.getById(seat.getRoomId());
            RoomSeatDto roomSeatDto = JSON.parseObject(room.getSeats(), RoomSeatDto.class);

            String seatNumber = roomSeatDto.getSeatNumber();
            JSONObject json = JSONObject.parseObject(seatNumber);
            JSONObject rowJSON = (JSONObject) json.get("r" + seat.getSeatRow());
            String seatName = rowJSON.getString("c" + seat.getSeatCol());
            resultJSON.put("campus", room.getCampus());
            resultJSON.put("roomName", room.getName());
            resultJSON.put("seatName", seatName);
            resultJSON.put("status", seat.getStatus());

            return ResponseGenerator.getSuccessResponse(resultJSON.toJSONString());
        } else {
            return ResponseGenerator.getFailureResponse();
        }
    }

    @ApiOperation(value = "down", notes = "入座")
    @PostMapping("/down")
    public ResponseMsg down() {
        ResponseMsg responseMsg;
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 查找当前学生可入座座位
        Seat seat = seatService.studentSeat(userDetails.getId());

        // 学生无可入座座位
        if (seat == null) {
            responseMsg = new ResponseMsg(ResponseCode.FAIL, "您没有可用座位");
        } else {
            // 检查座位是否有超时情况
            if (seatService.checkSeatOverTime(seat.getId()) != 1) {
                return ResponseGenerator.getFailureResponse();
            }

            if (seat.getStatus().equals(Seat.STATUS.IN)) {
                // 当前已入座
                responseMsg = new ResponseMsg(ResponseCode.SUCCESS, "当前已入座");
            } else if (seat.getStatus().equals(Seat.STATUS.ORDER)) {
                // 处理预约座位
                seat.setStatus(Seat.STATUS.IN);
                seat.setUseTime(ToolUtils.getTimeStamp());
                seatService.updateById(seat);
                responseMsg = new ResponseMsg(ResponseCode.SUCCESS, "成功入座");
            } else if (seat.getStatus().equals(Seat.STATUS.TEMP)) {
                // 处理暂离座位
                seat.setStatus(Seat.STATUS.IN);
                seatService.updateById(seat);
                responseMsg = new ResponseMsg(ResponseCode.SUCCESS, "成功入座");
            } else {
                responseMsg = new ResponseMsg(ResponseCode.FAIL, "发生未知错误，入座失败");
            }
        }
        return responseMsg;
    }

    @ApiOperation(value = "leave", notes = "退座")
    @PostMapping("/leave")
    public ResponseMsg leave() {
        ResponseMsg responseMsg;
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 查找当前学生的座位
        Seat seat = seatService.studentSeat(userDetails.getId());

        // 学生无座位可退
        if (seat == null) {
            responseMsg = new ResponseMsg(ResponseCode.FAIL, "当前没有预约或入座，操作失败");
        } else {
            // 检查座位是否有超时情况
            if (seatService.checkSeatOverTime(seat.getId()) != 1) {
                return ResponseGenerator.getFailureResponse();
            }

            if (seat.getStatus().equals(Seat.STATUS.IN)) {
                // 正常入座座位
                seat.setStatus(Seat.STATUS.OUT);
                seat.setEndTime(ToolUtils.getTimeStamp());
                seatService.updateById(seat);
                roomService.setSeatStatus(seat.getRoomId(), seat.getSeatRow(), seat.getSeatCol(), SeatServiceImpl.SEAT.AVAILABLE);
                responseMsg = new ResponseMsg(ResponseCode.SUCCESS, "操作成功");
            } else if (seat.getStatus().equals(Seat.STATUS.ORDER)) {
                // 处理预约座位
                seat.setStatus(Seat.STATUS.OUT);
                seatService.removeById(seat.getId());
                roomService.setSeatStatus(seat.getRoomId(), seat.getSeatRow(), seat.getSeatCol(), SeatServiceImpl.SEAT.AVAILABLE);
                responseMsg = new ResponseMsg(ResponseCode.SUCCESS, "操作成功");
            } else if (seat.getStatus().equals(Seat.STATUS.TEMP)) {
                // 处理暂离座位
                seat.setStatus(Seat.STATUS.OUT);
                seat.setEndTime(ToolUtils.getTimeStamp());
                seatService.updateById(seat);
                roomService.setSeatStatus(seat.getRoomId(), seat.getSeatRow(), seat.getSeatCol(), SeatServiceImpl.SEAT.AVAILABLE);
                responseMsg = new ResponseMsg(ResponseCode.SUCCESS, "成功入座");
            } else {
                responseMsg = new ResponseMsg(ResponseCode.FAIL, "发生未知错误，操作失败");
            }
        }

        return responseMsg;
    }

    @ApiOperation(value = "leave", notes = "暂离")
    @PostMapping("/temp/leave")
    public ResponseMsg tempLeave() {
        ResponseMsg responseMsg;
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 查找当前学生的座位
        Seat seat = seatService.studentSeat(userDetails.getId());

        // 学生无座位可暂离
        if (seat == null) {
            responseMsg = new ResponseMsg(ResponseCode.FAIL, "当前无座，操作失败");
        } else {
            // 检查座位是否有超时情况
            if (seatService.checkSeatOverTime(seat.getId()) != 1) {
                return ResponseGenerator.getFailureResponse();
            }

            if (seat.getStatus().equals(Seat.STATUS.IN)) {
                seat.setStatus(Seat.STATUS.TEMP);
                seat.setTempTime(ToolUtils.getTimeStamp());
                seatService.updateById(seat);
                responseMsg = new ResponseMsg(ResponseCode.SUCCESS, "操作成功");
            }
            else {
                responseMsg = new ResponseMsg(ResponseCode.FAIL, "当前未入座，操作失败");
            }
        }
        return responseMsg;
    }

    @ApiOperation(value = "record", notes = "座位使用记录")
    @GetMapping("/record")
    public ResponseMsg record() {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Seat> seatList = seatService.list(new QueryWrapper<Seat>()
                .eq("student_id", userDetails.getId())
                .eq("status", Seat.STATUS.OUT)
        );

        List<RecordDto> recordDtoList = new ArrayList<>();

        for (Seat seat: seatList) {
            RecordDto newRecord = new RecordDto();
            newRecord.setName(seat.getName());
            Integer time = Math.toIntExact((seat.getEndTime().getTime() - seat.getUseTime().getTime()) / (1000 * 60));
            newRecord.setTime(time);
            String date = ToolUtils.timestampToDataString(seat.getUseTime().getTime());
            newRecord.setDate(date);
            recordDtoList.add(newRecord);
        }

        String result = JSON.toJSONString(recordDtoList);

        return ResponseGenerator.getSuccessResponse(result);
    }
}
