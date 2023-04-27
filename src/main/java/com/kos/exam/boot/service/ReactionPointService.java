package com.kos.exam.boot.service;

import org.springframework.stereotype.Service;

import com.kos.exam.boot.repository.ReactionPointRepository;
import com.kos.exam.boot.vo.ResultData;

@Service
public class ReactionPointService {
	
	private ReactionPointRepository reactionPointRepository;
	private ArticleService articleService;
	
	public ReactionPointService(ReactionPointRepository reactionPointRepository, ArticleService articleService) {
		this.reactionPointRepository = reactionPointRepository;
		this.articleService = articleService;
	}
	public ResultData actorCanMakeReactionPoint(int actorId, String relTypeCode, int relId) {
		if(actorId ==0) {
			return ResultData.from("F-1", "로그인 후 이용해주세요.");
		}

		int sumReactionPointByMemberId = reactionPointRepository.getSumReactionPointByMemberId(relId, relTypeCode, actorId);
		
		if(sumReactionPointByMemberId != 0) {
			return ResultData.from("F-2", "리액션이 불가능합니다.", "sumReactionPointByMemberId", sumReactionPointByMemberId);
		}
		return ResultData.from("S-1", "리액션이 가능합니다.", "sumReactionPointByMemberId", sumReactionPointByMemberId);
	}
	public ResultData addGoodReactionPoint(int actorId, String relTypeCode, int relId) {
		reactionPointRepository.addGoodReactionPoint(actorId, relTypeCode, relId);
		
		switch(relTypeCode) {
		case "article":
			articleService.increaseGoodReactionPoint(relId);
		}
		return ResultData.from("S-1", "좋아요 처리 되었습니다.");
	}
	public ResultData addBadReactionPoint(int actorId, String relTypeCode, int relId) {
		reactionPointRepository.addBadReactionPoint(actorId, relTypeCode, relId);
		
		switch(relTypeCode) {
		case "article":
			articleService.increaseBadReactionPoint(relId);
		}
		return ResultData.from("S-1", "싫어요 처리 되었습니다.");
		
	}

}
