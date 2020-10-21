package cn.plutonight.library.service;

import cn.plutonight.library.entity.School;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 学校信息 服务类
 * </p>
 *
 * @author LPH
 * @since 2020-10-18
 */
public interface ISchoolService extends IService<School> {

    /**
     * 根据ID获取学校名称
     * @Method getSchoolNameById
     * @param schoolId
     * @Return String
     * @Author LPH
     * @Version 1.0
     */
    String getSchoolNameById(Long schoolId);

}
