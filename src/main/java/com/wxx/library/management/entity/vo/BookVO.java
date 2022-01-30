package com.wxx.library.management.entity.vo;

import com.wxx.library.management.entity.Book;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wangxin
 * @since 2022/1/30
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BookVO extends Book {

    /**
     * 分类名称
     */
    private String categoryName;
}
