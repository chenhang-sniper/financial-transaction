package com.financial.transaction.api.service.impl;

import com.financial.transaction.api.dto.PageSearchDto;
import com.financial.transaction.api.exceptions.ServiceException;
import com.financial.transaction.common.enums.TransactionMethod;
import com.financial.transaction.common.enums.TransactionStatus;
import com.financial.transaction.common.enums.TransactionType;
import com.financial.transaction.common.model.PageListingResult;
import com.financial.transaction.dao.pojo.Transaction;
import com.financial.transaction.dao.repository.TransactionDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class TransactionServiceImplTest {

    @Mock
    private TransactionDao mockTransactionDao;
    @Mock
    private ReentrantReadWriteLock mockLock;

    @InjectMocks
    private TransactionServiceImpl transactionServiceImplUnderTest;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    void setUp() {
        mockitoCloseable = openMocks(this);
        // TODO: Set the following fields: lock.
    }

    @AfterEach
    void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    void testCreateTransaction() {
        // Setup
        final Transaction transaction = new Transaction();
        transaction.setCreateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        transaction.setId(0L);
        transaction.setAccountId("accountId");
        transaction.setTransactionType(TransactionType.STOCK);
        transaction.setTransactionMethod(TransactionMethod.BUY);

        when(mockTransactionDao.existsById(0L)).thenReturn(false);

        // Run the test
        transactionServiceImplUnderTest.createTransaction(transaction);

        // Verify the results
        verify(mockTransactionDao).insert(any(Transaction.class));
    }

    @Test
    void testCreateTransaction_TransactionDaoExistsByIdReturnsTrue() {
        // Setup
        final Transaction transaction = new Transaction();
        transaction.setCreateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        transaction.setId(0L);
        transaction.setAccountId("accountId");
        transaction.setTransactionType(TransactionType.STOCK);
        transaction.setTransactionMethod(TransactionMethod.BUY);

        when(mockTransactionDao.existsById(0L)).thenReturn(true);

        // Run the test
        assertThrows(ServiceException.class, () -> transactionServiceImplUnderTest.createTransaction(transaction));
    }

    @Test
    void testDeleteTransaction() {
        // Setup
        when(mockTransactionDao.existsById(0L)).thenReturn(true);
        when(mockLock.writeLock()).thenReturn(null);

        // Run the test
        transactionServiceImplUnderTest.deleteTransaction(0L);

        // Verify the results
        verify(mockTransactionDao).deleteById(0L);
    }

    @Test
    void testDeleteTransaction_TransactionDaoExistsByIdReturnsFalse() {
        // Setup
        when(mockTransactionDao.existsById(0L)).thenReturn(false);

        // Run the test
        assertThrows(ServiceException.class, () -> transactionServiceImplUnderTest.deleteTransaction(0L));
    }

    @Test
    void testUpdateTransaction() {
        // Setup
        final Transaction transactionDetails = new Transaction();
        transactionDetails.setCreateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        transactionDetails.setId(0L);
        transactionDetails.setAccountId("accountId");
        transactionDetails.setTransactionType(TransactionType.STOCK);
        transactionDetails.setTransactionMethod(TransactionMethod.BUY);

        // Configure TransactionDao.queryOptionalById(...).
        final Transaction transaction = new Transaction();
        transaction.setCreateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        transaction.setId(0L);
        transaction.setAccountId("accountId");
        transaction.setTransactionType(TransactionType.STOCK);
        transaction.setTransactionMethod(TransactionMethod.BUY);
        final Optional<Transaction> optional = Optional.of(transaction);
        when(mockTransactionDao.queryOptionalById(0L)).thenReturn(optional);

        when(mockLock.writeLock()).thenReturn(null);

        // Run the test
        transactionServiceImplUnderTest.updateTransaction(transactionDetails);

        // Verify the results
        verify(mockTransactionDao).updateById(any(Transaction.class));
    }

    @Test
    void testUpdateTransaction_TransactionDaoQueryOptionalByIdReturnsAbsent() {
        // Setup
        final Transaction transactionDetails = new Transaction();
        transactionDetails.setCreateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        transactionDetails.setId(0L);
        transactionDetails.setAccountId("accountId");
        transactionDetails.setTransactionType(TransactionType.STOCK);
        transactionDetails.setTransactionMethod(TransactionMethod.BUY);

        when(mockTransactionDao.queryOptionalById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThrows(ServiceException.class,
                () -> transactionServiceImplUnderTest.updateTransaction(transactionDetails));
    }

    @Test
    void testFindTransaction() {
        // Setup
        // Configure TransactionDao.queryById(...).
        final Transaction transaction = new Transaction();
        transaction.setCreateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        transaction.setId(0L);
        transaction.setAccountId("accountId");
        transaction.setTransactionType(TransactionType.STOCK);
        transaction.setTransactionMethod(TransactionMethod.BUY);
        when(mockTransactionDao.queryById(0L)).thenReturn(transaction);

        // Run the test
        final Transaction result = transactionServiceImplUnderTest.findTransaction(0L);

        // Verify the results
    }

    @Test
    void testListPaging() {
        // Setup
        final PageSearchDto pageSearchDto = new PageSearchDto();
        pageSearchDto.setAccountId("accountId");
        pageSearchDto.setTransactionType(TransactionType.STOCK);
        pageSearchDto.setTransactionStatus(TransactionStatus.PENDING);
        pageSearchDto.setPageNo(0);
        pageSearchDto.setPageSize(0);

        when(mockLock.readLock()).thenReturn(null);

        // Configure TransactionDao.queryList(...).
        final Transaction transaction = new Transaction();
        transaction.setCreateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        transaction.setId(0L);
        transaction.setAccountId("accountId");
        transaction.setTransactionType(TransactionType.STOCK);
        transaction.setTransactionMethod(TransactionMethod.BUY);
        final List<Transaction> transactionList = List.of(transaction);
        final PageSearchDto pageSearchDto1 = new PageSearchDto();
        pageSearchDto1.setAccountId("accountId");
        pageSearchDto1.setTransactionType(TransactionType.STOCK);
        pageSearchDto1.setTransactionStatus(TransactionStatus.PENDING);
        pageSearchDto1.setPageNo(0);
        pageSearchDto1.setPageSize(0);
        when(mockTransactionDao.queryList(pageSearchDto1)).thenReturn(transactionList);

        // Run the test
        final PageListingResult<Transaction> result = transactionServiceImplUnderTest.listPaging("keyword",
                pageSearchDto);

        // Verify the results
    }

    @Test
    void testListPaging_TransactionDaoReturnsNoItems() {
        // Setup
        final PageSearchDto pageSearchDto = new PageSearchDto();
        pageSearchDto.setAccountId("accountId");
        pageSearchDto.setTransactionType(TransactionType.STOCK);
        pageSearchDto.setTransactionStatus(TransactionStatus.PENDING);
        pageSearchDto.setPageNo(0);
        pageSearchDto.setPageSize(0);

        when(mockLock.readLock()).thenReturn(null);

        // Configure TransactionDao.queryList(...).
        final PageSearchDto pageSearchDto1 = new PageSearchDto();
        pageSearchDto1.setAccountId("accountId");
        pageSearchDto1.setTransactionType(TransactionType.STOCK);
        pageSearchDto1.setTransactionStatus(TransactionStatus.PENDING);
        pageSearchDto1.setPageNo(0);
        pageSearchDto1.setPageSize(0);
        when(mockTransactionDao.queryList(pageSearchDto1)).thenReturn(Collections.emptyList());

        // Run the test
        final PageListingResult<Transaction> result = transactionServiceImplUnderTest.listPaging("keyword",
                pageSearchDto);

        // Verify the results
        assertNull(result);
    }

    @Test
    void testEvictCache() {
        transactionServiceImplUnderTest.evictCache();
    }
}
