package cn.leung.ai.aiselectclient.service.impl;

import cn.leung.ai.aiselectclient.entity.Room;
import cn.leung.ai.aiselectclient.entity.UserDevice;
import cn.leung.ai.aiselectclient.entity.vo.UserDeviceVO;
import cn.leung.ai.aiselectclient.mapper.UserDeviceMapper;
import cn.leung.ai.aiselectclient.service.IRoomService;
import cn.leung.ai.aiselectclient.service.IUserDeviceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 用户设备 服务实现类
 * </p>
 *
 * @author leung
 * @since 2026-02-27
 */
@Service
@Slf4j
public class UserDeviceServiceImpl extends ServiceImpl<UserDeviceMapper, UserDevice> implements IUserDeviceService {

    @Resource
    private IRoomService roomService;

    @Override
    public List<UserDeviceVO> getUserDeviceWithRoomName(Long homeId) {

        List<Room> roomList = roomService.lambdaQuery().select(Room::getName, Room::getRoomId).eq(Room::getHomeId, homeId).list();
        List<UserDevice> userDeviceList = this.lambdaQuery().select(UserDevice::getId, UserDevice::getName,UserDevice::getRoomId).eq(UserDevice::getHomeId, homeId).list();
        return userDeviceList.stream().map(item -> {
            UserDeviceVO userDeviceVO = new UserDeviceVO();
            for (Room room : roomList) {
                if (Objects.equals(room.getRoomId(), item.getRoomId())) {
                    userDeviceVO.setLocation(room.getName());
                }
            }
            userDeviceVO.setName(item.getName());
            userDeviceVO.setDeviceId(item.getId());
            return userDeviceVO;
        }).toList();
    }
}
