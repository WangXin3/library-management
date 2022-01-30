package com.wxx.library.management.entity.vo;

import lombok.Data;

/**
 * @author wangxin
 * @since 2022/1/30
 */
@Data
public class DashboardVO {

    /**
     * 用户数
     */
    private Integer userNum;
    /**
     * 馆藏书数量
     */
    private Integer bookNum;
    /**
     * 借阅数量
     */
    private Integer borrowNum;
    /**
     * 分类数量
     */
    private Integer categoryNum;
}
