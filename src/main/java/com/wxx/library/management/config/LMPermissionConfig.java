package com.wxx.library.management.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wangxin
 * @since 2021/12/12
 */
@Service(value = "lm")
public class LMPermissionConfig {

    public Boolean check(String ...permissions){

        Collection<? extends GrantedAuthority> collection = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        // 获取当前用户的所有权限
        List<String> lmPermissions = collection.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        // 判断当前用户的所有权限是否包含接口上定义的权限
        return lmPermissions.contains("admin") || Arrays.stream(permissions).anyMatch(lmPermissions::contains);
    }
}
