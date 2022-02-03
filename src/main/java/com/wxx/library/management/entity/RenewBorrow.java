package com.wxx.library.management.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 续借(RenewBorrow)表实体类
 *
 * @author wangxin
 * @since 2022-02-03 21:17:13
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RenewBorrow extends BaseEntity {
    /**
     * 借阅记录id
     */
    private String borrowId;

    /**
     * 状态 0-审批中 1-同意续借 2-拒绝续借
     */
    private Integer status;

    /**
     * 续借时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime renewTime;

    /**
     * 审批时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime approvalTime;
}
