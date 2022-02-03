package com.wxx.library.management.entity.vo;

import com.wxx.library.management.entity.BookCategory;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author wangxin
 * @since 2022/2/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BookCategoryVO extends BookCategory {

    private List<BookCategoryVO> children;
}
