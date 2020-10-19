package cn.plutonight.library.service.impl;

import cn.plutonight.library.entity.Room;
import cn.plutonight.library.mapper.RoomMapper;
import cn.plutonight.library.service.IRoomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 自习室 服务实现类
 * </p>
 *
 * @author LPH
 * @since 2020-10-18
 */
@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements IRoomService {

}
