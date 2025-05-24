package com.financial.transaction.dao.repository.impl;

import com.financial.transaction.api.dto.PageSearchDto;
import com.financial.transaction.common.enums.TransactionMethod;
import com.financial.transaction.common.enums.TransactionStatus;
import com.financial.transaction.common.enums.TransactionType;
import com.financial.transaction.dao.pojo.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

class TransactionDaoImplTest {

    private TransactionDaoImpl transactionDaoImplUnderTest;

    @BeforeEach
    void setUp() {
        transactionDaoImplUnderTest = new TransactionDaoImpl();
    }

    @Test
    void testQueryList() {
        // Setup
        final PageSearchDto pageSearchDto = new PageSearchDto();
        pageSearchDto.setAccountId("accountId");
        pageSearchDto.setTransactionType(TransactionType.STOCK);
        pageSearchDto.setTransactionStatus(TransactionStatus.PENDING);
        pageSearchDto.setTransactionMethod(TransactionMethod.BUY);
        pageSearchDto.setStarTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        pageSearchDto.setEndTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        // Run the test
        final List<Transaction> result = transactionDaoImplUnderTest.queryList(pageSearchDto);

        // Verify the results
    }
}
