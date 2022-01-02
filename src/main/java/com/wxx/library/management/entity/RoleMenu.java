package com.wxx.library.management.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色和菜单绑定关系(RoleMenu)表实体类
 *
 * @author wangxin
 * @since 2021-12-11 21:53:48
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleMenu extends BaseEntity {
    /**
     * 角色id
     */
    private String roleId;

    /**
     * 菜单id
     */
    private String menuId;

}
