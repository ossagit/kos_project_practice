package com.kos.exam.boot.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kos.exam.boot.vo.Article;

@Mapper
public interface ArticleRepository {

	public Article getArticle(int id);

	public List<Article> getArticles();

	public Article writeArticle(String title, String body);

	public void deleteArticle(int id);

	public void modifyArticle(int id, String title, String body);
}