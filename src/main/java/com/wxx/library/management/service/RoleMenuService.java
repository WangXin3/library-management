package com.wxx.library.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wxx.library.management.entity.RoleMenu;

import java.util.List;

/**
 * 角色和菜单绑定关系(RoleMenu)表服务接口
 *
 * @author wangxin
 * @since 2021-12-11 21:53:48
 */
public interface RoleMenuService extends IService<RoleMenu> {

    Boolean saveOrUpdateMenuByRoleId(String roleId, List<String> menuIds);
}

