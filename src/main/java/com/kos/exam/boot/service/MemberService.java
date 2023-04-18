package com.kos.exam.boot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kos.exam.boot.repository.MemberRepository;
import com.kos.exam.boot.vo.Member;

@Service
public class MemberService {
	private MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public Member getMember(int id) {
		return memberRepository.getMember(id);
	}

	public List<Member> getMembers() {
		return memberRepository.getMembers();
	}

	public int join(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
		Member oldMember = getMemberByLoginId(loginId);
		if(oldMember!=null) {
			return -1;
		}
		
		if(getMemberByNameAndEmail(name,email)!=null) {
			return -2;
		}
		
		else {
			memberRepository.join(loginId, loginPw, name, nickname, cellphoneNo, email);
			return memberRepository.getLastInsertId();
		}
	}

	private Member getMemberByLoginId(String loginId) {
		return memberRepository.getMemberByLoginId(loginId);
	}
	
	private Member getMemberByNameAndEmail(String name, String email) {
		return memberRepository.getMemberByNameAndEmail(name, email);
	}

	public void deleteMember(int id) {
		memberRepository.deleteMember(id);
	}

	public void modifyMember(int id, String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
		memberRepository.modifyMember(id, loginId, loginPw, name, nickname, cellphoneNo, email);
	}
}