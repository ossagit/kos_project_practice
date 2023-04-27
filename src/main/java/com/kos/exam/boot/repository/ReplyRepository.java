package com.kos.exam.boot.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kos.exam.boot.vo.Board;

@Mapper
public interface ReplyRepository {

	   @Insert("""
	         INSERT INTO reply
	         SET regDate = NOW(),
	         updateDate = NOW(),
	         memberId = #{memberId},
	         relTypeCode = #{relTypeCode},
	         relId = #{relId},
	         `body` = #{body}
	         """)
	   void writeReply(int memberId, String relTypeCode, int relId, String body);

	   @Select("""
	         SELECT LAST_INSERT_ID()
	         """)
	   int getLastInsertId();

	   
	}