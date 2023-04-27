package com.kos.exam.boot.service;

import org.springframework.stereotype.Service;

import com.kos.exam.boot.repository.ReplyRepository;
import com.kos.exam.boot.util.Ut;
import com.kos.exam.boot.vo.ResultData;

@Service
public class ReplyService {
	
	private ReplyRepository replyRepository;
	
	public ReplyService(ReplyRepository replyRepository) {
		this.replyRepository = replyRepository;
	}

	public ResultData<Integer> writeReply(int actorId, String relTypeCode, int relId, String body) {
		replyRepository.writeReply(actorId, relTypeCode, relId, body);
		int id = replyRepository.getLastInsertId();

		return ResultData.from("S-1", Ut.f("%d번 댓글이 생성되었습니다.", id), "id", id);
	}

}