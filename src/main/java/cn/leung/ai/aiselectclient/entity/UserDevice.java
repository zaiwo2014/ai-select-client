package cn.leung.ai.aiselectclient.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户设备
 * </p>
 *
 * @author leung
 * @since 2026-02-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_device")
public class UserDevice implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Integer type;

    private Integer entityType;

    private String name;

    private String icon;

    private Integer iconType;

    private Long roomId;

    private Long homeId;

    private Integer roomOrder;

    private Integer homeOrder;

    private Boolean common;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
