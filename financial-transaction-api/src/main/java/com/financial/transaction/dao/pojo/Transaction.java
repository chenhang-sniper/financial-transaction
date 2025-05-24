package com.financial.transaction.dao.pojo;

import com.financial.transaction.common.enums.TransactionMethod;
import com.financial.transaction.common.enums.TransactionStatus;
import com.financial.transaction.common.enums.TransactionType;
import com.financial.transaction.dao.memory.MemoryEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 交易信息类，继承自MemoryEntity以利用内存存储
 */
@Data
public class Transaction extends MemoryEntity {
    private Long id; // 交易记录的唯一标识
    private String accountId; // 账户id，表示进行交易的账户
    private TransactionType transactionType; // 交易类型，用于区分不同类型的交易
    private TransactionMethod transactionMethod; // 交易方式，描述交易是如何进行的
    private BigDecimal amount; // 交易金额，表示交易中的货币量
    private LocalDateTime transactionTime; // 交易时间，记录交易发生的具体时间
    private TransactionStatus transactionStatus; // 交易状态，表示交易的当前状态
    private LocalDateTime createTime; // 创建时间，记录交易记录被创建的时间
    private LocalDateTime updateTime; // 更新时间，记录交易记录最后一次更新的时间
}
