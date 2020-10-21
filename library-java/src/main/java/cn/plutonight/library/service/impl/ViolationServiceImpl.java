package cn.plutonight.library.service.impl;

import cn.plutonight.library.entity.Seat;
import cn.plutonight.library.entity.Student;
import cn.plutonight.library.entity.Violation;
import cn.plutonight.library.mapper.ViolationMapper;
import cn.plutonight.library.service.IRoomService;
import cn.plutonight.library.service.ISeatService;
import cn.plutonight.library.service.IStudentService;
import cn.plutonight.library.service.IViolationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LPH
 * @since 2020-10-18
 */
@Service
public class ViolationServiceImpl extends ServiceImpl<ViolationMapper, Violation> implements IViolationService {

    @Autowired
    ISeatService seatService;

    @Autowired
    IRoomService roomService;

    @Autowired
    IStudentService studentService;

    /**
     * 超时未签到座位记录违规并释放座位
     * @Method findAndReleaseOverTimeSeat
     * @param roomId
     * @Return void
     * @Exception
     * @Author LPH
     * @Version 1.0
     */
    @Override
    public void findAndReleaseOverTimeSeat(Long roomId) {
        List<Seat> overTimeSeatList = seatService.findOverTimeSeat(roomId);
        for (Seat seat: overTimeSeatList) {
            // 释放座位
            seat.setStatus(Seat.STATUS.OUT);
            seatService.updateById(seat);
            roomService.setSeatStatus(seat.getRoomId(), seat.getSeatRow(), seat.getSeatCol(), SeatServiceImpl.SEAT.AVAILABLE);
            // 对应用户增加违规次数
            studentService.addStudentViolationTime(seat.getStudentId());
            Violation violation = new Violation();
            violation.setReason("超时未签到");
            violation.setSeatId(seat.getId());
            violation.setStudentId(seat.getStudentId());
            // 保存违规记录
            this.save(violation);
        }
    }
}
