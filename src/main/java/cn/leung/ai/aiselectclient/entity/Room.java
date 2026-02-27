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
 * 房间表
 * </p>
 *
 * @author leung
 * @since 2026-02-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("room")
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "room_id", type = IdType.AUTO)
    private Long roomId;

    private Long homeId;

    private String name;

    private Integer displayOrder;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
