package cn.plutonight.library.controller;

import cn.plutonight.library.config.PassToken;
import cn.plutonight.library.entity.Student;
import cn.plutonight.library.entity.User;
import cn.plutonight.library.service.IStudentService;
import cn.plutonight.library.utils.JwtUtil;
import cn.plutonight.library.utils.ResponseGenerator;
import cn.plutonight.library.utils.ResponseMsg;
import cn.plutonight.library.utils.ToolUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

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
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return ResponseGenerator.getFailureResponse();
        }

        String[] params = username.split(":");
        if (params.length != 2) {
            return ResponseGenerator.getFailureResponse();
        }

        Long schoolId = Long.valueOf(params[0]);
        String studentNumber = params[1];
        Student student = studentService.getOne(new QueryWrapper<Student>()
                        .eq("number", studentNumber)
                        .eq("school_id", schoolId)
                        .last("LIMIT 1"));

        if (student == null) {
            return ResponseGenerator.getFailureResponse();
        } else {
            if (ToolUtils.StringToMD5_hex(password).equals(student.getPassword())) {
                String token = JwtUtil.generateToken(student.getName(), student.getId(), User.ROLE.STUDENT);
                return ResponseGenerator.getSuccessResponse(token);
            } else {
                return ResponseGenerator.getFailureResponse();
            }
//            String token = ToolUtils.getToken(student);
//            return ResponseGenerator.getSuccessResponse(token);
        }
    }

    @ApiOperation(value = "info", notes = "用户信息接口")
    @PassToken
    @GetMapping("/info")
    public ResponseMsg info(@RequestParam String username, @RequestParam String password) {
        Student student = studentService.login(username, password);

        if (student == null) {
            return ResponseGenerator.getFailureResponse();
        } else {
            String token = ToolUtils.getToken(student);
            return ResponseGenerator.getSuccessResponse(token);
        }
    }

    @ApiOperation(value = "login", notes = "管理员登录接口")
    @PassToken
    @PostMapping("/admin/login")
    public ResponseMsg adminLogin(@RequestParam String username, @RequestParam String password) {
        Student student = studentService.login(username, password);

        if (student == null) {
            return ResponseGenerator.getFailureResponse();
        } else {
            String token = ToolUtils.getToken(student);
            return ResponseGenerator.getSuccessResponse(token);
        }
    }
}
