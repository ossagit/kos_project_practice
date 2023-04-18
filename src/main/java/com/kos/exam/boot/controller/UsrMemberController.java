package com.kos.exam.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kos.exam.boot.service.MemberService;
import com.kos.exam.boot.util.Ut;
import com.kos.exam.boot.vo.Member;
import com.kos.exam.boot.vo.ResultData;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/usr/member")
public class UsrMemberController {

	@Autowired
	private MemberService memberService;

	@GetMapping("/doJoin")
	public ResultData<?> doJoin(Member member){
		if(Ut.empty(member.getLoginId())) {
			return ResultData.from("F-1", "loginId을 입력해주세요.",null);
		}
		if(Ut.empty(member.getLoginPw())) {
			return ResultData.from("F-1", "loginPw을 입력해주세요.",null);
		}
		if(Ut.empty(member.getName())) {
			return ResultData.from("F-1", "name을 입력해주세요.",null);
		}
		if(Ut.empty(member.getNickname())) {
			return ResultData.from("F-1", "nickname을 입력해주세요.",null);
		}
		if(Ut.empty(member.getCellphoneNo())) {
			return ResultData.from("F-1", "cellphoneNo을 입력해주세요.",null);
		}
		if(Ut.empty(member.getEmail())) {
			return ResultData.from("F-1", "email을 입력해주세요.",null);
		}
		
		ResultData<Integer> joinRd = memberService.join(member);
		
		if(joinRd.isFail()) {
			return (ResultData<?>)joinRd;
		}		
		Member getMember = memberService.getMemberById(joinRd.getData1());
		
		return ResultData.newData(joinRd,getMember);
	}
	
	@GetMapping("/doLogin")
	public ResultData<Member> doLogin(HttpSession httpSession, Member member){
		boolean isLogined = false;
		
		if(httpSession.getAttribute("loginedMemberId")!=null) {
			isLogined = true;
		}
		
		if(isLogined) {
			return ResultData.from("F-5", "이미 로그인 되었습니다.", null);
		}
		
		if(Ut.empty(member.getLoginId())) {
			return ResultData.from("F-1", "loginId을 입력해주세요.",null);
		}
		if(Ut.empty(member.getLoginPw())) {
			return ResultData.from("F-1", "loginPw을 입력해주세요.",null);
		}
		
		Member getMember = memberService.getMemberByLoginId(member.getLoginId());
		
		if(getMember == null) {
			return ResultData.from("F-3", "존재하지 않은 로그인 아이디 입니다.",null);
		}
		
		if(!getMember.getLoginPw().equals(member.getLoginPw())) {
			return ResultData.from("F-4", "비밀번호가 일치하지 않습니다.",null);
		}
		
		httpSession.setAttribute("loginedMemberId", getMember.getId());
	
		return ResultData.from("S-1",Ut.f("(%s)님 환영합니다.", member.getLoginId()),null);
	}
	
	@GetMapping("/doLogout")
	public ResultData<Member> doLogout(HttpSession httpSession){
		boolean isLogined = false;
		
		if(httpSession.getAttribute("loginedMemberId")==null) {
			isLogined = true;
		}
		
		if(isLogined) {
			return ResultData.from("S-1", "이미 로그아웃 상태입니다.",null);
		}
		
		httpSession.removeAttribute("loginedMemberId");
	
		return ResultData.from("S-2","로그아웃 되었습니다.",null);
	}
	
	
	
	@GetMapping("/getMembers")
	public List<Member> getMembers(){
		List<Member> memberList = memberService.getMemberList();
		
		return memberList;
	}
	
	@GetMapping("/getMember")
	public Member getMember(int id) {
		Member member = memberService.getMemberById(id);
		return member;
	}
}
