package cn.plutonight.library.controller;


import cn.plutonight.library.config.MyUserDetails;
import cn.plutonight.library.config.PassToken;
import cn.plutonight.library.entity.Student;
import cn.plutonight.library.service.IStudentService;
import cn.plutonight.library.utils.ResponseGenerator;
import cn.plutonight.library.utils.ResponseMsg;
import cn.plutonight.library.utils.Utils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 学生信息 前端控制器
 * </p>
 *
 * @author LPH
 * @since 2020-10-16
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    IStudentService studentService;

    @ApiOperation(value = "info", notes = "用户信息接口")
    @GetMapping("/info")
    public ResponseMsg info() {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseGenerator.getSuccessResponse(userDetails);
    }
}
