package com.wxx.library.management.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author wangxin
 * @since 2021/12/13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO {

    @JsonIgnore
    private String id;

    /**
     * 上级菜单ID
     */
    @JsonIgnore
    private String pid;

    /**
     * 菜单标题
     */
    private String name;

    /**
     * 组件
     */
    private String component;

    /**
     * 链接
     */
    private String path;

    private List<MenuDTO> children;
    private MetaDTO meta;
}
