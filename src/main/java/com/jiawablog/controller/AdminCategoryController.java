package com.jiawablog.controller;

import com.jiawablog.dto.CategoryDto;
import com.jiawablog.dto.PageDto;
import com.jiawablog.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class AdminCategoryController {

    private static final Logger LOG = LoggerFactory.getLogger(AdminCategoryController.class);

    @Resource
    private CategoryService categoryService;

    @GetMapping("/admin/category")
    private String category() {
        return "admin/category/category";
    }

    @GetMapping("/admin/category/list")
    private String list(Model model, PageDto pageDto) {
        LOG.info("分类列表查询开始：{}", pageDto.toString());
        List<CategoryDto> categoryList = categoryService.list(pageDto);
        model.addAttribute("list", categoryList);
        model.addAttribute("page", pageDto);

        LOG.info("分类列表查询结束：{}", pageDto);
        return "admin/category/list";
    }

    @PostMapping("/admin/category/save")
    @ResponseBody
    private String save(CategoryDto categoryDto) {
        LOG.info("分类列表保存开始：{}", categoryDto);
        int i = categoryService.save(categoryDto);
        LOG.info("分类列表保存结束：{}", "success");
        return "success";
    }

    /**
     * @param id
     * @return
     */
    @DeleteMapping("/admin/category/delete/{id}")
    @ResponseBody
    private String delete(@PathVariable String id) {
        LOG.info("分类列表删除开始：{}", id);
        categoryService.delete(id);
        LOG.info("分类列表删除结束：{}", "success");
        return "success";
    }
}
