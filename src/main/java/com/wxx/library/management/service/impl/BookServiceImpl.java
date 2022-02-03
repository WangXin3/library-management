package com.wxx.library.management.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxx.library.management.entity.Book;
import com.wxx.library.management.entity.vo.BookVO;
import com.wxx.library.management.mapper.BookMapper;
import com.wxx.library.management.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;



/**
 * 书(Book)表服务实现类
 *
 * @author wangxin
 * @since 2021-12-11 15:05:23
 */
@Service
@AllArgsConstructor
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    @Override
    public Page<BookVO> pageVO(Page<BookVO> page, Book book) {
        return this.getBaseMapper().pageVO(page, book.getBookName(), book.getBookCategoryId());
    }
}

