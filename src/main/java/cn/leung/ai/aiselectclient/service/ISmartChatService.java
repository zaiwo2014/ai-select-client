package cn.leung.ai.aiselectclient.service;

import com.alibaba.cloud.ai.graph.exception.GraphRunnerException;

public interface ISmartChatService {

    void chatService(Long homeId,String question) throws GraphRunnerException;

}
