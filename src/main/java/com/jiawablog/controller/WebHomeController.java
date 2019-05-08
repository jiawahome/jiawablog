package com.jiawablog.controller;

import com.jiawablog.dto.ArticleDto;
import com.jiawablog.dto.ArticlePageDto;
import com.jiawablog.dto.CategoryDto;
import com.jiawablog.dto.OptionDto;
import com.jiawablog.service.ArticleService;
import com.jiawablog.service.CategoryService;
import com.jiawablog.util.OptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class WebHomeController {

    private static final Logger LOG = LoggerFactory.getLogger(AdminArticleController.class);

    @Resource
    private ArticleService articleService;

    @Resource
    private CategoryService categoryService;

    @GetMapping("/")
    public String index() {
        return "web/index";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable String id) {
        ArticleDto articleDto = articleService.findArticle(id);
        model.addAttribute("article", articleDto);
        return "web/detail";
    }

    @GetMapping("/web/article/list")
    private String list(Model model, ArticlePageDto pageDto) {
        LOG.info("文章列表查询开始：{}", pageDto.toString());
        pageDto.setStatus("P");
        List<ArticleDto> articleList = articleService.list(pageDto);
        model.addAttribute("list", articleList);
        model.addAttribute("page", pageDto);

        List<CategoryDto> categoryDtoList = categoryService.all();
        model.addAttribute("categorys", categoryDtoList);

        List<OptionDto> optionDtoList = OptionUtil.getStatusOption();
        model.addAttribute("statuss", optionDtoList);

        LOG.info("文章列表查询结束：{}", pageDto);
        return "web/list";
    }

}
