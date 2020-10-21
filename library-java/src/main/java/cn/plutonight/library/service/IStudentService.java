package cn.plutonight.library.service;

import cn.plutonight.library.dto.StudentDto;
import cn.plutonight.library.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 学生信息 服务类
 * </p>
 *
 * @author LPH
 * @since 2020-10-18
 */
public interface IStudentService extends IService<Student> {

    /**
     * 学生用户登录
     * @Method login
     * @param username
     * @param password
     * @Return Student
     * @Author LPH
     * @Version 1.0
     */
    Student login(String username, String password);

    /**
     * 学生信息获取
     * @Method login
     * @param studentId
     * @Return StudentDto
     * @Author LPH
     * @Version 1.0
     */
    StudentDto getStudentInfo(Long studentId);

    /**
     * 学生违规次数增加
     * @Method addStudentViolationTime
     * @param studentId
     * @Return int
     * @Author LPH
     * @Version 1.0
     */
    int addStudentViolationTime(Long studentId);

    /**
     * 检查学生是否在黑名单中
     * @Method checkStudentViolation
     * @param studentId
     * @Return int
     * @Author LPH
     * @Version 1.0
     */
    int checkStudentViolation(Long studentId);
}
