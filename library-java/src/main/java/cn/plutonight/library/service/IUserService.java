package cn.plutonight.library.service;

import cn.plutonight.library.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LPH
 * @since 2020-10-10
 */
public interface IUserService extends IService<User> {

    public User login(String username, String password);
}
