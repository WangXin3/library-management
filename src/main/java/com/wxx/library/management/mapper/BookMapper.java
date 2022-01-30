package com.wxx.library.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wxx.library.management.entity.Book;
import com.wxx.library.management.entity.vo.BookVO;
import org.apache.ibatis.annotations.Param;

/**
 * 书(Book)表数据库访问层
 *
 * @author wangxin
 * @since 2021-12-11 15:05:23
 */
public interface BookMapper extends BaseMapper<Book> {

    Page<BookVO> pageVO(@Param("page") Page<BookVO> page, @Param("bookName") String bookName);
}

