package com.kos.exam.boot.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kos.exam.boot.vo.Article;

@Mapper
public interface ArticleRepository {
	
	public Article getForPrintArticle(@Param("id")int id);
	
	public List<Article> getForPrintArticles(@Param("boardId")int boardId, String searchKeywordTypeCode, String searchKeyword, int limitStart, int limitTake);
	
	public void writeArticle(@Param("memberId")int memberId, @Param("title")String title, @Param("boardId")int boardId, @Param("body")String body);
	
	public void deleteArticle(@Param("id")int id);

	public void modifyArticle(@Param("id")int id, @Param("title")String title, @Param("body")String body);

	public int getLastInsertId();

	public int getArticlesCount(int boardId, String searchKeywordTypeCode, String searchKeyword);

	public int increaseHitCount(int id);
	
}
