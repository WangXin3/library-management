package com.wxx.library.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wxx.library.management.entity.RenewBorrow;
import com.wxx.library.management.entity.vo.RenewBorrowVO;
import org.apache.ibatis.annotations.Param;

/**
 * 续借(RenewBorrow)表数据库访问层
 *
 * @author wangxin
 * @since 2022-02-03 21:17:13
 */
public interface RenewBorrowMapper extends BaseMapper<RenewBorrow> {

    IPage<RenewBorrowVO> pageMy(@Param("page") Page<RenewBorrowVO> page, @Param("key") String key);
}

