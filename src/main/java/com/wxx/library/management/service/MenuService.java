package com.wxx.library.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wxx.library.management.entity.Menu;
import com.wxx.library.management.entity.dto.MenuDTO;
import com.wxx.library.management.entity.vo.MenuVO;

import java.util.List;

/**
 * 菜单(Menu)表服务接口
 *
 * @author wangxin
 * @since 2021-12-11 21:15:23
 */
public interface MenuService extends IService<Menu> {

    List<MenuDTO> buildMenu();

    List<MenuVO> buildTree(Menu menu);

    List<MenuVO> getChildren(String pid);

    List<String> getMenuByRoleId(String roleId);

    List<String> getPermissionByUserId(String id);
}

