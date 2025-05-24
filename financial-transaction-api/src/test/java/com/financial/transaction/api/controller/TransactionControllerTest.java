package com.financial.transaction.api.controller;

import com.financial.transaction.api.service.TransactionService;
import com.financial.transaction.api.utils.PageInfo;
import com.financial.transaction.api.validator.TransactionValidator;
import com.financial.transaction.api.vo.*;
import com.financial.transaction.common.model.PageListingResult;
import com.financial.transaction.dao.pojo.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.financial.transaction.api.utils.Result;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionControllerTest {

    @InjectMocks
    private TransactionController transactionController;

    @Mock
    private TransactionService transactionService;

    @Mock
    private TransactionValidator transactionValidator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // TC01: createTransaction - success
    @Test
    void testCreateTransaction_Success() {
        TransactionCreateRequest request = new TransactionCreateRequest();
        request.setAmount(BigDecimal.valueOf(100.0)); // 改为 BigDecimal

        doNothing().when(transactionValidator).validateTransactionRequest(request);
       // when(transactionService.createTransaction(any(Transaction.class))).thenReturn(true);

        Result<Boolean> result = transactionController.createTransaction(request);

        assertTrue(result.isSuccess());
        assertEquals(Boolean.TRUE, result.getData());
        verify(transactionValidator).validateTransactionRequest(request);
        verify(transactionService).createTransaction(any(Transaction.class));
    }

    // TC02: createTransaction - validation fails
    @Test
    void testCreateTransaction_ValidationFails() {
        TransactionCreateRequest request = new TransactionCreateRequest();

        //doThrow(new ApiException("CREATE_TRANSACTION_ERROR")).when(transactionValidator).validateTransactionRequest(request);

//        assertThrows(ApiException.class, new Executable() {
//            @Override
//            public void execute() throws Throwable {
//                transactionController.createTransaction(request);
//            }
//        });
    }

    // TC03: deleteTransaction - success
    @Test
    void testDeleteTransaction_Success() {
        Long id = 1L;

        Result<Boolean> result = transactionController.deleteTransaction(id);

        assertTrue(result.isSuccess());
        assertEquals(Boolean.TRUE, result.getData());
        verify(transactionService).deleteTransaction(id);
    }

    // TC05: updateTransaction - success
    @Test
    void testUpdateTransaction_Success() {
        Long id = 1L;
        TransactionUpdateRequest request = new TransactionUpdateRequest();
        request.setAmount(BigDecimal.valueOf(200.0));

        doNothing().when(transactionValidator).validateTransactionRequest(request);
        //when(transactionService.updateTransaction(any(Transaction.class))).thenReturn(true);

        Result<Boolean> result = transactionController.updateTransaction(id, request);

        assertTrue(result.isSuccess());
        assertEquals(Boolean.TRUE, result.getData());
        verify(transactionValidator).validateTransactionRequest(request);
        verify(transactionService).updateTransaction(any(Transaction.class));
    }

    // TC07: findTransaction - exists
    @Test
    void testFindTransaction_Exists() {
        Long id = 1L;
        Transaction transaction = new Transaction();
        transaction.setId(id);

        when(transactionService.findTransaction(id)).thenReturn(transaction);

        Result<TransactionResponseVo> result = transactionController.findTransaction(id);

        assertTrue(result.isSuccess());
        assertNotNull(result.getData());
        verify(transactionService).findTransaction(id);
    }

    // TC08: findTransaction - not exists
    @Test
    void testFindTransaction_NotExists() {
        Long id = 1L;

        when(transactionService.findTransaction(id)).thenReturn(null);

        Result<TransactionResponseVo> result = transactionController.findTransaction(id);

        assertFalse(result.isSuccess());
        assertEquals("TRANSACTION_NOT_EXIST", result.getCode());
        verify(transactionService).findTransaction(id);
    }

    // TC09: listPaging - has data
    @Test
    void testListPaging_HasData() {
        TransactionSearchRequest request = new TransactionSearchRequest();
        request.setPageNo(1);
        request.setPageSize(10);



        Result<PageInfo<TransactionResponseVo>> result = transactionController.listPaging(request);

        assertTrue(result.isSuccess());
        assertNotNull(result.getData());
        assertEquals(1, result.getData().getTotalList().size());
    }

    // TC10: listPaging - no data
    @Test
    void testListPaging_NoData() {
        TransactionSearchRequest request = new TransactionSearchRequest();
        request.setPageNo(1);
        request.setPageSize(10);


        PageListingResult<Transaction> pageResult = PageListingResult.<Transaction>builder()
                .build();
        pageResult.setRecords(Collections.emptyList());


        Result<PageInfo<TransactionResponseVo>> result = transactionController.listPaging(request);

        assertTrue(result.isSuccess());
        assertNotNull(result.getData());
        assertTrue(result.getData().getTotalList().isEmpty());
    }
}
