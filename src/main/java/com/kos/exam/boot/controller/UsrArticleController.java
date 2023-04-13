package com.kos.exam.boot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kos.exam.boot.vo.Article;

@Controller
public class UsrArticleController {
	int articlesLastId=0;
	private List<Article> articles;
	
	public UsrArticleController() {
		articles = new ArrayList<Article>();
		makeTestData();
	}
	
	private void makeTestData() {
		for(int i=0;i<10;i++) {
		int id=articlesLastId++;
		Article article = new Article(id, "제목"+i, "내용"+i);
		articles.add(article);
		}
	}
	
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doAdd(String title, String body) {
		int id=articlesLastId+1;
		Article article = new Article(id, title, body);
		articles.add(article);
		articlesLastId=id;
		return article;
	}
	
	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public List<Article> getArticle() {
		return articles;
	}

}
