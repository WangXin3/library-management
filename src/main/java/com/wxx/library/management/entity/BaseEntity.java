package com.wxx.library.management.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author wangxin
 * @since 2021/12/11
 */
@Data
public class BaseEntity {
    
    @TableId
    private String id;

    @TableLogic
    @TableField(select = false)
    @JsonIgnore
    private Integer deleted;

    @TableField(fill = FieldFill.INSERT, select = false)
    @JsonIgnore
    private String createBy;

    @TableField(fill = FieldFill.INSERT, select = false)
    @JsonIgnore
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.UPDATE, select = false)
    @JsonIgnore
    private String updateBy;

    @TableField(fill = FieldFill.UPDATE, select = false)
    @JsonIgnore
    private LocalDateTime updateTime;
}
