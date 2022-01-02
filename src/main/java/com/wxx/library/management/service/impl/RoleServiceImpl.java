package com.wxx.library.management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxx.library.management.entity.Role;
import com.wxx.library.management.mapper.RoleMapper;
import com.wxx.library.management.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 角色(Role)表服务实现类
 *
 * @author wangxin
 * @since 2021-12-11 21:15:40
 */
@Service
@AllArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}

