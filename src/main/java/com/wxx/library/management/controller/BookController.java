package com.wxx.library.management.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wxx.library.management.entity.Book;
import com.wxx.library.management.entity.vo.BookVO;
import com.wxx.library.management.service.BookService;
import com.wxx.library.management.util.RespBean;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 书(Book)表控制层
 *
 * @author wangxin
 * @since 2021-12-12 20:33:09
 */
@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param book 查询实体
     * @return 所有数据
     */
    @GetMapping
    @PreAuthorize("@lm.check('book:list', 'book-borrow:list')")
    public RespBean selectAll(Page<BookVO> page, Book book) {
        return RespBean.success(bookService.pageVO(page, book));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    @PreAuthorize("@lm.check('book:list')")
    public RespBean selectOne(@PathVariable String id) {
        return RespBean.success(bookService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param book 实体对象
     * @return 新增结果
     */
    @PostMapping
    @PreAuthorize("@lm.check('book:add')")
    public RespBean insert(@RequestBody Book book) {
        return RespBean.success(bookService.save(book));
    }

    /**
     * 修改数据
     *
     * @param book 实体对象
     * @return 修改结果
     */
    @PutMapping
    @PreAuthorize("@lm.check('book:edit')")
    public RespBean update(@RequestBody Book book) {
        return RespBean.success(bookService.updateById(book));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    @PreAuthorize("@lm.check('book:del')")
    public RespBean delete(@RequestParam("idList") List<String> idList) {
        return RespBean.success(bookService.removeByIds(idList));
    }

    @GetMapping("/hotBook")
    @PreAuthorize("@lm.check('hot-book:list')")
    public RespBean hotBook() {
        return RespBean.success(bookService.hotBook());
    }
}

