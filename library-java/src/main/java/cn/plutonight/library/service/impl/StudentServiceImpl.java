package cn.plutonight.library.service.impl;

import cn.plutonight.library.entity.Student;
import cn.plutonight.library.mapper.StudentMapper;
import cn.plutonight.library.service.IStudentService;
import cn.plutonight.library.utils.Utils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学生信息 服务实现类
 * </p>
 *
 * @author LPH
 * @since 2020-10-16
 */
@Slf4j
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

    @Override
    public Student login(String username, String password) {
        if (username.isBlank() || password.isBlank()) {
            return null;
        }

        Student student = this.getOne(new QueryWrapper<Student>().eq("number", username).last("LIMIT 1"));

        if (student == null) {
            return null;
        }

        String md5Password = Utils.StringToMD5_hex(password);
        System.out.println("md5Password: " + md5Password);

        if (md5Password.equals(student.getPassword())) {
            log.info("{} 用户登录成功", student.getName());
            return student;
        } else {
            log.info("{} 用户登录失败", student.getName());
            return null;
        }
    }
}
