package com.financial.transaction.dao.pojo;

import com.financial.transaction.common.enums.TransactionMethod;
import com.financial.transaction.common.enums.TransactionStatus;
import com.financial.transaction.common.enums.TransactionType;
import com.financial.transaction.dao.memory.MemoryEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 交易信息类，继承自MemoryEntity以利用内存存储
 */

public class Transaction extends MemoryEntity {
    private Long id; // 交易记录的唯一标识
    private String accountId; // 账户id，表示进行交易的账户
    private TransactionType transactionType; // 交易类型，用于区分不同类型的交易
    private TransactionMethod transactionMethod; // 交易方式，描述交易是如何进行的
    private BigDecimal amount; // 交易金额，表示交易中的货币量
    private LocalDateTime transactionTime; // 交易时间，记录交易发生的具体时间
    private TransactionStatus transactionStatus; // 交易状态，表示交易的当前状态

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
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

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    private LocalDateTime createTime; // 创建时间，记录交易记录被创建的时间
    private LocalDateTime updateTime; // 更新时间，记录交易记录最后一次更新的时间
}
