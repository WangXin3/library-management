package com.wxx.library.management.controller;

import com.wxx.library.management.service.DashboardService;
import com.wxx.library.management.util.RespBean;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangxin
 * @since 2022/1/30
 */
@RestController
@RequestMapping("/dashboard")
@AllArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/getNum")
    public RespBean getNum() {
        return RespBean.success(dashboardService.getNum());
    }
}
