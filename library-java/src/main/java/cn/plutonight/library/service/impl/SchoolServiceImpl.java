package cn.plutonight.library.service.impl;

import cn.plutonight.library.entity.School;
import cn.plutonight.library.mapper.SchoolMapper;
import cn.plutonight.library.service.ISchoolService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学校信息 服务实现类
 * </p>
 *
 * @author LPH
 * @since 2020-10-18
 */
@Service
public class SchoolServiceImpl extends ServiceImpl<SchoolMapper, School> implements ISchoolService {

    /**
     * 根据ID获取学校名称
     * @Method getSchoolNameById
     * @param schoolId
     * @Return String
     * @Author LPH
     * @Version 1.0
     */
    @Override
    public String getSchoolNameById(Long schoolId) {
        School school = this.getById(schoolId);
        return school.getName();
    }

}
