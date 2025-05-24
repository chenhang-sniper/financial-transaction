package com.financial.transaction.api.service;

import com.financial.transaction.common.enums.TransactionMethod;
import com.financial.transaction.common.enums.TransactionStatus;
import com.financial.transaction.common.enums.TransactionType;
import com.financial.transaction.common.model.PageListingResult;
import com.financial.transaction.dao.pojo.Transaction;
import java.time.LocalDateTime;

/**
 * TransactionService接口定义了对交易记录进行 CRUD 操作及分页查询的方法
 */
public interface TransactionService {

    /**
     * 创建一个新的交易记录
     *
     * @param transaction 交易记录对象，包含交易的所有详细信息
     */
    void createTransaction(Transaction transaction);

    /**
     * 删除指定ID的交易记录
     *
     * @param id 要删除的交易记录的ID
     */
    void deleteTransaction(Long id);

    /**
     * 更新交易记录的详细信息
     *
     * @param transactionDetails 包含更新信息的交易记录对象
     */
    void updateTransaction(Transaction transactionDetails);

    /**
     * 查找指定ID的交易记录
     *
     * @param id 要查找的交易记录的ID
     * @return 返回找到的交易记录对象
     */
    Transaction findTransaction(Long id);

    /**
     * 分页列表查询交易记录
     *
     * @param accountId 账户ID，用于筛选交易记录
     * @param transactionType 交易类型，用于筛选交易记录
     * @param transactionStatus 交易状态，用于筛选交易记录
     * @param transactionMethod 交易方式，用于筛选交易记录
     * @param starTime 开始时间，用于筛选交易记录
     * @param endTime 结束时间，用于筛选交易记录
     * @param pageNo 页码，用于指定查询的页数
     * @param pageSize 每页大小，用于指定每页的记录数
     * @return 返回分页后的交易记录列表
     */
    PageListingResult<Transaction> listPaging(String accountId, TransactionType transactionType,
                                              TransactionStatus transactionStatus, TransactionMethod transactionMethod,
                                              LocalDateTime starTime, LocalDateTime endTime,
                                              Integer pageNo, Integer pageSize);
}
