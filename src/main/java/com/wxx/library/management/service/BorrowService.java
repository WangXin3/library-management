package com.wxx.library.management.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wxx.library.management.entity.Borrow;
import com.wxx.library.management.entity.vo.BorrowVO;

/**
 * (Borrow)表服务接口
 *
 * @author wangxin
 * @since 2022-02-03 19:29:13
 */
public interface BorrowService extends IService<Borrow> {

    Boolean saveMy(Borrow borrow);

    IPage<BorrowVO> pageMy(Page<Borrow> page, String key);

    IPage<BorrowVO> my(Page<Borrow> page, String key);

    Boolean remand(Borrow borrow);
}

