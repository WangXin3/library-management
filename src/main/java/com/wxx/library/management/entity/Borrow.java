package com.wxx.library.management.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * (Borrow)表实体类
 *
 * @author wangxin
 * @since 2022-02-03 19:29:12
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Borrow extends BaseEntity {
    /**
     * 书id
     */
    private String bookId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 租借时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime borrowTime;

    /**
     * 到期时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime maturityTime;

    /**
     * 归还时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime returnTime;

    /**
     * 是否归还 0-未归还 1-已归还
     */
    private Integer returned;
}
