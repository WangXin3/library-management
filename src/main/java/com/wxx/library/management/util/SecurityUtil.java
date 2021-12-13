package com.wxx.library.management.util;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author wangxin
 * @since 2021/12/13
 */
public class SecurityUtil {

    private SecurityUtil() {

    }

    public static String getUsername() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static String getUserId() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
    }
}
