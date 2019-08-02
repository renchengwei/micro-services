package com.xgg.microservices.pojo.po;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Author renchengwei
 * @Date 2019-08-03
 * @Description
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
public class UserPO implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String loginName;

    private String password;

    private String userName;

    private String mobile;

    private String passwordSalt;

    private String locked;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;


}