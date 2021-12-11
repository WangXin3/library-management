package com.wxx.library.management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxx.library.management.entity.Book;
import com.wxx.library.management.mapper.BookMapper;
import com.wxx.library.management.service.BookService;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;



/**
 * 书(Book)表服务实现类
 *
 * @author wangxin
 * @since 2021-12-11 15:05:23
 */
@Service
@AllArgsConstructor
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

}

