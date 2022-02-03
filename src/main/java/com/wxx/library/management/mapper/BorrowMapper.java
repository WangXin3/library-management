package com.wxx.library.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wxx.library.management.entity.Borrow;
import com.wxx.library.management.entity.vo.BorrowVO;
import org.apache.ibatis.annotations.Param;

/**
 * (Borrow)表数据库访问层
 *
 * @author wangxin
 * @since 2022-02-03 19:29:12
 */
public interface BorrowMapper extends BaseMapper<Borrow> {

    IPage<BorrowVO> page(@Param("page") Page<Borrow> page, @Param("key") String key);

    IPage<BorrowVO> my(@Param("page") Page<Borrow> page, @Param("key") String key, @Param("userId") String userId);
}

