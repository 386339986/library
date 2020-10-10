package cn.plutonight.library.controller;


import cn.plutonight.library.config.PassToken;
import cn.plutonight.library.entity.User;
import cn.plutonight.library.service.impl.UserServiceImpl;
import cn.plutonight.library.utils.ResponseCode;
import cn.plutonight.library.utils.ResponseGenerator;
import cn.plutonight.library.utils.ResponseMsg;
import cn.plutonight.library.utils.Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LPH
 * @since 2020-10-10
 */
@Api(tags = "用户接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @ApiOperation(value = "login", notes = "用户登录接口")
    @PassToken
    @PostMapping("/login")
    public ResponseMsg login(@RequestParam String username, @RequestParam String password) {
        User user = userService.login(username, password);

        if (user == null) {
            return new ResponseMsg(ResponseCode.FAIL, "登录失败，用户名或密码错误");
        } else {
            String token = Utils.getToken(user);
            return ResponseGenerator.getSuccessResponse(token);
        }

    }
}
