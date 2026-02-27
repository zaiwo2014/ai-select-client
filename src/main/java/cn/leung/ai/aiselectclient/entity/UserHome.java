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
 * 用户家庭关系
 * </p>
 *
 * @author leung
 * @since 2026-02-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_home")
public class UserHome implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long uid;

    /**
     * 家庭id
     */
    private Long homeId;

    /**
     * 用户在该家庭的角色
        1. 拥有者
        2. 管理者
        3. 普通用户
        4. 待加入
     */
    private Integer userRole;

    /**
     * 用户在该家庭的名称
     */
    private String userName;

    /**
     * 家庭列表的排序
     */
    private Integer displayOrder;

    /**
     * 是否加入
     */
    private Boolean status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
