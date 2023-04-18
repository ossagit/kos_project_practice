package com.kos.exam.boot.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {

	private int id;
	private String regDate;
	private String updateDate;
	private String loginId;
	private String loginPw;
	private int authLevel;
	private String name;
	private String nickname;
	private String cellphoneNo;
	private String email;
	private boolean delStatus;
	private String delDate;
	
}
