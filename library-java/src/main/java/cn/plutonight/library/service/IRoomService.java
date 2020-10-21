package cn.plutonight.library.service;

import cn.plutonight.library.entity.Room;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 自习室 服务类
 * </p>
 *
 * @author LPH
 * @since 2020-10-18
 */
public interface IRoomService extends IService<Room> {


    /**
     * 根据输入参数返回指定座位状态
     * @Method checkSeatStatus
     * @param roomId
     * @param row
     * @param col
     * @Return int
     * @Author LPH
     * @Version 1.0
     */
    int checkSeatStatus(Long roomId, int row, int col);

    /**
     * 根据输入参数修改指定座位状态
     * @Method setSeatStatus
     * @param roomId
     * @param row
     * @param col
     * @param status
     * @Return int
     * @Author LPH
     * @Version 1.0
     */
    int setSeatStatus(Long roomId, int row, int col, Integer status);
}
