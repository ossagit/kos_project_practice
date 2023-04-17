package com.kos.exam.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kos.exam.boot.service.MemberService;
import com.kos.exam.boot.vo.Member;

@Controller
public class UsrMemberController {
	// 인스턴스 변수 시작
	@Autowired
	private MemberService memberService;

	// 인스턴스 변수 끝

	// 생성자
	public UsrMemberController() {

	}
	// 서비스 메서드 시작

	@RequestMapping("/usr/member/getMember")
	@ResponseBody
	private Object getMember(int id) {
		Member Member = memberService.getMember(id);
		if (Member == null) {
			return id + "번 회원정보가 존재하지 않습니다.";
		}
		return Member;
	}

	@RequestMapping("/usr/member/getMembers")
	@ResponseBody
	public List<Member> getMembers() {
		return memberService.getMembers();
	}

	// 서비스 메서드 끝

	// 액션 메서드 시작
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public Member doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
		int id = memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);
		Member Member = memberService.getMember(id);
		return Member;
	}

	@RequestMapping("/usr/member/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		Member Member = memberService.getMember(id);
		if (Member == null) {
			return id + "번 회원이 존재하지 않습니다.";
		}

		memberService.deleteMember(id);
		return id + "번 회원정보가 삭제되었습니다.";
	}

	@RequestMapping("/usr/member/doModify")
	@ResponseBody
	public String doModify(int id, String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
		Member Member = memberService.getMember(id);
		if (Member == null) {
			return id + "번 회원이 존재하지 않습니다.";
		}

		memberService.modifyMember(id, loginId, loginPw, name, nickname, cellphoneNo, email);
		return id + "번 회원정보가 수정되었습니다.";
	}
}