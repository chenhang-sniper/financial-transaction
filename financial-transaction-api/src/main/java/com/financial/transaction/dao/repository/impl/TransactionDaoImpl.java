package com.financial.transaction.dao.repository.impl;

import com.financial.transaction.api.dto.PageSearchDto;
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
     * 根据分页查询条件获取交易记录列表
     *
     * @param pageSearchDto 分页查询条件对象，包含分页信息及查询参数
     * @return 交易记录列表，满足查询条件的交易对象集合
     */
    @Override
    public List<Transaction> queryList(PageSearchDto pageSearchDto) {
        // 从内存映射中获取所有交易记录
        List<Transaction> list = memoryMapper.queryList();
        // 如果获取到的交易记录列表不为空，则进行筛选和排序操作
        if(!CollectionUtils.isEmpty(list)){
            // 根据传入的筛选条件过滤交易记录，并按交易时间排序
            List<Transaction> sortedList = list.stream().filter(transaction -> StringUtils.isEmpty(pageSearchDto.getAccountId()) ||
                            transaction.getAccountId().equals(pageSearchDto.getAccountId()))
                    .filter(transaction -> pageSearchDto.getTransactionType() == null ||
                            transaction.getTransactionType() ==  pageSearchDto.getTransactionType())
                    .filter(transaction ->  pageSearchDto.getTransactionStatus() == null ||
                            transaction.getTransactionStatus() ==  pageSearchDto.getTransactionStatus())
                    .filter(transaction -> pageSearchDto.getTransactionMethod() == null ||
                            transaction.getTransactionMethod() == pageSearchDto.getTransactionMethod())
                    .filter(transaction -> pageSearchDto.getStarTime() == null ||
                            transaction.getTransactionTime().isAfter(pageSearchDto.getStarTime()))
                    .filter(transaction -> pageSearchDto.getEndTime() == null ||
                            transaction.getTransactionTime().isBefore(pageSearchDto.getEndTime()))
                    .sorted(Comparator.comparing(Transaction::getTransactionTime))
                    .toList();
            return sortedList;
        }
        // 如果没有获取到交易记录，则直接返回原始列表
        return list;
    }
}
