package com.lxp.mapper;

import com.lxp.util.mapper.MyBatisRepository;
import com.lxp.vo.Article;

@MyBatisRepository
public interface ArticleMapper {
    Integer insertArticle(Article article);
}
