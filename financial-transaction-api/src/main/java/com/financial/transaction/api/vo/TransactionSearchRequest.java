package com.financial.transaction.api.vo;

import com.financial.transaction.common.enums.TransactionMethod;
import com.financial.transaction.common.enums.TransactionStatus;
import com.financial.transaction.common.enums.TransactionType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public class TransactionSearchRequest {
    @Schema(description = "账户ID，用于筛选特定账户的交易记录", example = "ACC123456")
    private String accountId; // 账户ID

    @Schema(description = "交易类型，例如 STOCK(股票), BOND(债券) 等", example = "STOCK")
    private TransactionType transactionType; // 交易类型

    @Schema(description = "交易方式，BUY(买入) 或 SELL(卖出)", example = "BUY")
    private TransactionMethod transactionMethod; // 交易方式

    @Schema(description = "交易开始时间，格式：yyyy-MM-dd'T'HH:mm:ss", example = "2025-01-01T00:00:00")
    private LocalDateTime starTime; // 交易开始时间

    @Schema(description = "交易结束时间，格式：yyyy-MM-dd'T'HH:mm:ss", example = "2025-12-31T23:59:59")
    private LocalDateTime endTime; // 交易结束时间

    @Schema(description = "交易状态，PENDING(交易中), SUCCESS(已成功), CANCELLED(已撤销)", example = "PENDING")
    private TransactionStatus transactionStatus; // 交易状态

    @Schema(description = "当前页码，默认为1", example = "1")
    private Integer pageNo = 1; // 当前页码

    @Schema(description = "每页大小，默认为10", example = "10")
    private Integer pageSize = 10; // 每页大小

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

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
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