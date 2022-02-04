package com.wxx.library.management.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wxx.library.management.entity.BookCategory;
import com.wxx.library.management.service.BookCategoryService;
import com.wxx.library.management.util.RespBean;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 图书分类(BookCategory)表控制层
 *
 * @author wangxin
 * @since 2021-12-12 20:33:25
 */
@RestController
@RequestMapping("/bookCategory")
@AllArgsConstructor
public class BookCategoryController {

    private final BookCategoryService bookCategoryService;

    /**
     * 分页查询所有数据
     *
     * @param page         分页对象
     * @param bookCategory 查询实体
     * @return 所有数据
     */
    @GetMapping
    @PreAuthorize("@lm.check('bookCategory:list')")
    public RespBean selectAll(Page<BookCategory> page, BookCategory bookCategory) {
        return RespBean.success(bookCategoryService.page(page, new LambdaQueryWrapper<BookCategory>()
                .like(StrUtil.isNotBlank(bookCategory.getCategoryName()), BookCategory::getCategoryName, bookCategory.getCategoryName())));
    }

    @GetMapping("/buildCategoryTree")
    @PreAuthorize("@lm.check('bookCategory:list', 'book-borrow:list')")
    public RespBean buildCategoryTree(@RequestParam String categoryName) {
        return RespBean.success(bookCategoryService.buildCategoryTree(categoryName));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    @PreAuthorize("@lm.check('bookCategory:list')")
    public RespBean selectOne(@PathVariable String id) {
        return RespBean.success(bookCategoryService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param bookCategory 实体对象
     * @return 新增结果
     */
    @PostMapping
    @PreAuthorize("@lm.check('bookCategory:add')")
    public RespBean insert(@RequestBody BookCategory bookCategory) {
        return RespBean.success(bookCategoryService.save(bookCategory));
    }

    /**
     * 修改数据
     *
     * @param bookCategory 实体对象
     * @return 修改结果
     */
    @PutMapping
    @PreAuthorize("@lm.check('bookCategory:edit')")
    public RespBean update(@RequestBody BookCategory bookCategory) {
        return RespBean.success(bookCategoryService.updateById(bookCategory));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    @PreAuthorize("@lm.check('bookCategory:del')")
    public RespBean delete(@RequestParam("idList") List<String> idList) {
        return RespBean.success(bookCategoryService.removeByIds(idList));
    }

    @GetMapping("/hotCategory")
    @PreAuthorize("@lm.check('hot-category:list')")
    public RespBean hotCategory() {
        return RespBean.success(bookCategoryService.hotCategory());
    }
}

