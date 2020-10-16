package cn.plutonight.library.controller;

import cn.plutonight.library.config.PassToken;
import cn.plutonight.library.entity.Student;
import cn.plutonight.library.service.IStudentService;
import cn.plutonight.library.utils.ResponseGenerator;
import cn.plutonight.library.utils.ResponseMsg;
import cn.plutonight.library.utils.Utils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 登录用户 前端控制器
 * </p>
 *
 * @author LPH
 * @since 2020-10-16
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IStudentService studentService;

    @ApiOperation(value = "login", notes = "用户登录接口")
    @PassToken
    @PostMapping("/login")
    public ResponseMsg login(@RequestParam String username, @RequestParam String password) {
        Student student = studentService.login(username, password);

        if (student == null) {
            return ResponseGenerator.getFailureResponse();
        } else {
            String token = Utils.getToken(student);
            return ResponseGenerator.getSuccessResponse(token);
        }
    }

    @ApiOperation(value = "info", notes = "用户信息接口")
    @PassToken
    @PostMapping("/info")
    public ResponseMsg info(@RequestParam String username, @RequestParam String password) {
        Student student = studentService.login(username, password);

        if (student == null) {
            return ResponseGenerator.getFailureResponse();
        } else {
            String token = Utils.getToken(student);
            return ResponseGenerator.getSuccessResponse(token);
        }
    }

}
