package cn.plutonight.library.service.impl;

import cn.plutonight.library.entity.User;
import cn.plutonight.library.mapper.UserMapper;
import cn.plutonight.library.service.IUserService;
import cn.plutonight.library.utils.Utils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LPH
 * @since 2020-10-10
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    @Override
    public User login(String username, String password) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return null;
        }

        User user = this.getOne(new QueryWrapper<User>().eq("student_num", username).last("LIMIT 1"));

        String md5Pwd = Utils.StringToMD5_hex(password);

        if (user == null) {
            return null;
        }

        if (md5Pwd.equals(user.getPassword())) {
            log.info("{} 用户登录成功", user.getName());
            return user;
        } else {
            log.info("{} 用户登录失败", user.getName());
            return null;
        }
    }
}
