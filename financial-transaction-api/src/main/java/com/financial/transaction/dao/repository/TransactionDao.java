package com.financial.transaction.dao.repository;

import java.util.List;
import java.time.LocalDateTime;

import com.financial.transaction.api.dto.PageSearchDto;
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
     * 根据分页查询条件获取交易记录列表
     *
     * @param pageSearchDto 分页查询条件对象，包含分页信息及查询参数
     * @return 交易记录列表，满足查询条件的交易对象集合
     */
    List<Transaction> queryList(PageSearchDto pageSearchDto);
}
