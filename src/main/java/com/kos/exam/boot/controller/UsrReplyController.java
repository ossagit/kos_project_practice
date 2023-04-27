package com.kos.exam.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kos.exam.boot.service.ReactionPointService;
import com.kos.exam.boot.service.ReplyService;
import com.kos.exam.boot.util.Ut;
import com.kos.exam.boot.vo.ResultData;
import com.kos.exam.boot.vo.Rq;

@Controller
public class UsrReplyController {
	
	private ReplyService replyService;
	private Rq rq;
	
	public UsrReplyController(ReplyService replyService, Rq rq) {
		this.replyService=replyService;
		this.rq=rq;
	}
	
	@RequestMapping("/usr/reply/doWrite")
	@ResponseBody
	String doWrite(String relTypeCode, int relId, String body, String replaceUri) {
		if(Ut.empty(relTypeCode)) {
			return rq.jsHistoryBack("relTypeCode를 입력해주세요.");
		}
		if(Ut.empty(relId)) {
			return rq.jsHistoryBack("relId를 입력해주세요.");
		}
		if(Ut.empty(body)) {
			return rq.jsHistoryBack("body를 입력해주세요.");
		}
		
		ResultData<Integer> writeReplyRd = replyService.writeReply(rq.getLoginedMemberId(), relTypeCode, relId, body);
		
		int id = writeReplyRd.getData1();
		
		if(Ut.empty(replaceUri)) {
			switch(relTypeCode) {
			case "article" :
				replaceUri = Ut.f("../article/detail?id=%d", relId);
				break;
			}
		}
		return rq.jsReplace(writeReplyRd.getMsg(), replaceUri);
	}

}
