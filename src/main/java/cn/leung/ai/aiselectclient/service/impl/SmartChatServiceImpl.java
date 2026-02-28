package cn.leung.ai.aiselectclient.service.impl;

import cn.leung.ai.aiselectclient.agent.DeviceFunction;
import cn.leung.ai.aiselectclient.entity.vo.UserDeviceVO;
import cn.leung.ai.aiselectclient.service.ISmartChatService;
import cn.leung.ai.aiselectclient.service.IUserDeviceService;
import com.alibaba.cloud.ai.graph.agent.ReactAgent;
import com.alibaba.cloud.ai.graph.exception.GraphRunnerException;
import com.alibaba.fastjson2.JSON;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SmartChatServiceImpl implements ISmartChatService {

    @Resource
    private ChatModel chatModel;

    @Resource
    private IUserDeviceService userDeviceService;

    @Resource
    private DeviceFunction deviceFunction;

    @Override
    public void chatService(Long homeId,String question) throws GraphRunnerException {

        List<UserDeviceVO> userDeviceWithRoomName = userDeviceService.getUserDeviceWithRoomName(homeId);

        String SYSTEM_PROMPT = """
            你是一个智能家居控制助手。
            
            你的职责：
            根据用户指令选择需要控制的一个或多个设备，并根据意图用户意图调用提供给你的 Function。
            
            【要求】
            1. Function 的参数必须是数组的形式
            2. 根据用户的输入计算控制设备的置信度, 影响因素为: 设备名称、房间名称
            3. 回答中输出被选择的设备
            4. 回答中包含 Function 的输出结果, 如果为 true 则执行指令成功, false 则为失败.
            
            
            【参考信息】
             %s
            """.formatted(userDeviceWithRoomName == null ? "没有相关设备" : JSON.toJSONString(userDeviceWithRoomName));


        ReactAgent agent = ReactAgent.builder().name("device_selector")
                .model(chatModel)
                .systemPrompt(SYSTEM_PROMPT)
                .methodTools(deviceFunction)
                .build();

        AssistantMessage resp = agent.call(question);
        System.out.println(resp);

    }


    @Override
    public void chatServiceWithFunctionDemo(String question) throws GraphRunnerException {
        String SYSTEM_PROMPT = """
                你是一个只能聊天机器人, 负责回答用户的问题.
                
                你的职责：
                根据用户的问题进行智能回答，并在必要时调用 function。
                
                """;

        ReactAgent agent = ReactAgent.builder().name("order_answer")
                .model(chatModel)
                .systemPrompt(SYSTEM_PROMPT)
                .methodTools(deviceFunction)
                .build();

        AssistantMessage resp = agent.call(question);
        System.out.println(resp);
    }
}
