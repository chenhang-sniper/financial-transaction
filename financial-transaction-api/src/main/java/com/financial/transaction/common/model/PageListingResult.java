package com.financial.transaction.common.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PageListingResult<T> {

    private List<T> records;

    private int totalCount;

    private int currentPage;

    private int pageSize;


}
