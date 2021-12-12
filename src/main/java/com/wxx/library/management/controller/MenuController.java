package com.wxx.library.management.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wxx.library.management.entity.Menu;
import com.wxx.library.management.service.MenuService;
import com.wxx.library.management.util.RespBean;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单(Menu)表控制层
 *
 * @author wangxin
 * @since 2021-12-12 20:32:29
 */
@RestController
@RequestMapping("/menu")
@AllArgsConstructor
public class MenuController {

    private final MenuService menuService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param menu 查询实体
     * @return 所有数据
     */
    @GetMapping
    @PreAuthorize("@lm.check('menu:list')")
    public RespBean selectAll(Page<Menu> page, Menu menu) {
        return RespBean.success(menuService.page(page, new QueryWrapper<>(menu)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    @PreAuthorize("@lm.check('menu:list')")
    public RespBean selectOne(@PathVariable String id) {
        return RespBean.success(menuService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param menu 实体对象
     * @return 新增结果
     */
    @PostMapping
    @PreAuthorize("@lm.check('menu:add')")
    public RespBean insert(@RequestBody Menu menu) {
        return RespBean.success(menuService.save(menu));
    }

    /**
     * 修改数据
     *
     * @param menu 实体对象
     * @return 修改结果
     */
    @PutMapping
    @PreAuthorize("@lm.check('menu:edit')")
    public RespBean update(@RequestBody Menu menu) {
        return RespBean.success(menuService.updateById(menu));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    @PreAuthorize("@lm.check('menu:del')")
    public RespBean delete(@RequestParam("idList") List<String> idList) {
        return RespBean.success(menuService.removeByIds(idList));
    }
}

