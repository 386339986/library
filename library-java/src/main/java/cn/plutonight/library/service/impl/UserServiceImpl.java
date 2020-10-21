package cn.plutonight.library.service.impl;

import cn.plutonight.library.entity.User;
import cn.plutonight.library.mapper.UserMapper;
import cn.plutonight.library.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LPH
 * @since 2020-10-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
