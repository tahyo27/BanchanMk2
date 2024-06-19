package com.hello.banchanMk2.common;

import lombok.Data;

@Data
public class PageViewInfo {
    private int nowPage;
    private int startPage;
    private int endPage;
    private int total;
    private int pageListSize; //보여질 목록수
    private int lastPage;
    private int viewPage; //밑에 보여줄 페이지 수

    public PageViewInfo(int nowPage, int total, int pageSize, int viewPage) {
        this.nowPage = nowPage;
        this.total = total;
        this.pageListSize = pageSize;
        this.viewPage = viewPage;
        calcLastPage(getTotal(), getPageListSize());
        calcStartEndPage(nowPage, viewPage);
    }

    private void calcLastPage(int total, int cntPerPage) { //마지막 페이지
        setLastPage((int) Math.ceil((double) total / (double) cntPerPage));
    }

    private void calcStartEndPage(int nowPage, int viewPage) {
        setEndPage(((int) Math.ceil((double) nowPage / viewPage)) * viewPage); // 밑에 보일 마지막 페이지
        if(getLastPage() < getEndPage()) {
            setEndPage(getLastPage()); // 보일 페이지가 진짜 마지막보다 크면 마지막으로 설정
        }
        setStartPage(getEndPage() - viewPage + 1); // 보일 마지막 페이지 - 보일 페이지 개수 + 1
        if(getStartPage() < 1) { // 최소 1
            setStartPage(1);
        }
    }
}
