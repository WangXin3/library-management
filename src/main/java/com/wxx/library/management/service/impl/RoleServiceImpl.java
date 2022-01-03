package com.wxx.library.management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxx.library.management.config.LMException;
import com.wxx.library.management.entity.Role;
import com.wxx.library.management.entity.RoleMenu;
import com.wxx.library.management.entity.UserRole;
import com.wxx.library.management.mapper.RoleMapper;
import com.wxx.library.management.service.RoleMenuService;
import com.wxx.library.management.service.RoleService;
import com.wxx.library.management.service.UserRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色(Role)表服务实现类
 *
 * @author wangxin
 * @since 2021-12-11 21:15:40
 */
@Service
@AllArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    private final RoleMenuService roleMenuService;
    private final UserRoleService userRoleService;

    @Override
    public Boolean removeByIdsAndOther(List<String> idList) {
        // 删除角色的同时，先校验是否有用户或菜单和这个角色关联，有关联则不能删除
        Integer roleMenuCount = roleMenuService.lambdaQuery().in(RoleMenu::getRoleId, idList).count();
        Integer userRoleCount = userRoleService.lambdaQuery().in(UserRole::getRoleId, idList).count();
        if (roleMenuCount > 0 || userRoleCount > 0) {
            throw new LMException("该角色绑定了菜单或用户，请先解绑！");
        }
        return this.removeByIds(idList);
    }
}

