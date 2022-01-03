package com.wxx.library.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wxx.library.management.entity.Role;

import java.util.List;

/**
 * 角色(Role)表服务接口
 *
 * @author wangxin
 * @since 2021-12-11 21:15:40
 */
public interface RoleService extends IService<Role> {

    Boolean removeByIdsAndOther(List<String> idList);
}

