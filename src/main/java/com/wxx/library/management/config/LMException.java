package com.wxx.library.management.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wangxin
 * @since 2022/1/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class LMException extends RuntimeException {
    private final String msg;
}
