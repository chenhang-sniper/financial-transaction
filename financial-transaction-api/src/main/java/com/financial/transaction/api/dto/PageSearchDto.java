package com.financial.transaction.api.dto;

import com.financial.transaction.common.enums.TransactionMethod;
import com.financial.transaction.common.enums.TransactionStatus;
import com.financial.transaction.common.enums.TransactionType;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

@Data
public class PageSearchDto {
    String accountId;
    TransactionType transactionType;
    TransactionStatus transactionStatus;
    TransactionMethod transactionMethod;
    LocalDateTime starTime;
    LocalDateTime endTime;
    Integer pageNo;
    Integer pageSize;

    public String getKeyword() {
        StringBuilder stb = new StringBuilder("");
        stb.append(StringUtils.isNotBlank(accountId) ? accountId : "").append("-")
                .append(transactionType == null ? "" : transactionType).append("-")
                .append(transactionStatus == null ? "" : transactionStatus).append("-")
                .append(transactionMethod == null ? "" : transactionMethod).append("-")
                .append(starTime == null ? "" : starTime).append("-")
                .append(endTime == null ? "" : endTime).append("-")
                .append(pageNo == null ? "" : pageNo).append("-")
                .append(pageSize == null ? "" : pageSize);

        return stb.toString();
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public TransactionMethod getTransactionMethod() {
        return transactionMethod;
    }

    public void setTransactionMethod(TransactionMethod transactionMethod) {
        this.transactionMethod = transactionMethod;
    }

    public LocalDateTime getStarTime() {
        return starTime;
    }

    public void setStarTime(LocalDateTime starTime) {
        this.starTime = starTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
