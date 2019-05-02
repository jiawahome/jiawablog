package com.jiawablog.controller;

import com.jiawablog.dto.ArticleDto;
import com.jiawablog.dto.CategoryDto;
import com.jiawablog.dto.PageDto;
import com.jiawablog.service.ArticleService;
import com.jiawablog.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class AdminArticleController {

    private static final Logger LOG = LoggerFactory.getLogger(AdminArticleController.class);

    @Resource
    private ArticleService articleService;

    @Resource
    private CategoryService categoryService;

    @GetMapping("/admin/article")
    private String article(Model model) {
        List<CategoryDto> categoryDtoList = categoryService.all();
        model.addAttribute("categorys", categoryDtoList);
        return "admin/article/article";
    }

    @GetMapping("/admin/article/list")
    private String list(Model model, PageDto pageDto) {
        LOG.info("文章列表查询开始：{}", pageDto.toString());
        List<ArticleDto> articleList = articleService.list(pageDto);
        model.addAttribute("list", articleList);
        model.addAttribute("page", pageDto);

        LOG.info("文章列表查询结束：{}", pageDto);
        return "admin/article/list";
    }

    @PostMapping("/admin/article/save")
    @ResponseBody
    private String save(ArticleDto articleDto) {
        LOG.info("文章列表保存开始：{}", articleDto);
        int i = articleService.save(articleDto);
        LOG.info("文章列表保存结束：{}", "success");
        return "success";
    }

    /**
     * @param id
     * @return
     */
    @DeleteMapping("/admin/article/delete/{id}")
    @ResponseBody
    private String delete(@PathVariable String id) {
        LOG.info("文章列表删除开始：{}", id);
        articleService.delete(id);
        LOG.info("文章列表删除结束：{}", "success");
        return "success";
    }
}
