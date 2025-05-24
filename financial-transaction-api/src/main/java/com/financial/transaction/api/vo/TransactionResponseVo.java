package com.financial.transaction.api.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.financial.transaction.common.enums.TransactionMethod;
import com.financial.transaction.common.enums.TransactionStatus;
import com.financial.transaction.common.enums.TransactionType;
import com.financial.transaction.dao.pojo.Transaction;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 交易响应数据封装类
 * 用于向前端返回交易相关的详细信息
 */
@Schema(description = "交易响应数据，包含交易的完整信息")
public class TransactionResponseVo {

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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Schema(description = "交易流水ID，唯一标识符", example = "1234567890")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @Schema(description = "账户ID，表示此次交易所属的账户信息", example = "ACC123456")
    private String accountId; // 账户ID

    @Schema(description = "交易类型，例如 STOCK(股票), BOND(债券) 等", example = "STOCK")
    private TransactionType transactionType; // 交易类型

    @Schema(description = "交易方式，BUY(买入) 或 SELL(卖出)", example = "BUY")
    private TransactionMethod transactionMethod; // 交易方式

    @Schema(description = "交易金额，精确到小数点后两位", example = "100.50")
    private BigDecimal amount; // 交易金额

    @Schema(description = "交易时间，格式：yyyy-MM-dd'T'HH:mm:ss", example = "2025-05-24T10:00:00")
    private LocalDateTime transactionTime; // 交易时间

    @Schema(description = "交易状态，PENDING(交易中), SUCCESS(已成功), CANCELLED(已撤销)", example = "PENDING")
    private TransactionStatus transactionStatus; // 交易状态

    @Schema(description = "交易记录创建时间，格式：yyyy-MM-dd'T'HH:mm:ss", example = "2025-05-24T09:55:00")
    private LocalDateTime createTime; // 创建时间

    @Schema(description = "交易记录最后更新时间，格式：yyyy-MM-dd'T'HH:mm:ss", example = "2025-05-24T10:05:00")
    private LocalDateTime updateTime; // 更新时间

    /**
     * 将 Transaction 实体转换为 TransactionResponseVo 响应对象
     *
     * @param transaction 数据库实体对象
     * @return 返回封装后的响应对象
     */
    public static TransactionResponseVo transform(Transaction transaction) {
        TransactionResponseVo vo = new TransactionResponseVo();
        BeanUtils.copyProperties(transaction, vo);
        return vo;
    }
}