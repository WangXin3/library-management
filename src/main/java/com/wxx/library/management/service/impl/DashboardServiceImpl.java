package com.wxx.library.management.service.impl;

import com.wxx.library.management.entity.vo.DashboardVO;
import com.wxx.library.management.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author wangxin
 * @since 2022/1/30
 */
@Service
@AllArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final UserService userService;
    private final BookService bookService;
    private final BookCategoryService bookCategoryService;
    private final BorrowService borrowService;
    @Override
    public DashboardVO getNum() {
        DashboardVO dashboardVO = new DashboardVO();

        // 查询用户数
        Integer userNum = userService.lambdaQuery().count();
        dashboardVO.setUserNum(userNum);

        // 查询书数量
        Integer bookCount = bookService.lambdaQuery().count();
        dashboardVO.setBookNum(bookCount);

        // 借阅次数
        int count = borrowService.count();
        dashboardVO.setBorrowNum(count);

        // 查询分类数量
        Integer categoryCount = bookCategoryService.lambdaQuery().count();
        dashboardVO.setCategoryNum(categoryCount);

        return dashboardVO;
    }
}
