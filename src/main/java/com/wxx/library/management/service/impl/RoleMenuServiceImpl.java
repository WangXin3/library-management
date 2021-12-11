package com.wxx.library.management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxx.library.management.mapper.RoleMenuMapper;
import com.wxx.library.management.entity.RoleMenu;
import com.wxx.library.management.service.RoleMenuService;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

/**
 * 角色和菜单绑定关系(RoleMenu)表服务实现类
 *
 * @author wangxin
 * @since 2021-12-11 21:53:48
 */
@Service
@AllArgsConstructor
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

}

