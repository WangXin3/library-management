package com.wxx.library.management.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wxx.library.management.entity.Book;
import com.wxx.library.management.entity.vo.BookVO;
import com.wxx.library.management.entity.vo.HotBookVO;

import java.util.List;

/**
 * 书(Book)表服务接口
 *
 * @author wangxin
 * @since 2021-12-11 15:05:23
 */
public interface BookService extends IService<Book> {

    Page<BookVO> pageVO(Page<BookVO> page, Book book);

    List<HotBookVO> hotBook();
}

