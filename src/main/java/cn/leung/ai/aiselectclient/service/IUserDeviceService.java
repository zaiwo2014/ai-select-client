package cn.leung.ai.aiselectclient.service;

import cn.leung.ai.aiselectclient.entity.UserDevice;
import cn.leung.ai.aiselectclient.entity.vo.UserDeviceVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户设备 服务类
 * </p>
 *
 * @author leung
 * @since 2026-02-27
 */
public interface IUserDeviceService extends IService<UserDevice> {

    List<UserDeviceVO> getUserDeviceWithRoomName(Long homeId);

}
