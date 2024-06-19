package com.hello.banchanMk2.common;

import lombok.Data;
import lombok.Getter;

@Getter
public class Paging {
    private int pageNum;
    private int startPage;
    private int endPage;
    private int total;
    private int pageListSize; //보여질 목록수
    private int lastPage;
    private int viewPage; //밑에 보여줄 페이지 수
    private int offset;

    public Paging(int pageNum, int total, int pageSize, int viewPage) {
        this.pageNum = pageNum;
        this.total = total;
        this.pageListSize = pageSize;
        this.viewPage = viewPage;
        this.lastPage = calcLastPage(getTotal(), getPageListSize());
        calcStartEndPage(pageNum, viewPage);
        this.offset = calcOffset(pageNum, pageListSize);
    }

    private int calcLastPage(int total, int cntPerPage) { //마지막 페이지
        return ((int) Math.ceil((double) total / (double) cntPerPage));
    }

    private void calcStartEndPage(int pageNum, int viewPage) {
        this.endPage = (((int) Math.ceil((double) pageNum / viewPage)) * viewPage); // 밑에 보일 마지막 페이지
        if(getLastPage() < getEndPage()) {
            this.endPage = getLastPage(); // 보일 페이지가 진짜 마지막보다 크면 마지막으로 설정
        }
        this.startPage = (getEndPage() - viewPage + 1); // 보일 마지막 페이지 - 보일 페이지 개수 + 1
        if(getStartPage() < 1) { // 최소 1
            this.startPage = 1;
        }
    }

    private int calcOffset(int pageNum, int pageListSize ) {
        pageNum = Math.max(pageNum, 1);
        return (pageNum - 1) * pageListSize;
    }
}
