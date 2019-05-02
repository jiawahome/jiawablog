package com.jiawablog.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiawablog.db.Category;
import com.jiawablog.dto.CategoryDto;
import com.jiawablog.dto.PageDto;
import com.jiawablog.mapper.CategoryMapper;
import com.jiawablog.util.UuidUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Resource
    public CategoryMapper categoryMapper;

    /**
     * 查出所有的分类
     * @return
     */
    public List<CategoryDto> all() {
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        List<Category> categorys = categoryMapper.selectByExample(null);

        for (int i = 0; i < categorys.size(); i++) {
            Category category = categorys.get(i);
            CategoryDto categoryDto = new CategoryDto();
            BeanUtils.copyProperties(category, categoryDto);
            categoryDtoList.add(categoryDto);
        }
        return categoryDtoList;
    }

    public List<CategoryDto> list(PageDto pageDto) {
        // 只对第一个查询语句有效
        PageHelper.startPage(pageDto.getCurrent(), pageDto.getSize());
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        List<Category> categorys = categoryMapper.selectByExample(null);
        PageInfo<Category> pageInfo = new PageInfo<>(categorys);
        long total = pageInfo.getTotal(); // 得到总行数
        pageDto.setTotal(total);

        for (int i = 0; i < categorys.size(); i++) {
            Category category = categorys.get(i);
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(category.getId());
            categoryDto.setName(category.getName());
            categoryDtoList.add(categoryDto);
        }
        return categoryDtoList;
    }

    public int create(Category category) {
        return categoryMapper.insert(category);
    }

    public int delete(String id) {
        return categoryMapper.deleteByPrimaryKey(id);
    }

    /**
     * 保存分类
     * @param categoryDto
     * @return
     */
    public int save(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        int i = categoryMapper.updateByPrimaryKey(category);
        if (i == 0) {
            String id = UuidUtil.uuid();
            category.setId(id);
            i = this.create(category);
        }
        return i;
    }
}
