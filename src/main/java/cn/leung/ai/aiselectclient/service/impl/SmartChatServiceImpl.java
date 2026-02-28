package cn.leung.ai.aiselectclient.service.impl;

import cn.leung.ai.aiselectclient.entity.vo.ChatOutputSchema;
import cn.leung.ai.aiselectclient.entity.vo.UserDeviceVO;
import cn.leung.ai.aiselectclient.service.ISmartChatService;
import cn.leung.ai.aiselectclient.service.IUserDeviceService;
import com.alibaba.cloud.ai.graph.agent.ReactAgent;
import com.alibaba.cloud.ai.graph.exception.GraphRunnerException;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.deepseek.api.ResponseFormat;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class SmartChatServiceImpl implements ISmartChatService {

    @Resource
    private ChatModel chatModel;

    @Resource
    private IUserDeviceService userDeviceService;


    @Override
    public void chatService(Long homeId,String question) throws GraphRunnerException {
        String SYSTEM_PROMPT = """
                你是一个识别用户控制设备的智能体,需要从用户输入中识别用户想要控制的设备并计算置信度
                
                【要求】
                1. 从用户输入中识别是打开单个设备还是某个区域内的所有设备, 并计算置信度
                2. 影响置信度的因素: 设备的地理位置: location字段 、设备名称等
                3. 可以输出多个设备
                4. 不输出置信度为 0 的设备
                5. 如果【参考信息】为空,则说明没有设备,返回一个空列表
                
                【参考信息】
                {context}
                
                
                【回答】
                """;

        BeanOutputConverter<ChatOutputSchema> outputConverter = new BeanOutputConverter<>(ChatOutputSchema.class);
        String format = outputConverter.getFormat();

        List<UserDeviceVO> userDeviceWithRoomName = userDeviceService.getUserDeviceWithRoomName(homeId);

        PromptTemplate promptTemplate = new PromptTemplate(SYSTEM_PROMPT);
        Map<String, Object> params = Map.of(
                "context", userDeviceWithRoomName.isEmpty() ? "没有相关设备" : JSON.toJSONString(userDeviceWithRoomName)
        );

        Prompt prompt = promptTemplate.create(params);

        log.info("prompt : {}",prompt.getContents());

        ReactAgent agent = ReactAgent.builder().name("device_selector")
                .model(chatModel)
                .systemPrompt(prompt.getContents())
                .outputType(ResponseFormat.class)
                .outputSchema(format)
                .build();

        AssistantMessage message = agent.call(question);
        System.out.println(message.getText());
    }
}
