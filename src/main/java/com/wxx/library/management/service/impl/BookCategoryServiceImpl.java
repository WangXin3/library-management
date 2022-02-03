package com.wxx.library.management.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxx.library.management.entity.BookCategory;
import com.wxx.library.management.entity.vo.BookCategoryVO;
import com.wxx.library.management.mapper.BookCategoryMapper;
import com.wxx.library.management.service.BookCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 图书分类(BookCategory)表服务实现类
 *
 * @author wangxin
 * @since 2021-12-11 21:13:58
 */
@Service
@AllArgsConstructor
public class BookCategoryServiceImpl extends ServiceImpl<BookCategoryMapper, BookCategory> implements BookCategoryService {

    @Override
    public List<BookCategoryVO> buildCategoryTree(String categoryName) {
        List<BookCategory> list = this.lambdaQuery()
                .like(StrUtil.isNotBlank(categoryName), BookCategory::getCategoryName, categoryName)
                .isNull(BookCategory::getParentId)
                .list();

        return this.getBookCategoryVOS(list);
    }

    private List<BookCategoryVO> getBookCategoryVOS(List<BookCategory> list) {
        return list.stream().map(l -> {
            BookCategoryVO vo = new BookCategoryVO();
            BeanUtil.copyProperties(l, vo);
            vo.setChildren(this.getChildren(vo.getId()));
            return vo;
        }).collect(Collectors.toList());
    }

    private List<BookCategoryVO> getChildren(String parentId) {
        List<BookCategory> list = this.lambdaQuery().eq(BookCategory::getParentId, parentId).list();
        if (CollUtil.isEmpty(list)) {
            return null;
        }
        return list.stream().map(l -> {
            BookCategoryVO vo = new BookCategoryVO();
            BeanUtil.copyProperties(l, vo);
            vo.setChildren(this.getChildren(l.getId()));
            return vo;
        }).collect(Collectors.toList());
    }
}

