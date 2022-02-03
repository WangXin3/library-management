package com.wxx.library.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wxx.library.management.entity.BookCategory;
import com.wxx.library.management.entity.vo.BookCategoryVO;

import java.util.List;

/**
 * 图书分类(BookCategory)表服务接口
 *
 * @author wangxin
 * @since 2021-12-11 21:13:58
 */
public interface BookCategoryService extends IService<BookCategory> {

    List<BookCategoryVO> buildCategoryTree(String categoryName);
}

