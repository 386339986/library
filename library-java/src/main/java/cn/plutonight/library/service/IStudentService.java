package cn.plutonight.library.service;

import cn.plutonight.library.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 学生信息 服务类
 * </p>
 *
 * @author LPH
 * @since 2020-10-16
 */
public interface IStudentService extends IService<Student> {

    /**
     * @Method login
     * TODO: 登陆接口
     * @param username
     * @param password
     * @Return Student
     * @Date 2020/10/16
     * @Author LPH
     * @Version  1.0
     */
    Student login(String username, String password);

}
