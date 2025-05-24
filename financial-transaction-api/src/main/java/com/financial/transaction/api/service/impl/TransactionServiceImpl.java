package com.financial.transaction.api.service.impl;

import com.financial.transaction.api.enums.Status;
import com.financial.transaction.api.exceptions.ServiceException;
import com.financial.transaction.api.service.TransactionService;
import com.financial.transaction.common.enums.TransactionMethod;
import com.financial.transaction.common.enums.TransactionStatus;
import com.financial.transaction.common.enums.TransactionType;
import com.financial.transaction.common.model.PageListingResult;
import com.financial.transaction.common.utils.SnowflakeIdGenerator;
import com.financial.transaction.dao.pojo.Transaction;
import com.financial.transaction.dao.repository.TransactionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * TransactionServiceImpl类实现了TransactionService接口，负责处理交易相关的业务逻辑。
 */
@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionDao transactionDao;
    // 读写锁
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    /**
     * 创建交易记录。
     * 如果交易ID已存在，则抛出异常。如果交易ID为空，则生成新的雪花ID。
     *
     * @param transaction 待创建的交易对象。
     * @throws ServiceException 如果交易ID已存在。
     */
    @CacheEvict(value = "transactions", allEntries = true)
    public void createTransaction(Transaction transaction) {
        if (transaction.getId() != null
            && transactionDao.existsById(transaction.getId())) {
            throw new ServiceException(Status.TRANSACTION_ALREADY_EXISTS,
                    "Transaction with ID " + transaction.getId() + " already exists.");
        }
        if(transaction.getId() == null){
            //创建雪花id
            SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator();
            transaction.setId(idGenerator.nextId());
        }
        transactionDao.insert(transaction);
    }

    /**
     * 删除交易记录。
     * 如果交易ID不存在，则抛出异常。
     *
     * @param id 待删除的交易记录的ID。
     * @throws ServiceException 如果交易ID不存在。
     */
    @CacheEvict(value = "transactions", allEntries = true)
    public void deleteTransaction(Long id) {
        if (!transactionDao.existsById(id)) {
            throw new ServiceException(Status.TRANSACTION_NOT_EXIST,
                    "Transaction with ID " + id + " not found.");
        }
        try {
            lock.writeLock().lock();
            transactionDao.deleteById(id);
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * 更新交易记录。
     * 如果交易ID不存在，则抛出异常。
     *
     * @param transactionDetails 包含更新信息的交易对象。
     * @throws ServiceException 如果交易ID不存在。
     */
    @Override
    @CacheEvict(value = "transactions", allEntries = true)
    public void updateTransaction(Transaction transactionDetails) {
        long id = transactionDetails.getId();
        Optional<Transaction> optional = transactionDao.queryOptionalById(id);
        if(optional.isEmpty()){
            throw new ServiceException(Status.TRANSACTION_NOT_EXIST,
                    "Transaction with ID " + id + " not found.");
        }
        Transaction old = optional.get();
        lock.writeLock().lock();
        try {
            transactionDetails.setCreateTime(old.getCreateTime());
            transactionDao.updateById(transactionDetails);
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * 根据ID查找交易记录。
     *
     * @param id 待查找的交易记录的ID。
     * @return 对应ID的交易记录，如果不存在则返回null。
     */
    @Override
    @Cacheable(value = "transactions", key = "#id")
    public Transaction findTransaction(Long id) {
        return transactionDao.queryById(id);
    }

    /**
     * 分页列出交易记录。
     * 根据多种条件筛选交易记录，并按照指定的页码和页面大小返回结果。
     *
     * @param accountId 账户ID。
     * @param transactionType 交易类型。
     * @param transactionStatus 交易状态。
     * @param transactionMethod 交易方式。
     * @param starTime 开始时间。
     * @param endTime 结束时间。
     * @param pageNo 页码。
     * @param pageSize 页面大小。
     * @return 包含交易记录的分页结果。
     */
    @Override
    @Cacheable(value = "transactions", key = "#accountId ?: 'null' + #transactionType ?: 'null' + #transactionStatus ?: 'null' + #transactionMethod ?: 'null' + #starTime ?: 'null' + #endTime ?: 'null' + #pageNo + #pageSize")
    public PageListingResult<Transaction> listPaging(String accountId,
                                                     TransactionType transactionType,
                                                     TransactionStatus transactionStatus,
                                                     TransactionMethod transactionMethod,
                                                     LocalDateTime starTime,
                                                     LocalDateTime endTime,
                                                     Integer pageNo,
                                                     Integer pageSize) {

        lock.readLock().lock();
        try {
            List<Transaction> transactionList = transactionDao.queryList(accountId, transactionType,
                    transactionStatus, transactionMethod, starTime, endTime);
            if(!CollectionUtils.isEmpty(transactionList)){
                int start = (pageNo - 1) * pageSize;
                int end = Math.min(start + pageSize, transactionList.size());
                List<Transaction> list = transactionList.subList(start, end);
                PageListingResult rs = PageListingResult.<Transaction>builder()
                        .totalCount(transactionList.size())
                        .currentPage(pageNo)
                        .pageSize(pageSize)
                        .records(list)
                        .build();
                return rs;
            }
            return null;
        } finally {
            lock.readLock().unlock();
        }

    }

    /**
     * 清除缓存的方法。
     */
    @CacheEvict(value = "transactions", allEntries = true)
    public void evictCache() {
    }

}
