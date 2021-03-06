package com.jiawablog.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiawablog.db.Article;
import com.jiawablog.db.ArticleExample;
import com.jiawablog.db.Content;
import com.jiawablog.dto.ArticleDto;
import com.jiawablog.dto.ArticlePageDto;
import com.jiawablog.dto.ContentDto;
import com.jiawablog.mapper.ArticleMapper;
import com.jiawablog.mapper.ContentMapper;
import com.jiawablog.util.UuidUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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

    public List<ArticleDto> list(ArticlePageDto pageDto) {
        // 只对第一个查询语句有效
        PageHelper.startPage(pageDto.getCurrent(), pageDto.getSize());
        List<ArticleDto> articleDtoList = new ArrayList<>();

        ArticleExample example = new ArticleExample();
        ArticleExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(pageDto.getStatus())) {
            criteria.andStatusEqualTo(pageDto.getStatus());
        }
        if (!StringUtils.isEmpty(pageDto.getCategoryId())) {
            criteria.andCategoryIdEqualTo(pageDto.getCategoryId());
        }

        List<Article> articles = articleMapper.selectByExample(example);
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

    @Transactional
    public int delete(String id) {
        contentMapper.deleteByPrimaryKey(id);
        if (1 == 1) {
            throw new RuntimeException("事务测试异常");
        }
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
            content.setId(contentDto.getId());
            i = contentMapper.insert(content);
        }
        return i;
    }

    public ContentDto findContent(String id) {
        Content content = contentMapper.selectByPrimaryKey(id);
        if (content == null) {
            return null;
        }
        ContentDto contentDto = new ContentDto();
        BeanUtils.copyProperties(content, contentDto);
        return contentDto;
    }

    public ArticleDto findArticle(String id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        ArticleDto articleDto = new ArticleDto();
        BeanUtils.copyProperties(article, articleDto);
        ContentDto contentDto = findContent(id);
        articleDto.setContent(contentDto);
        return articleDto;
    }
}
