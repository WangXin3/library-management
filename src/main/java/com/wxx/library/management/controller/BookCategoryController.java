package com.wxx.library.management.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wxx.library.management.entity.BookCategory;
import com.wxx.library.management.service.BookCategoryService;
import com.wxx.library.management.util.RespBean;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 图书分类(BookCategory)表控制层
 *
 * @author wangxin
 * @since 2021-12-11 21:13:58
 */
@RestController
@RequestMapping("/bookCategory")
@AllArgsConstructor
public class BookCategoryController {

    private final BookCategoryService bookCategoryService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param bookCategory 查询实体
     * @return 所有数据
     */
    @GetMapping
    public RespBean selectAll(Page<BookCategory> page, BookCategory bookCategory) {
        return RespBean.successData(bookCategoryService.page(page, new QueryWrapper<>(bookCategory)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public RespBean selectOne(@PathVariable String id) {
        return RespBean.successData(bookCategoryService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param bookCategory 实体对象
     * @return 新增结果
     */
    @PostMapping
    public RespBean insert(@RequestBody BookCategory bookCategory) {
        return RespBean.successData(bookCategoryService.save(bookCategory));
    }

    /**
     * 修改数据
     *
     * @param bookCategory 实体对象
     * @return 修改结果
     */
    @PutMapping
    public RespBean update(@RequestBody BookCategory bookCategory) {
        return RespBean.successData(bookCategoryService.updateById(bookCategory));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public RespBean delete(@RequestParam("idList") List<String> idList) {
        return RespBean.successData(bookCategoryService.removeByIds(idList));
    }
}

