package com.wxx.library.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxx.library.management.entity.Menu;

import java.util.List;

/**
 * 菜单(Menu)表数据库访问层
 *
 * @author wangxin
 * @since 2021-12-11 21:15:23
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> getMenuByUserId(String userId);

    List<String> getPermissionByUserId(String id);
}

