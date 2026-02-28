package cn.leung.ai.aiselectclient.entity.vo;


import lombok.Data;

import java.util.List;

@Data
public class ChatOutputSchema {

    List<SelectDevice> selectDeviceList;


    @Data
    public static class SelectDevice {
        private Long deviceId;

        private String name;

        private double confidence;

    }

}
