package com.wxx.library.management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxx.library.management.entity.Menu;
import com.wxx.library.management.entity.dto.MenuDTO;
import com.wxx.library.management.mapper.MenuMapper;
import com.wxx.library.management.service.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return null;
    }
}

