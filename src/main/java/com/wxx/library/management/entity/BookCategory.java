package com.wxx.library.management.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 图书分类(BookCategory)表实体类
 *
 * @author wangxin
 * @since 2021-12-11 21:13:58
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BookCategory extends BaseEntity {
    /**
     * 上级分类id
     */
    private String parentId;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 分类编码
     */
    private String categoryCode;

    /**
     * 同一层级分类排序排序
     */
    private Integer seq;

}
