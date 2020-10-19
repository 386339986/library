package cn.plutonight.library.controller;


import cn.plutonight.library.entity.Room;
import cn.plutonight.library.entity.School;
import cn.plutonight.library.service.IRoomService;
import cn.plutonight.library.service.ISchoolService;
import cn.plutonight.library.utils.ResponseGenerator;
import cn.plutonight.library.utils.ResponseMsg;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 自习室 前端控制器
 * </p>
 *
 * @author LPH
 * @since 2020-10-18
 */
@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    ISchoolService schoolService;
    @Autowired
    IRoomService roomService;

    @ApiOperation(value = "info", notes = "学校自习室信息接口")
    @GetMapping("/list")
    public ResponseMsg list(@RequestParam Integer schoolId) {
        // School school = schoolService.getById(schoolId);
        List<Room> roomList = roomService.list(new QueryWrapper<Room>().eq("school_id", schoolId));
        String roomListJSON = JSON.toJSONString(roomList);
        return ResponseGenerator.getSuccessResponse(roomListJSON);
    }

    @ApiOperation(value = "info", notes = "学校自习室座位信息接口")
    @GetMapping("/one")
    public ResponseMsg one(@RequestParam Integer roomId) {
        // School school = schoolService.getById(schoolId);
        Room room = roomService.getById(roomId);
        String roomJSON = JSON.toJSONString(room);
        return ResponseGenerator.getSuccessResponse(roomJSON);
    }
}
