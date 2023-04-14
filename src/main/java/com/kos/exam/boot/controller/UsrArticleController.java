package com.kos.exam.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kos.exam.boot.service.ArticleService;
import com.kos.exam.boot.vo.Article;

@Controller
public class UsrArticleController {
	// 인스턴스 변수 시작
	@Autowired
	private ArticleService articleService;

	// 인스턴스 변수 끝

	// 생성자
	public UsrArticleController() {

	}
	// 서비스 메서드 시작

	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	private Object getArticleAction(int id) {
		Article article = articleService.getArticle(id);
		if (article == null) {
			return id + "번 게시물이 존재하지 않습니다.";
		}
		return article;
	}

	// 서비스 메서드 끝

	// 액션 메서드 시작
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doAdd(String title, String body) {
		Article article = articleService.writeArticle(title, body);

		return article;
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		Article article = articleService.getArticle(id);
		if (article == null) {
			return id + "번 게시물이 존재하지 않습니다.";
		}

		articleService.deleteArticle(id);
		return id + "번 게시물 삭제되었습니다.";
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body) {
		Article article = articleService.getArticle(id);
		if (article == null) {
			return id + "번 게시물이 존재하지 않습니다.";
		}

		articleService.modifyArticle(id, title, body);
		return id + "번 게시물 수정되었습니다.";
	}
}