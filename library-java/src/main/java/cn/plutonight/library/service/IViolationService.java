package cn.plutonight.library.service;

import cn.plutonight.library.entity.Violation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LPH
 * @since 2020-10-18
 */
public interface IViolationService extends IService<Violation> {

    /**
     * 超时未签到座位记录违规并释放座位
     * @Method findAndReleaseOverTimeSeat
     * @param roomId
     * @Return void
     * @Exception
     * @Author LPH
     * @Version 1.0
     */
    void findAndReleaseOverTimeSeat(Long roomId);

}
