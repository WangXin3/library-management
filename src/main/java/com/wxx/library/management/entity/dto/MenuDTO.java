package com.wxx.library.management.entity.dto;

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

    private String component;
    private String componentName;
    private String path;
    private List<MenuDTO> children;
    private MetaDTO meta;
}
