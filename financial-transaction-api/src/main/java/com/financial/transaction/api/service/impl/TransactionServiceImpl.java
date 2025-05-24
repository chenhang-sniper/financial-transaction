package com.financial.transaction.api.service.impl;

import com.financial.transaction.api.dto.PageSearchDto;
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
     * 根据关键字和分页搜索条件列出交易记录
     * 该方法使用Spring的缓存机制，将结果缓存到名为"transactions"的缓存中，key为keyword
     * 这样，下次请求相同keyword时，将直接从缓存中获取数据，而不是重新执行方法
     *
     * @param keyword 关键字，用于缓存的唯一标识
     * @param pageSearchDto 分页搜索条件，包含页码和页面大小
     * @return 返回一个分页结果对象，包含交易记录列表和分页信息
     */
    @Override
    @Cacheable(value = "transactions", key = "#keyword")
    public PageListingResult<Transaction> listPaging(String keyword, PageSearchDto pageSearchDto) {
        // 获取读锁，以确保线程安全在读取操作中
        lock.readLock().lock();
        try {
            // 获取分页参数
            int pageNo = pageSearchDto.getPageNo();
            int pageSize = pageSearchDto.getPageSize();
            // 查询交易记录列表
            List<Transaction> transactionList = transactionDao.queryList(pageSearchDto);
            if(!CollectionUtils.isEmpty(transactionList)){
                // 计算分页的起始和结束索引
                int start = (pageNo - 1) * pageSize;
                int end = Math.min(start + pageSize, transactionList.size());
                // 提取当前页的交易记录列表
                List<Transaction> list = transactionList.subList(start, end);
                // 构建并返回分页结果对象
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
            // 释放读锁
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
