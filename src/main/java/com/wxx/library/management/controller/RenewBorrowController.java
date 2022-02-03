package com.wxx.library.management.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wxx.library.management.entity.RenewBorrow;
import com.wxx.library.management.entity.vo.RenewBorrowVO;
import com.wxx.library.management.service.RenewBorrowService;
import com.wxx.library.management.util.RespBean;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 续借(RenewBorrow)表控制层
 *
 * @author wangxin
 * @since 2022-02-03 21:17:13
 */
@RestController
@RequestMapping("/renewBorrow")
@AllArgsConstructor
public class RenewBorrowController {

    private final RenewBorrowService renewBorrowService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param key  查询字段
     * @return 所有数据
     */
    @GetMapping
    @PreAuthorize("@lm.check('renew:list')")
    public RespBean selectAll(Page<RenewBorrowVO> page, String key) {
        return RespBean.success(renewBorrowService.pageMy(page, key));
    }


    /**
     * 发起续借
     *
     * @param renewBorrow 实体对象
     * @return 新增结果
     */
    @PostMapping
    @PreAuthorize("@lm.check('my-borrow:renew')")
    public RespBean insert(@RequestBody RenewBorrow renewBorrow) {
        return RespBean.success(renewBorrowService.renew(renewBorrow));
    }

    /**
     * 处理续借
     *
     * @param renewBorrow 续借
     * @return /
     */
    @PostMapping("/approval")
    @PreAuthorize("@lm.check('renew:approve')")
    public RespBean approval(@RequestBody RenewBorrow renewBorrow) {
        return RespBean.success(renewBorrowService.approval(renewBorrow));
    }
}

