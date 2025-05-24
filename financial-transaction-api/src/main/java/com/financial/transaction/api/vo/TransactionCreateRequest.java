package com.financial.transaction.api.vo;

import com.financial.transaction.common.enums.TransactionMethod;
import com.financial.transaction.common.enums.TransactionStatus;
import com.financial.transaction.common.enums.TransactionType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Schema(description = "交易创建请求参数")
public class TransactionCreateRequest {
    @Schema(description = "交易唯一标识符，可选", example = "1234567890")
    private Long id;

    @Schema(description = "账户ID", example = "ACC123456")
    private String accountId;

    @Schema(description = "交易类型", example = "STOCK")
    private TransactionType transactionType;

    @Schema(description = "交易方式", example = "BUY")
    private TransactionMethod transactionMethod;

    @Schema(description = "交易金额", example = "100.50")
    private BigDecimal amount;

    @Schema(description = "交易时间", example = "2025-05-24T10:00:00")
    private LocalDateTime transactionTime;

    @Schema(description = "交易状态", example = "PENDING")
    private TransactionStatus transactionStatus;

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
