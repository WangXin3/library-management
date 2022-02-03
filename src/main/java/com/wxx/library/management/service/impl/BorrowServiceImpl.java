package com.wxx.library.management.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxx.library.management.config.LMException;
import com.wxx.library.management.constant.Num;
import com.wxx.library.management.entity.BaseEntity;
import com.wxx.library.management.entity.Book;
import com.wxx.library.management.entity.Borrow;
import com.wxx.library.management.entity.vo.BorrowVO;
import com.wxx.library.management.mapper.BorrowMapper;
import com.wxx.library.management.service.BookService;
import com.wxx.library.management.service.BorrowService;
import com.wxx.library.management.util.SecurityUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * (Borrow)表服务实现类
 *
 * @author wangxin
 * @since 2022-02-03 19:29:13
 */
@Service
@AllArgsConstructor
public class BorrowServiceImpl extends ServiceImpl<BorrowMapper, Borrow> implements BorrowService {

    private final BookService bookService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveMy(Borrow borrow) {
        // 先查询该用户是否已经借阅过该书
        Integer count = this.lambdaQuery().eq(Borrow::getUserId, SecurityUtil.getUserId()).eq(Borrow::getBookId, borrow.getBookId()).count();
        if (count > 0) {
            throw new LMException("请勿重复借阅！");
        }

        // 判断这本书的库存
        Book book = bookService.getById(borrow.getBookId());
        if (book.getStock() == 0) {
            throw new LMException("库存不足！");
        }

        borrow.setUserId(SecurityUtil.getUserId());
        LocalDateTime now = LocalDateTime.now();
        borrow.setBorrowTime(now);
        // 默认30天之后到期
        borrow.setMaturityTime(now.plusDays(30));
        // 减库存
        bookService.lambdaUpdate()
                .eq(BaseEntity::getId, borrow.getId())
                .set(Book::getStock, book.getStock() - 1)
                .update();
        return this.save(borrow);
    }

    @Override
    public IPage<BorrowVO> pageMy(Page<Borrow> page, String key) {
        return this.baseMapper.page(page, key);
    }

    @Override
    public IPage<BorrowVO> my(Page<Borrow> page, String key) {
        return this.baseMapper.my(page, key, SecurityUtil.getUserId());
    }

    @Override
    public Boolean remand(Borrow borrow) {
        borrow.setReturnTime(LocalDateTime.now());
        if (Num.Y.v().equals(borrow.getReturned())) {
            // 归还 书库存+1 查询书id
            Borrow byId = this.getById(borrow.getId());
            bookService.lambdaUpdate()
                    .eq(BaseEntity::getId, byId.getBookId())
                    .setSql("stock = stock + 1")
                    .update();
        }
        return this.updateById(borrow);
    }
}

