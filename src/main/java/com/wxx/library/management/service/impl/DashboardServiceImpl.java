package com.wxx.library.management.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.wxx.library.management.entity.vo.DashboardVO;
import com.wxx.library.management.service.BookCategoryService;
import com.wxx.library.management.service.BookService;
import com.wxx.library.management.service.DashboardService;
import com.wxx.library.management.service.UserService;
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

    @Override
    public DashboardVO getNum() {
        DashboardVO dashboardVO = new DashboardVO();

        // 查询用户数
        Integer userNum = userService.lambdaQuery().count();
        dashboardVO.setUserNum(userNum);

        // 查询书数量
        Integer bookCount = bookService.lambdaQuery().count();
        dashboardVO.setBookNum(bookCount);

        // TODO 查询借阅数量
        dashboardVO.setBorrowNum(RandomUtil.randomInt(999, 100000));

        // 查询分类数量
        Integer categoryCount = bookCategoryService.lambdaQuery().count();
        dashboardVO.setCategoryNum(categoryCount);

        return dashboardVO;
    }
}
