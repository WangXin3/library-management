package com.wxx.library.management.util;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wangxin
 * @since 2021/12/13
 */
public class SecurityUtil {

    private static final Map<String, String> THREAD_LOCAL = new HashMap<>();

    private SecurityUtil() {

    }

    public static void set(String key, String value) {
        THREAD_LOCAL.put(key, value == null ? StringUtils.EMPTY : value);
    }

    public static String get(String key) {
        return THREAD_LOCAL.getOrDefault(key, StringUtils.EMPTY);
    }

    public static void remove(String key) {
        THREAD_LOCAL.remove(key);
    }

    public static String getUsername() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static String getUserId() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
    }

    public static List<String> getAuthorities() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    }
}
