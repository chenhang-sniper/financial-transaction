package com.financial.transaction.api.service;


import com.financial.transaction.api.service.impl.TransactionServiceImpl;
import com.financial.transaction.common.enums.TransactionMethod;
import com.financial.transaction.common.enums.TransactionStatus;
import com.financial.transaction.common.enums.TransactionType;
import com.financial.transaction.common.model.PageListingResult;
import com.financial.transaction.dao.pojo.Transaction;
import com.financial.transaction.dao.repository.TransactionDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * TransactionService Implementation Unit Test
 */
public class TransactionServiceImplTest {

    @Mock
    private TransactionDao transactionDao;

    @InjectMocks
    private TransactionService transactionService = new TransactionServiceImpl();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test createTransaction with valid input
     */
    @Test
    public void testCreateTransaction_validInput_success() {
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setAccountId("A001");
        transaction.setAmount(BigDecimal.valueOf(100.0));

        transactionService.createTransaction(transaction);

        verify(transactionDao, times(1)).insert(transaction);
    }

    /**
     * Test createTransaction with null input
     */
    @Test
    public void testCreateTransaction_nullInput_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            transactionService.createTransaction(null);
        });
    }

    /**
     * Test deleteTransaction with valid ID
     */
    @Test
    public void testDeleteTransaction_validId_success() {
        Long id = 1L;
        when(transactionDao.existsById(id)).thenReturn(true);

        transactionService.deleteTransaction(id);

        verify(transactionDao, times(1)).deleteById(id);
    }

    /**
     * Test deleteTransaction with invalid ID
     */
    @Test
    public void testDeleteTransaction_invalidId_throwsException() {
        Long id = null;

        assertThrows(IllegalArgumentException.class, () -> {
            transactionService.deleteTransaction(id);
        });
    }

    /**
     * Test updateTransaction with valid data
     */
    @Test
    public void testUpdateTransaction_validData_success() {
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setAccountId("A001");

        when(transactionDao.existsById(transaction.getId())).thenReturn(true);

        transactionService.updateTransaction(transaction);

        verify(transactionDao, times(1)).updateById(transaction);
    }

    /**
     * Test updateTransaction with non-existent ID
     */
    @Test
    public void testUpdateTransaction_nonExistentId_throwsException() {
        Transaction transaction = new Transaction();
        transaction.setId(999L);

        when(transactionDao.existsById(transaction.getId())).thenReturn(false);

        assertThrows(IllegalStateException.class, () -> {
            transactionService.updateTransaction(transaction);
        });
    }

    /**
     * Test findTransaction with existing ID
     */
    @Test
    public void testFindTransaction_existingId_returnsTransaction() {
        Long id = 1L;
        Transaction expected = new Transaction();
        expected.setId(id);
        expected.setAccountId("A001");

        when(transactionDao.queryOptionalById(id)).thenReturn(Optional.of(expected));

        Transaction result = transactionService.findTransaction(id);

        assertNotNull(result);
        assertEquals(expected.getId(), result.getId());
    }

    /**
     * Test findTransaction with non-existing ID
     */
    @Test
    public void testFindTransaction_nonExistingId_returnsNull() {
        Long id = 999L;
        when(transactionDao.queryOptionalById(id)).thenReturn(Optional.empty());

        Transaction result = transactionService.findTransaction(id);

        assertNull(result);
    }

    /**
     * Test listPaging with valid filters and pagination
     */
    @Test
    public void testListPaging_validFilters_returnsPageResult() {
        String accountId = "A001";
        TransactionType type = TransactionType.BOND;
        TransactionStatus status = TransactionStatus.SUCCESS;
        TransactionMethod method = TransactionMethod.BUY;
        LocalDateTime startTime = LocalDateTime.now().minusDays(7);
        LocalDateTime endTime = LocalDateTime.now();
        Integer pageNo = 1;
        Integer pageSize = 10;

        PageListingResult<Transaction> result = transactionService.listPaging(
                accountId, type, status, method, startTime, endTime, pageNo, pageSize);

        assertNotNull(result);
        assertEquals(2, result.getRecords().size());
    }

}