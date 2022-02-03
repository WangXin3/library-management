package com.wxx.library.management.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wxx.library.management.entity.Borrow;
import com.wxx.library.management.service.BorrowService;
import com.wxx.library.management.util.RespBean;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * (Borrow)表控制层
 *
 * @author wangxin
 * @since 2022-02-03 19:29:12
 */
@RestController
@RequestMapping("/borrow")
@AllArgsConstructor
public class BorrowController {

    private final BorrowService borrowService;

    /**
     * 借阅列表
     *
     * @param page 分页对象
     * @return 所有数据
     */
    @GetMapping
    @PreAuthorize("@lm.check('borrow:list')")
    public RespBean selectAll(Page<Borrow> page, String key) {
        return RespBean.success(borrowService.pageMy(page, key));
    }

    /**
     * 发起借阅
     *
     * @param borrow 实体对象
     * @return 新增结果
     */
    @PostMapping
    @PreAuthorize("@lm.check('book-borrow:launch')")
    public RespBean insert(@RequestBody Borrow borrow) {
        return RespBean.success(borrowService.saveMy(borrow));
    }

    /**
     * 我的借阅
     *
     * @param page /
     * @param key  /
     * @return /
     */
    @GetMapping("/my")
    @PreAuthorize("@lm.check('my-borrow:list')")
    public RespBean my(Page<Borrow> page, String key) {
        return RespBean.success(borrowService.my(page, key));
    }

    /**
     * 处理图书归还
     *
     * @param borrow /
     * @return /
     */
    @PostMapping("/remand")
    @PreAuthorize("@lm.check('borrow:remand')")
    public RespBean remand(@RequestBody Borrow borrow) {
        return RespBean.success(borrowService.remand(borrow));
    }
}

