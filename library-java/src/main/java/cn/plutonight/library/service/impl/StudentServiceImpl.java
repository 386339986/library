package cn.plutonight.library.service.impl;

import cn.plutonight.library.entity.Student;
import cn.plutonight.library.mapper.StudentMapper;
import cn.plutonight.library.service.IStudentService;
import cn.plutonight.library.utils.ToolUtils;
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

    /**
     * 学生用户登录
     * @Method login
     * @param username
     * @param password
     * @Return Student
     * @Author LPH
     * @Version 1.0
     */
    @Override
    public Student login(String username, String password) {
        if (username.isBlank() || password.isBlank()) {
            return null;
        }

        Student student = this.getOne(new QueryWrapper<Student>().eq("number", username).last("LIMIT 1"));

        if (student == null) {
            return null;
        }

        String md5Password = ToolUtils.StringToMD5_hex(password);
        if (md5Password.equals(student.getPassword())) {
            return student;
        } else {
            return null;
        }
    }

    /**
     * 学生违规次数增加
     * @Method addStudentViolationTime
     * @param studentId
     * @Return int
     * @Author LPH
     * @Version 1.0
     */
    @Override
    public int addStudentViolationTime(Long studentId) {
        Student student = this.getById(studentId);

        if (student == null) {
            return -1;
        }

        student.setViolationTime(student.getViolationTime() + 1);
        // 违规超过3次拉入黑名单
        if (student.getViolationTime() >= 3) {
            student.setStatus(Student.STATUS.BLACKLIST);
            student.setBlacklistTime(ToolUtils.getTimeStamp());
        }
        this.updateById(student);
        return 1;
    }

    /**
     * 检查学生是否在黑名单中
     * @Method checkStudentViolation
     * @param studentId
     * @Return int
     * @Author LPH
     * @Version 1.0
     */
    @Override
    public int checkStudentViolation(Long studentId) {
        Student student = this.getById(studentId);

        if (student == null) {
            return -1;
        }

        // 用户在黑名单中
        if (student.getStatus().equals(Student.STATUS.BLACKLIST)) {
            // 如果用户在黑名单的时间大于7天，则解除
            if ((ToolUtils.getLongTimeStamp() - student.getBlacklistTime().getTime()) / (1000 * 60 * 60 * 24) > 7) {
                student.setStatus(Student.STATUS.NORMAL);
                student.setViolationTime(0);
                this.updateById(student);
                return Student.STATUS.NORMAL;
            } else {
                return Student.STATUS.BLACKLIST;
            }
        } else {
            // 用户不在黑名单中，检查违规次数是否超过3
            if (student.getViolationTime() >= 3) {
                // 违规超过3次拉入黑名单
                student.setStatus(Student.STATUS.BLACKLIST);
                student.setBlacklistTime(ToolUtils.getTimeStamp());
                this.updateById(student);
                return Student.STATUS.BLACKLIST;
            } else {
                return Student.STATUS.NORMAL;
            }
        }
    }
}
