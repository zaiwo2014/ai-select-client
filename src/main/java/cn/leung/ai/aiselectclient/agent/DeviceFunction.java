package cn.leung.ai.aiselectclient.agent;


import cn.leung.ai.aiselectclient.entity.Room;
import cn.leung.ai.aiselectclient.entity.vo.ChatOutputSchema;
import cn.leung.ai.aiselectclient.service.IRoomService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class DeviceFunction {


    @Tool(name = "turnOnDevice", description = "执行开启一个或多个设备", returnDirect = true)
    public boolean turnOnDevice(@ToolParam List<ChatOutputSchema> devicesList) {
        log.info("设备列表: {}", devicesList);
        return false;
    }


    @Tool(name = "special_add", description = "一个特殊加法器", returnDirect = true)
    public Long specialAdd(@ToolParam Integer p1, @ToolParam Integer p2) {
        return p1 + p2 + System.currentTimeMillis();
    }
}
