package com.kos.exam.boot.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kos.exam.boot.repository.ArticleRepository;
import com.kos.exam.boot.util.Ut;
import com.kos.exam.boot.vo.Article;
import com.kos.exam.boot.vo.ResultData;


@Service
public class ArticleService {

	@Autowired
	private ArticleRepository articleRepository;
	
	public Article getForPrintArticle(int id) {
		return articleRepository.getForPrintArticle(id);
	}
	
	public List<Article> getForPrintArticles() {
		return articleRepository.getForPrintArticles();
	}
	
	public ResultData<Integer> writeArticle(int memberId, String title, String body) {
		articleRepository.writeArticle(memberId, title, body);
		int id = articleRepository.getLastInsertId();
		
		return ResultData.from("s-1", Ut.f("%d번 게시물이 생성되었습니다", id), "게시물번호", id);
	}
	
	public void deleteArticle(int id) {
		articleRepository.deleteArticle(id);
	}

	public ResultData<Article> modifyArticle(int id, String title, String body) {
		articleRepository.modifyArticle(id, title, body);
		
		Article article = getForPrintArticle(id);
		
		return ResultData.from("S-1", Ut.f("%d번 게시물이 수정되었습니다.", id), "Article", article);
	}

	public ResultData<?> actorCanModify(int actorId, Article article) {
		if(article == null) {
			return ResultData.from("F-1", "권한이 없습니다.");
		}
		if(article.getMemberId() != actorId) {
			return ResultData.from("F-2", "권한이 없습니다.");
		}
		return ResultData.from("S-1", "수정 가능합니다.");
	}
}
