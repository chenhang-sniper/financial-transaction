package com.financial.transaction.api.service;

import com.financial.transaction.api.dto.PageSearchDto;
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
     * 根据关键字和分页搜索条件获取交易列表
     *
     * @param keyword 关键字，用于模糊匹配交易的相关信息
     * @param pageSearchDto 分页搜索条件对象，包含分页大小、当前页码等信息
     * @return 返回一个包含交易记录的分页列表结果对象
     */
    PageListingResult<Transaction> listPaging(String keyword, PageSearchDto pageSearchDto);
}
