package com.wxx.library.management.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxx.library.management.entity.User;
import com.wxx.library.management.entity.UserRole;
import com.wxx.library.management.entity.vo.UserVO;
import com.wxx.library.management.mapper.UserMapper;
import com.wxx.library.management.service.MenuService;
import com.wxx.library.management.service.UserRoleService;
import com.wxx.library.management.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户(User)表服务实现类
 *
 * @author wangxin
 * @since 2021-12-11 21:15:49
 */
@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserRoleService userRoleService;
    private final MenuService menuService;

    @Override
    public User getUserByUsername(String username) {
        return this.lambdaQuery().eq(User::getUsername, username).one();
    }

    @Override
    public UserVO infoByToken() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = this.getUserByUsername(username);
        List<String> permissions = menuService.getPermissionByUserId(user.getId());
        UserVO userVO = new UserVO();
        BeanUtil.copyProperties(user, userVO);
        userVO.setPermissions(permissions);
        return userVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveAndSetPassword(UserVO user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode("123456"));

        this.saveUserRole(user);
        return this.save(user);
    }

    private void saveUserRole(UserVO user) {
        List<UserRole> userRoleList = user.getRoles().stream().map(roleIds -> {
            UserRole userRole = new UserRole();
            userRole.setRoleId(roleIds);
            userRole.setUserId(user.getId());
            return userRole;
        }).collect(Collectors.toList());

        if (CollUtil.isNotEmpty(userRoleList)) {
            userRoleService.saveBatch(userRoleList);
        }
    }

    @Override
    public UserVO getByIdAndRole(String id) {
        User user = this.getById(id);
        List<String> roleIds = userRoleService.lambdaQuery().eq(UserRole::getUserId, user.getId())
                .list().stream().map(UserRole::getRoleId).collect(Collectors.toList());
        UserVO userVO = new UserVO();
        BeanUtil.copyProperties(user, userVO);
        userVO.setRoles(roleIds);

        return userVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateUser(UserVO user) {
        // 删除该用户的已有的角色
        userRoleService.lambdaUpdate().eq(UserRole::getUserId, user.getId()).remove();
        this.saveUserRole(user);
        return this.updateById(user);
    }
}

