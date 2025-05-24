package com.financial.transaction.api.validator;

import com.financial.transaction.api.exceptions.ServiceException;
import com.financial.transaction.api.vo.TransactionCreateRequest;
import com.financial.transaction.api.vo.TransactionUpdateRequest;
import com.financial.transaction.common.enums.TransactionMethod;
import com.financial.transaction.common.enums.TransactionType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TransactionValidatorTest {

    @Test
    void testValidateTransactionRequest1() {
        // Setup
        final TransactionCreateRequest request = new TransactionCreateRequest();
        request.setId(0L);
        request.setAccountId("accountId");
        request.setTransactionType(TransactionType.STOCK);
        request.setTransactionMethod(TransactionMethod.BUY);
        request.setAmount(new BigDecimal("10.00"));

        // Run the test
        TransactionValidator.validateTransactionRequest(request);

        // Verify the results
    }

    @Test
    void testValidateTransactionRequest1_ThrowsServiceException() {
        // Setup
        final TransactionCreateRequest request = new TransactionCreateRequest();
        request.setId(0L);
        request.setAccountId("accountId");
        request.setTransactionType(TransactionType.STOCK);
        request.setTransactionMethod(TransactionMethod.BUY);
        request.setAmount(new BigDecimal("10.00"));

        // Run the test
        assertThrows(ServiceException.class, () -> TransactionValidator.validateTransactionRequest(request));
    }

    @Test
    void testValidateTransactionRequest2() {
        // Setup
        final TransactionUpdateRequest request = new TransactionUpdateRequest();
        request.setId(0L);
        request.setAccountId("accountId");
        request.setTransactionType(TransactionType.STOCK);
        request.setTransactionMethod(TransactionMethod.BUY);
        request.setAmount(new BigDecimal("10.00"));

        // Run the test
        TransactionValidator.validateTransactionRequest(request);

        // Verify the results
    }

    @Test
    void testValidateTransactionRequest2_ThrowsServiceException() {
        // Setup
        final TransactionUpdateRequest request = new TransactionUpdateRequest();
        request.setId(0L);
        request.setAccountId("accountId");
        request.setTransactionType(TransactionType.STOCK);
        request.setTransactionMethod(TransactionMethod.BUY);
        request.setAmount(new BigDecimal("10.00"));

        // Run the test
        assertThrows(ServiceException.class, () -> TransactionValidator.validateTransactionRequest(request));
    }

    @Test
    void testCheckPageParams() {
        // Setup
        // Run the test
        TransactionValidator.checkPageParams(10, 20);

        // Verify the results
    }

    @Test
    void testCheckPageParams_ThrowsServiceException() {
        // Setup
        // Run the test
        assertThrows(ServiceException.class, () -> TransactionValidator.checkPageParams(10, 20));
    }
}
