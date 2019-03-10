package com.doooly.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.doooly.common.BaseEntity;
import lombok.Data;

@Data
@TableName("user")
public class UserEntity extends BaseEntity {
    private Long id;
    private String name;
    private Integer age;
    private String email;

}
