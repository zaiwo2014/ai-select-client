package cn.leung.ai.aiselectclient;

import cn.leung.ai.aiselectclient.entity.vo.UserDeviceVO;
import cn.leung.ai.aiselectclient.service.ISmartChatService;
import cn.leung.ai.aiselectclient.service.IUserDeviceService;
import com.alibaba.cloud.ai.graph.exception.GraphRunnerException;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AiSelectClientApplicationTests {

    @Resource
    private IUserDeviceService userDeviceService;

    @Test
    void contextLoads() {
        List<UserDeviceVO> userDeviceWithRoomName = userDeviceService.getUserDeviceWithRoomName(1904059974249017345L);
        System.out.println(userDeviceWithRoomName);
    }

    @Resource
    private ISmartChatService smartChatService;


    @Test
    public void test() throws GraphRunnerException {
        smartChatService.chatService(1904059974249017345L,"打开123");
    }

}
