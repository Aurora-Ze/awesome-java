package com.ze.aurora.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Aurora
 * @date 2021/11/8 18:12
 */
@Data
@NoArgsConstructor
public class User {
    /** id自增策略 */
    @TableId(type = IdType.NONE)
    private long id;

    private String email;
    private String name;
    private int age;

    /** 插入或更新时自动填充*/
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /** 版本号实现乐观锁，版本号默认值不可为null！*/
    @Version
    @TableField(fill = FieldFill.INSERT)
    private int version;

    @TableLogic
    private int deleted;

    public User(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public User(long id) {
        this.id = id;
    }
}
