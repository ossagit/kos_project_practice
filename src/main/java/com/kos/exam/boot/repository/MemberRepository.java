package com.kos.exam.boot.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kos.exam.boot.vo.Member;

@Mapper
public interface MemberRepository {
	
	@Insert("""
			INSERT INTO `member`
			SET regDate = NOW(),
			updateDate = NOW(),
			loginId = #{loginId},
			loginPw = #{loginPw},
			`name` = #{name},
			nickname = #{nickname},
			cellphoneNo = #{cellphoneNo},
			email = #{email}
			""")
	void insertMember(Member member);

	@Select("""
			SELECT * FROM MEMBER ORDER BY id DESC
			""")
	List<Member> selectMemberList();

	@Select("""
			SELECT * FROM MEMBER WHERE id=#{id}
			""")
	Member selectMemberById(int id);

	@Select("""
			SELECT LAST_INSERT_ID()
			""")
	int selectLastInsertId();

	@Select("""
			SELECT * FROM MEMBER WHERE loginId=#{loginId}
			""")
	Member selectMemberByLoginId(String loginId);

	@Select("""
			SELECT * FROM MEMBER 
			WHERE email=#{email}
			AND name = #{name}
			""")
	Member selectMemberByNameEmail(Member member);
	
}
