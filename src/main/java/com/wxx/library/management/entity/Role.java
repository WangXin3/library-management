package com.wxx.library.management.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色(Role)表实体类
 *
 * @author wangxin
 * @since 2021-12-11 21:15:40
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity {
    /**
     * 名称
     */
    private String name;

    /**
     * 角色描述
     */
    private String description;

}
