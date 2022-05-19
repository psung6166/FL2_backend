package com.snk.fl_solution_v02.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PageNavigationVo {

    private int totalCount; // 총갯수
    private int firstPage; // 기본페이지
    private int rowPerPage; //페이지당 행수
    private int currentPage; //현재페이지 번호
    private int naviCount; // 네비게이션에 보일 숫자수
    private int lastPage; //마지막페이지
    private int currDataNo; //현재데이터 no
    private int pageCount; //페이지카운트
    private String pageInputName; //current page input 네임
    private String pageCallFunction; //페이지변경시 호출될 javascript 함수
    
}
