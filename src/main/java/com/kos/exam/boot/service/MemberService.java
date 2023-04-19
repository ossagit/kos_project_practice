package com.kos.exam.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kos.exam.boot.repository.MemberRepository;
import com.kos.exam.boot.util.Ut;
import com.kos.exam.boot.vo.Member;
import com.kos.exam.boot.vo.ResultData;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	public ResultData join(Member member){
		Member oldMember = memberRepository.selectMemberByLoginId(member.getLoginId());
		
		if(oldMember != null) {
			return ResultData.from("F-7", Ut.f("해당 로그인 아이디(%s)는 이미 사용중입니다.", member.getLoginId()));
		}
		oldMember = memberRepository.selectMemberByNameEmail(member);
		if(oldMember != null) {
			return ResultData.from("F-8", Ut.f("해당 이름(%s)과 이메일(%s)은 이미 사용중입니다.", member.getName(),member.getEmail()));
		}
		
		
		memberRepository.insertMember(member);
		
		int id = memberRepository.selectLastInsertId(); 
		
		return ResultData.from("S-1", "회원가입이 완료되었습니다.","id",id);
	}

	public Member getMemberById(int id) {
		return memberRepository.selectMemberById(id);
	}
	
	public List<Member> getMemberList() {
		List<Member> memberList = memberRepository.selectMemberList();
		return memberList;
	}

	public Member getMemberByLoginId(String loginId) {
		return memberRepository.selectMemberByLoginId(loginId);
	}

}
