package com.financial.transaction.dao.repository.impl;

import com.financial.transaction.common.enums.TransactionMethod;
import com.financial.transaction.common.enums.TransactionStatus;
import com.financial.transaction.common.enums.TransactionType;
import com.financial.transaction.dao.memory.MemoryMapper;
import com.financial.transaction.dao.pojo.Transaction;
import com.financial.transaction.dao.repository.BaseDao;
import com.financial.transaction.dao.repository.TransactionDao;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

/**
 * TransactionDao的具体实现类，负责实现交易记录的查询功能。
 * 继承自BaseDao，并实现了TransactionDao接口。
 */
@Repository
public class TransactionDaoImpl extends BaseDao<Transaction, MemoryMapper<Transaction>>
        implements TransactionDao {

    /**
     * 根据多个筛选条件查询交易记录列表。
     *
     * @param accountId 用户账户ID，如果为空，则不按此字段筛选。
     * @param transactionType 交易类型，如果为空，则不按此字段筛选。
     * @param transactionStatus 交易状态，如果为空，则不按此字段筛选。
     * @param transactionMethod 交易方式，如果为空，则不按此字段筛选。
     * @param starTime 开始时间，如果为空，则不按此字段筛选。
     * @param endTime 结束时间，如果为空，则不按此字段筛选。
     * @return 筛选并排序后的交易记录列表。
     */
    @Override
    public List<Transaction> queryList(String accountId,
                                       TransactionType transactionType,
                                       TransactionStatus transactionStatus,
                                       TransactionMethod transactionMethod,
                                       LocalDateTime starTime,
                                       LocalDateTime endTime) {
        // 从内存映射中获取所有交易记录
        List<Transaction> list = memoryMapper.queryList();
        // 如果获取到的交易记录列表不为空，则进行筛选和排序操作
        if(!CollectionUtils.isEmpty(list)){
            // 根据传入的筛选条件过滤交易记录，并按交易时间排序
            List<Transaction> sortedList = list.stream().filter(transaction -> StringUtils.isEmpty(accountId) ||
                            transaction.getAccountId().equals(accountId))
                    .filter(transaction -> transactionType == null ||
                            transaction.getTransactionType() == transactionType)
                    .filter(transaction -> transactionStatus == null ||
                            transaction.getTransactionStatus() == transactionStatus)
                    .filter(transaction -> transactionType == null ||
                            transaction.getTransactionMethod() == transactionMethod)
                    .filter(transaction -> starTime == null ||
                            transaction.getTransactionTime().isAfter(starTime))
                    .filter(transaction -> endTime == null ||
                            transaction.getTransactionTime().isBefore(endTime))
                    .sorted(Comparator.comparing(Transaction::getTransactionTime))
                    .toList();
            return sortedList;
        }
        // 如果没有获取到交易记录，则直接返回原始列表
        return list;
    }
}
