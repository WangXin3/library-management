package com.wxx.library.management.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxx.library.management.entity.BaseEntity;
import com.wxx.library.management.entity.Menu;
import com.wxx.library.management.entity.dto.MenuDTO;
import com.wxx.library.management.entity.dto.MetaDTO;
import com.wxx.library.management.entity.vo.MenuVO;
import com.wxx.library.management.mapper.MenuMapper;
import com.wxx.library.management.service.MenuService;
import com.wxx.library.management.util.SecurityUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 菜单(Menu)表服务实现类
 *
 * @author wangxin
 * @since 2021-12-11 21:15:23
 */
@Service
@AllArgsConstructor
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public List<MenuDTO> buildMenu() {
        List<Menu> menus;
        if (SecurityUtil.getAuthorities().contains("admin")) {
            menus = this.lambdaQuery().ne(Menu::getType, 2)
                    .list();
        } else {
            menus = this.getBaseMapper().getMenuByUserId(SecurityUtil.getUserId());
        }


        List<MenuDTO> menuDTOS = menus.stream().map(m -> {
            MenuDTO menuDTO = new MenuDTO();
            BeanUtil.copyProperties(m, menuDTO);

            menuDTO.setMeta(new MetaDTO(m.getIcon(), m.getName()));
            menuDTO.setComponent(StrUtil.isBlank(m.getComponent()) ? "Layout" : m.getComponent());
            if (StrUtil.isBlank(m.getPid())) {
                menuDTO.setPath(StrUtil.isNotBlank(menuDTO.getPath()) ? "/" + menuDTO.getPath() : null);
            }
            menuDTO.setChildren(new ArrayList<>());

            return menuDTO;
        }).collect(Collectors.toList());

        for (MenuDTO menuDTO1 : menuDTOS) {
            List<MenuDTO> children = menuDTO1.getChildren();
            for (MenuDTO menuDTO2 : menuDTOS) {
                if (StrUtil.isBlank(menuDTO1.getPid()) && menuDTO1.getId().equals(menuDTO2.getPid())) {
                    children.add(menuDTO2);
                }
            }
        }

        menuDTOS = menuDTOS.stream().filter(m -> StrUtil.isBlank(m.getPid())).collect(Collectors.toList());

        return menuDTOS;
    }

    @Override
    public List<MenuVO> buildTree(Menu menu) {
        List<Menu> list = this.lambdaQuery()
                .like(Menu::getName, "%" + menu.getName() + "%")
                .isNull(Menu::getPid)
                .list();

        return getMenuVOS(list);
    }

    private List<MenuVO> getMenuVOS(List<Menu> list) {
        List<String> ids = list.stream().map(BaseEntity::getId).collect(Collectors.toList());
        Map<String, Long> map = this.lambdaQuery()
                .in(Menu::getPid, ids)
                .list()
                .stream()
                .collect(Collectors.groupingBy(Menu::getPid, Collectors.counting()));
        return list.stream().map(l -> {
            MenuVO vo = new MenuVO();
            BeanUtil.copyProperties(l, vo);
            vo.setHasChildren(map.get(l.getId()) != null && map.get(l.getId()) > 0);
            return vo;
        }).collect(Collectors.toList());
    }

    @Override
    public List<MenuVO> getChildren(String pid) {
        List<Menu> list = this.lambdaQuery().eq(Menu::getPid, pid).list();
        return getMenuVOS(list);
    }
}

