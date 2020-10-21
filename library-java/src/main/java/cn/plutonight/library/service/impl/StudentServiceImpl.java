package cn.plutonight.library.service.impl;

import cn.plutonight.library.entity.Student;
import cn.plutonight.library.mapper.StudentMapper;
import cn.plutonight.library.service.IStudentService;
import cn.plutonight.library.utils.Utils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学生信息 服务实现类
 * </p>
 *
 * @author LPH
 * @since 2020-10-18
 */
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
        if (md5Password.equals(student.getPassword())) {
            return student;
        } else {
            return null;
        }
    }

    @Override
    public int addStudentViolationTime(Long studentId) {
        Student student = this.getById(studentId);

        if (student == null) {
            return -1;
        }

        student.setViolationTime(student.getViolationTime());
        // 违规超过3次拉入黑名单
        if (student.getViolationTime() >= 3) {
            student.setStatus(3);
        }

        this.updateById(student);
        return 1;
    }
}
