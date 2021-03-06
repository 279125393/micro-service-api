package com.doooly.common;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {
    @TableField(exist = false)
    private Date gmt_created;
    @TableField(exist = false)
    private Date gmt_modified;
}
