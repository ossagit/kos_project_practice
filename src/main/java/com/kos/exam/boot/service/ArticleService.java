package com.kos.exam.boot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kos.exam.boot.repository.ArticleRepository;
import com.kos.exam.boot.vo.Article;

@Service
public class ArticleService {
	private ArticleRepository articleRepository;

	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
		articleRepository.makeTestData();
	}

	public Article getArticle(int id) {
		return articleRepository.getArticle(id);
	}

	public List<Article> getArticles() {
		return articleRepository.getArticles();
	}

	public Article writeArticle(String title, String body) {
		return articleRepository.writeArticle(title, body);
	}

	public void deleteArticle(int id) {
		articleRepository.deleteArticle(id);
	}

	public void modifyArticle(int id, String title, String body) {
		articleRepository.modifyArticle(id, title, body);
	}
}