package com.financial.transaction.api.controller;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.financial.transaction.api.dto.PageSearchDto;
import com.financial.transaction.api.service.TransactionService;
import com.financial.transaction.api.vo.TransactionCreateRequest;
import com.financial.transaction.api.vo.TransactionUpdateRequest;
import com.financial.transaction.common.enums.TransactionMethod;
import com.financial.transaction.common.enums.TransactionStatus;
import com.financial.transaction.common.enums.TransactionType;
import com.financial.transaction.common.model.PageListingResult;
import com.financial.transaction.dao.pojo.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    // 创建 ObjectMapper 实例
    ObjectMapper objectMapper = new ObjectMapper();



    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService mockTransactionService;

    @Test
    void testCreateTransaction() throws Exception {
        // Setup
        // Run the test and verify the results
        // 将对象转换为 JSON 字符串
        String requestBody = objectMapper.writeValueAsString(new TransactionCreateRequest());
        mockMvc.perform(post("/api/v1/transactions")
                        .content(requestBody).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"code\": 1001}", false));

        // Confirm TransactionService.createTransaction(...).
        final Transaction transaction = new Transaction();
        transaction.setId(0L);
        transaction.setAccountId("accountId");
        transaction.setTransactionType(TransactionType.STOCK);
        transaction.setCreateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        transaction.setUpdateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        verify(mockTransactionService).createTransaction(transaction);
    }

    @Test
    void testDeleteTransaction() throws Exception {
        // Setup
        // Run the test and verify the results
        mockMvc.perform(delete("/api/v1/transactions/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"code\": 0}", false));
        verify(mockTransactionService).deleteTransaction(0L);
    }

    @Test
    void testUpdateTransaction() throws Exception {
        // Setup
        // Run the test and verify the results
        String requestBody = objectMapper.writeValueAsString(new TransactionUpdateRequest());
        mockMvc.perform(put("/api/v1/transactions/{id}", 0)
                        .content(requestBody).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"code\": 1001}", false));

        // Confirm TransactionService.updateTransaction(...).
        final Transaction transactionDetails = new Transaction();
        transactionDetails.setId(0L);
        transactionDetails.setAccountId("accountId");
        transactionDetails.setTransactionType(TransactionType.STOCK);
        transactionDetails.setCreateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        transactionDetails.setUpdateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        verify(mockTransactionService).updateTransaction(transactionDetails);
    }

    @Test
    void testFindTransaction() throws Exception {
        // Setup
        // Configure TransactionService.findTransaction(...).
        final Transaction transaction = new Transaction();
        transaction.setId(0L);
        transaction.setAccountId("accountId");
        transaction.setTransactionType(TransactionType.STOCK);
        transaction.setCreateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        transaction.setUpdateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockTransactionService.findTransaction(0L)).thenReturn(transaction);

        // Run the test and verify the results
        mockMvc.perform(get("/api/v1/transactions/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"code\": 0}", false));
    }

    @Test
    void testFindTransaction_TransactionServiceReturnsNull() throws Exception {
        // Setup
        when(mockTransactionService.findTransaction(0L)).thenReturn(null);

        // Run the test and verify the results
        mockMvc.perform(get("/api/v1/transactions/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"code\": 1002}", false));
    }

    @Test
    void testListPaging() throws Exception {
        // Setup
        // Configure TransactionService.listPaging(...).
        final Transaction transaction = new Transaction();
        transaction.setId(0L);
        transaction.setAccountId("accountId");
        transaction.setTransactionType(TransactionType.STOCK);
        transaction.setCreateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        transaction.setUpdateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final PageListingResult<Transaction> result1 = new PageListingResult<>(List.of(transaction), 0, 0, 0);
        final PageSearchDto pageSearchDto = new PageSearchDto();
        pageSearchDto.setAccountId("accountId");
        pageSearchDto.setTransactionType(TransactionType.STOCK);
        pageSearchDto.setTransactionStatus(TransactionStatus.PENDING);
        pageSearchDto.setTransactionMethod(TransactionMethod.BUY);
        pageSearchDto.setStarTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockTransactionService.listPaging("keyword", pageSearchDto)).thenReturn(result1);

        // Run the test and verify the results
        String requestBody = objectMapper.writeValueAsString(new TransactionCreateRequest());

        mockMvc.perform(post("/api/v1/transactions/list")
                        .content(requestBody).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"code\": 0}", false));
    }

    @Test
    void testListPaging_TransactionServiceReturnsNull() throws Exception {
        // Setup
        // Configure TransactionService.listPaging(...).
        final PageSearchDto pageSearchDto = new PageSearchDto();
        pageSearchDto.setAccountId("accountId");
        pageSearchDto.setTransactionType(TransactionType.STOCK);
        pageSearchDto.setTransactionStatus(TransactionStatus.PENDING);
        pageSearchDto.setTransactionMethod(TransactionMethod.BUY);
        pageSearchDto.setStarTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockTransactionService.listPaging("keyword", pageSearchDto)).thenReturn(null);

        String requestBody = objectMapper.writeValueAsString(new TransactionCreateRequest());

        // Run the test and verify the results
        mockMvc.perform(post("/api/v1/transactions/list")
                        .content(requestBody).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"code\": 0}", false));
    }
}
