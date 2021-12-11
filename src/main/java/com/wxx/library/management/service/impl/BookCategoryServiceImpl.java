package com.wxx.library.management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxx.library.management.mapper.BookCategoryMapper;
import com.wxx.library.management.entity.BookCategory;
import com.wxx.library.management.service.BookCategoryService;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

/**
 * 图书分类(BookCategory)表服务实现类
 *
 * @author wangxin
 * @since 2021-12-11 21:13:58
 */
@Service
@AllArgsConstructor
public class BookCategoryServiceImpl extends ServiceImpl<BookCategoryMapper, BookCategory> implements BookCategoryService {

}

