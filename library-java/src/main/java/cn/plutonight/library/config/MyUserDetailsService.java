package cn.plutonight.library.config;

import cn.plutonight.library.entity.Student;
import cn.plutonight.library.service.impl.StudentServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 自定义用户登录时调用的类
 * </p>
 *
 * @author LPH
 * @since 2020-10-16
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    StudentServiceImpl studentService;

    /**
     * 登陆验证时，通过username获取用户的所有权限信息
     * 并返回UserDetails放到spring的全局缓存SecurityContextHolder中，以供授权器使用
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        String[] params = s.split(":");
        if (params.length != 2) {
            throw new UsernameNotFoundException(s);
        }
        Long schoolId = Long.valueOf(params[0]);
        String studentNumber = params[1];
        Student student = studentService.getOne(
                new QueryWrapper<Student>()
                        .eq("number", studentNumber)
                        .eq("school_id", schoolId)
                        .last("LIMIT 1")
        );

        if (student == null) {
            throw new UsernameNotFoundException(s);
        }

        MyUserDetails userDetails = new MyUserDetails();
        userDetails.setUsername(student.getName());
        userDetails.setPassword(student.getPassword());
        userDetails.setId(student.getId());
        return userDetails;
    }
}
