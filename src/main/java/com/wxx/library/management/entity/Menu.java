package com.wxx.library.management.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单(Menu)表实体类
 *
 * @author wangxin
 * @since 2021-12-11 21:15:23
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Menu extends BaseEntity {
    /**
     * 上级菜单ID
     */
    private String pid;

    /**
     * 菜单类型
     */
    private Integer type;

    /**
     * 菜单标题
     */
    private String title;

    /**
     * 组件名称
     */
    private String componentName;

    /**
     * 组件
     */
    private String component;

    /**
     * 菜单排序
     */
    private Integer menuSort;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 链接
     */
    private String path;

    /**
     * 权限
     */
    private String permission;

}
