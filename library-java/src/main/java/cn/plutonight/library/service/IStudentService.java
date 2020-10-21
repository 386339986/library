package cn.plutonight.library.service;

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
     * @Method login
     * TODO: 验证用户名和密码
     *
     * @Date 2020/10/16
     * @Author LPH
     */
    Student login(String username, String password);

    int addStudentViolationTime(Long studentId);
}
