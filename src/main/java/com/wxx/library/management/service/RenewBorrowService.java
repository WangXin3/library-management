package com.wxx.library.management.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wxx.library.management.entity.RenewBorrow;
import com.wxx.library.management.entity.vo.RenewBorrowVO;

/**
 * 续借(RenewBorrow)表服务接口
 *
 * @author wangxin
 * @since 2022-02-03 21:17:13
 */
public interface RenewBorrowService extends IService<RenewBorrow> {

    Boolean renew(RenewBorrow renewBorrow);

    IPage<RenewBorrowVO> pageMy(Page<RenewBorrowVO> page, String vo);

    Boolean approval(RenewBorrow renewBorrow);
}

