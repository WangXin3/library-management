package com.wxx.library.management.entity.vo;

import lombok.Data;

/**
 * @author wangxin
 * @since 2022/2/4
 */
@Data
public class HotCategoryVO {
    /**
     * 该分类下的图书被借阅次数
     */
    private String value;
    /**
     * 分类名
     */
    private String name;
}
