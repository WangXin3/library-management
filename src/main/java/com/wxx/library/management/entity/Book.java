package com.wxx.library.management.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 书(Book)表实体类
 *
 * @author wangxin
 * @since 2021-12-11 15:26:57
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Book extends BaseEntity {
    /**
     * 分类id
     */
    private Long bookCategoryId;

    /**
     * 书名
     */
    private String bookName;

    /**
     * 作者
     */
    private String author;

    /**
     * 出版年份
     */
    private String publicationYear;

    /**
     * isbn
     */
    private String isbn;

    /**
     * 价格
     */
    private Double price;

    /**
     * 页数
     */
    private Integer pages;

    /**
     * 图片
     */
    private String image;

}
