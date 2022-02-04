package com.wxx.library.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxx.library.management.entity.BookCategory;
import com.wxx.library.management.entity.vo.HotCategoryVO;

import java.util.List;

/**
 * 图书分类(BookCategory)表数据库访问层
 *
 * @author wangxin
 * @since 2021-12-11 21:13:58
 */
public interface BookCategoryMapper extends BaseMapper<BookCategory> {

    List<HotCategoryVO> hotCategory();
}

