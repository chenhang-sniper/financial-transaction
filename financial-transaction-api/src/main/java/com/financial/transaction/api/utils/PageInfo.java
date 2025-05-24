package com.financial.transaction.api.utils;
import java.util.Collections;
import java.util.List;


import lombok.Data;


@Data
public class PageInfo<T>  {

    /**
     * totalList
     */
    private List<T> totalList = Collections.emptyList();
    /**
     * total
     */
    private Integer total = 0;
    /**
     * total Page
     */
    private Integer totalPage;
    /**
     * page size
     */
    private Integer pageSize = 20;
    /**
     * current page
     */
    private Integer currentPage = 0;
    /**
     * pageNo
     */
    private Integer pageNo;

    public PageInfo() {

    }

    public PageInfo(Integer currentPage, Integer pageSize) {
        if (currentPage == null) {
            currentPage = 1;
        }
        this.pageNo = (currentPage - 1) * pageSize;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }




}
