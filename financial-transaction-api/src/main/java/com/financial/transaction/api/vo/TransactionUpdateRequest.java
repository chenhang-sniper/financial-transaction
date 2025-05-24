package com.financial.transaction.api.vo;

import com.financial.transaction.common.enums.TransactionMethod;
import com.financial.transaction.common.enums.TransactionStatus;
import com.financial.transaction.common.enums.TransactionType;
import io.swagger.v3.oas.annotations.media.Schema;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionUpdateRequest {
    @Schema(description = "交易唯一标识符", example = "1234567890")
    private Long id; // 交易ID

    @Schema(description = "账户ID，表示此次交易所属的账户信息", example = "ACC123456")
    private String accountId; // 账户ID

    @Schema(description = "交易类型，例如 STOCK(股票), BOND(债券) 等", example = "STOCK")
    private TransactionType transactionType; // 交易类型

    @Schema(description = "交易方式，BUY(买入) 或 SELL(卖出)", example = "BUY")
    private TransactionMethod transactionMethod; // 交易方式

    @Schema(description = "交易金额，必须大于零", example = "100.50")
    private BigDecimal amount; // 交易金额

    @Schema(description = "交易时间，格式：yyyy-MM-dd'T'HH:mm:ss", example = "2025-05-24T10:00:00")
    private LocalDateTime transactionTime; // 交易时间

    @Schema(description = "交易状态，PENDING(交易中), SUCCESS(已成功), CANCELLED(已撤销)", example = "PENDING")
    private TransactionStatus transactionStatus; // 交易状态

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public TransactionMethod getTransactionMethod() {
        return transactionMethod;
    }

    public void setTransactionMethod(TransactionMethod transactionMethod) {
        this.transactionMethod = transactionMethod;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
}