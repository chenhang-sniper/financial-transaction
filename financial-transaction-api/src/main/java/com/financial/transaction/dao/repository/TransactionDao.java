package com.financial.transaction.dao.repository;

import java.util.List;
import java.time.LocalDateTime;
import com.financial.transaction.common.enums.TransactionMethod;
import com.financial.transaction.common.enums.TransactionStatus;
import com.financial.transaction.common.enums.TransactionType;
import com.financial.transaction.dao.pojo.Transaction;

/**
 * TransactionDao接口扩展了IDao接口，专门用于处理交易记录的数据访问层操作。
 * 它提供了一个基于多个条件查询交易记录的方法，以便于进行复杂的交易数据检索。
 */
public interface TransactionDao extends IDao<Transaction> {

    /**
     * 根据多个条件查询交易记录列表。
     *
     * @param accountId 账户ID，用于筛选特定账户的交易记录。
     * @param transactionType 交易类型，用于筛选特定类型的交易记录。
     * @param transactionStatus 交易状态，用于筛选处于特定状态的交易记录。
     * @param transactionMethod 交易方式，用于筛选采用特定方式的交易记录。
     * @param starTime 查询的开始时间，用于筛选在此时间之后的交易记录。
     * @param endTime 查询的结束时间，用于筛选在此时间之前的交易记录。
     * @return 返回符合筛选条件的交易记录列表。
     */
    List<Transaction> queryList(String accountId, TransactionType transactionType, TransactionStatus transactionStatus, TransactionMethod transactionMethod, LocalDateTime starTime, LocalDateTime endTime);
}
