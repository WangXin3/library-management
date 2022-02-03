package com.wxx.library.management.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxx.library.management.config.LMException;
import com.wxx.library.management.constant.Num;
import com.wxx.library.management.entity.Borrow;
import com.wxx.library.management.entity.RenewBorrow;
import com.wxx.library.management.entity.vo.RenewBorrowVO;
import com.wxx.library.management.mapper.RenewBorrowMapper;
import com.wxx.library.management.service.BorrowService;
import com.wxx.library.management.service.RenewBorrowService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 续借(RenewBorrow)表服务实现类
 *
 * @author wangxin
 * @since 2022-02-03 21:17:13
 */
@Service
@AllArgsConstructor
public class RenewBorrowServiceImpl extends ServiceImpl<RenewBorrowMapper, RenewBorrow> implements RenewBorrowService {

    private final BorrowService borrowService;

    @Override
    public Boolean renew(RenewBorrow renewBorrow) {
        renewBorrow.setRenewTime(LocalDateTime.now());
        // 查询该用户是否有该订单未审核完成的续借
        Integer count = this.lambdaQuery().eq(RenewBorrow::getBorrowId, renewBorrow.getBorrowId()).eq(RenewBorrow::getStatus, Num.N.v()).count();
        if (count > 0) {
            throw new LMException("请勿重复发起续借！");
        }


        return this.save(renewBorrow);
    }

    @Override
    public IPage<RenewBorrowVO> pageMy(Page<RenewBorrowVO> page, String key) {
        return this.baseMapper.pageMy(page, key);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean approval(RenewBorrow vo) {
        // 处理续借
        RenewBorrow renewBorrow = this.getById(vo.getId());
        if (Num.Y.v().equals(vo.getStatus())) {
            // 同意续借，增加30天到期时间
            // 借阅订单id
            String borrowId = renewBorrow.getBorrowId();
            Borrow borrow = borrowService.getById(borrowId);
            // 续借成功
            borrow.setMaturityTime(borrow.getMaturityTime().plusDays(30));
            borrowService.updateById(borrow);
        }
        vo.setApprovalTime(LocalDateTime.now());
        return this.updateById(vo);
    }
}

