package com.jiawablog.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiawablog.db.Article;
import com.jiawablog.db.Content;
import com.jiawablog.dto.ArticleDto;
import com.jiawablog.dto.ContentDto;
import com.jiawablog.dto.PageDto;
import com.jiawablog.mapper.ArticleMapper;
import com.jiawablog.mapper.ContentMapper;
import com.jiawablog.util.UuidUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ArticleService {

    @Resource
    public ArticleMapper articleMapper;

    @Resource
    public ContentMapper contentMapper;

    public List<ArticleDto> list(PageDto pageDto) {
        // 只对第一个查询语句有效
        PageHelper.startPage(pageDto.getCurrent(), pageDto.getSize());
        List<ArticleDto> articleDtoList = new ArrayList<>();
        List<Article> articles = articleMapper.selectByExample(null);
        PageInfo<Article> pageInfo = new PageInfo<>(articles);
        long total = pageInfo.getTotal(); // 得到总行数
        pageDto.setTotal(total);

        for (int i = 0; i < articles.size(); i++) {
            Article article = articles.get(i);
            ArticleDto articleDto = new ArticleDto();
            BeanUtils.copyProperties(article, articleDto);
            articleDtoList.add(articleDto);
        }
        return articleDtoList;
    }

    public int create(Article article) {
        return articleMapper.insert(article);
    }

    public int delete(String id) {
        return articleMapper.deleteByPrimaryKey(id);
    }

    /**
     * 保存文章
     * @param articleDto
     * @return
     */
    public int save(ArticleDto articleDto) {
        Article article = new Article();
        articleDto.setAt(new Date());
        BeanUtils.copyProperties(articleDto, article);
        int i = articleMapper.updateByPrimaryKey(article);
        if (i == 0) {
            String id = UuidUtil.uuid();
            article.setId(id);
            i = this.create(article);
        }
        return i;
    }

    /**
     * 保存文章
     * @param contentDto
     * @return
     */
    public int save(ContentDto contentDto) {
        Content content = new Content();
        BeanUtils.copyProperties(contentDto, content);
        int i = contentMapper.updateByPrimaryKeyWithBLOBs(content);
        if (i == 0) {
            String id = UuidUtil.uuid();
            content.setId(id);
            i = contentMapper.insert(content);
        }
        return i;
    }
}
