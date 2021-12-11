package com.wxx.library.management.config;

import cn.hutool.core.util.StrUtil;
import com.wxx.library.management.constant.Num;
import com.wxx.library.management.service.UserRoleService;
import com.wxx.library.management.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wangxin
 * @since 2021/12/11
 */
@Service
@AllArgsConstructor
public class MyUserDetailService implements UserDetailsService {

    private final UserService userService;
    private final UserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 通过用户名去数据库查询账号数据
        com.wxx.library.management.entity.User user = userService.getUserByUsername(username);
        if (user == null) {
            // 找不到账号
            throw new UsernameNotFoundException("找不到账号！");
        }

        List<GrantedAuthority> authorities;
        if (Num.Y.v().equals(user.getAdmin())) {
            authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
        } else {
            // 查询这个账号的权限
            List<String> permissions = userRoleService.getPermissionByUserId(user.getId());
            authorities = permissions.stream().distinct().filter(StrUtil::isNotBlank).map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }
        return new User(username, user.getPassword(), authorities);
    }

}
