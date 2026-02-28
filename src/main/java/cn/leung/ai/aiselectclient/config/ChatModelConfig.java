package cn.leung.ai.aiselectclient.config;


import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatModelConfig {

    @Value("${spring.ai.dashscope.api-key}")
    private String API_KEY;

    @Bean
    public ChatModel chatModel() {
        // 初始化 ChatModel
        DashScopeApi dashScopeApi = DashScopeApi.builder()
                .apiKey(API_KEY)
                .build();

        ChatModel chatModel = DashScopeChatModel.builder()
                .dashScopeApi(dashScopeApi)
                .build();

        return chatModel;
    }




}
