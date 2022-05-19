package com.snk.fl_solution_v02.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserInfoVo {

	//private static final long serialVersionUID = 100L;
	
	/* 유저 기본정보 */
	private String userNo; //사용자번호
	private String userNm; //사용자이름
	private String userId; //사용자아이디
	
	/* 유저 추가정보 */
	private String userIp; //사용자아이피
	private String userOs; //사용자운영체제
	private String userBr; //사용자브라우저
	private String userTy; //사용자타입(PC, MOBILE)
}