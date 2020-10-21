package cn.plutonight.library.controller;


import cn.plutonight.library.config.MyUserDetails;
import cn.plutonight.library.entity.School;
import cn.plutonight.library.service.ISchoolService;
import cn.plutonight.library.utils.ResponseGenerator;
import cn.plutonight.library.utils.ResponseMsg;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 学校信息 前端控制器
 * </p>
 *
 * @author LPH
 * @since 2020-10-18
 */
@RestController
@RequestMapping("/school")
public class SchoolController {

    @Autowired
    ISchoolService schoolService;

    @ApiOperation(value = "info", notes = "学校列表")
    @GetMapping("/list")
    public ResponseMsg list() {
        List<School> schoolList = schoolService.list();
        String schoolListJson = JSON.toJSONString(schoolList);
        return ResponseGenerator.getSuccessResponse(schoolListJson);
    }
}
