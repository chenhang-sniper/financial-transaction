package com.financial.transaction.api.validator;


import com.financial.transaction.api.enums.Status;
import com.financial.transaction.api.exceptions.ServiceException;
import com.financial.transaction.api.vo.TransactionCreateRequest;
import com.financial.transaction.api.vo.TransactionUpdateRequest;
import com.financial.transaction.common.enums.TransactionType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionValidatorTest {

    @Test
    public void testValidateTransactionRequest_Create_NullTransactionType_ShouldThrowServiceException() {
        TransactionCreateRequest request = new TransactionCreateRequest();
        request.setAmount(BigDecimal.ONE);
        request.setAccountId("1");

        ServiceException exception = assertThrows(ServiceException.class,
                () -> TransactionValidator.validateTransactionRequest(request));
        assertEquals(Status.REQUEST_PARAMS_NOT_VALID_ERROR, exception.getCode());
        assertTrue(exception.getMessage().contains("Transaction type cannot be empty."));
    }

    @Test
    public void testValidateTransactionRequest_Create_NullAmount_ShouldThrowServiceException() {
        TransactionCreateRequest request = new TransactionCreateRequest();
        request.setTransactionType(TransactionType.BOND);

        ServiceException exception = assertThrows(ServiceException.class,
                () -> TransactionValidator.validateTransactionRequest(request));
        assertEquals(Status.REQUEST_PARAMS_NOT_VALID_ERROR, exception.getCode());
        assertTrue(exception.getMessage().contains("Transaction amount must be greater than zero."));
    }

    @Test
    public void testValidateTransactionRequest_Create_ZeroAmount_ShouldThrowServiceException() {
        TransactionCreateRequest request = new TransactionCreateRequest();
                request.setTransactionType(TransactionType.BOND);
        request.setAmount(BigDecimal.ZERO);

        ServiceException exception = assertThrows(ServiceException.class,
                () -> TransactionValidator.validateTransactionRequest(request));
        assertEquals(Status.REQUEST_PARAMS_NOT_VALID_ERROR, exception.getCode());
        assertTrue(exception.getMessage().contains("Transaction amount must be greater than zero."));
    }

    @Test
    public void testValidateTransactionRequest_Create_NegativeAmount_ShouldThrowServiceException() {
        TransactionCreateRequest request = new TransactionCreateRequest();
                request.setTransactionType(TransactionType.BOND);
        request.setAmount(new BigDecimal("-100"));

        ServiceException exception = assertThrows(ServiceException.class,
                () -> TransactionValidator.validateTransactionRequest(request));
        assertEquals(Status.REQUEST_PARAMS_NOT_VALID_ERROR, exception.getCode());
        assertTrue(exception.getMessage().contains("Transaction amount must be greater than zero."));
    }

    @Test
    public void testValidateTransactionRequest_Create_NullAccountId_ShouldThrowServiceException() {
        TransactionCreateRequest request = new TransactionCreateRequest();
                request.setTransactionType(TransactionType.BOND);
        request.setAmount(BigDecimal.ONE);

        ServiceException exception = assertThrows(ServiceException.class,
                () -> TransactionValidator.validateTransactionRequest(request));
        assertEquals(Status.REQUEST_PARAMS_NOT_VALID_ERROR, exception.getCode());
        assertTrue(exception.getMessage().contains("Transaction account cannot be null."));
    }

    @Test
    public void testValidateTransactionRequest_Create_ValidRequest_ShouldNotThrowException() {
        TransactionCreateRequest request = new TransactionCreateRequest();
                request.setTransactionType(TransactionType.BOND);
        request.setAmount(new BigDecimal("100.00"));
        request.setAccountId("1");

        assertDoesNotThrow(() -> TransactionValidator.validateTransactionRequest(request));
    }

    // -----------------------------
    // 测试 validateTransactionRequest(TransactionUpdateRequest)
    // -----------------------------

    @Test
    public void testValidateTransactionRequest_Update_NullRequest_ShouldThrowNullPointerException() {
        assertThrows(NullPointerException.class, () -> TransactionValidator.validateTransactionRequest((TransactionUpdateRequest) null));
    }

    @Test
    public void testValidateTransactionRequest_Update_NullTransactionType_ShouldThrowServiceException() {
        TransactionUpdateRequest request = new TransactionUpdateRequest();
        request.setAmount(BigDecimal.ONE);
        request.setAccountId("1");

        ServiceException exception = assertThrows(ServiceException.class,
                () -> TransactionValidator.validateTransactionRequest(request));
        assertEquals(Status.REQUEST_PARAMS_NOT_VALID_ERROR, exception.getCode());
        assertTrue(exception.getMessage().contains("Transaction type cannot be empty."));
    }

    @Test
    public void testValidateTransactionRequest_Update_NullAmount_ShouldThrowServiceException() {
        TransactionUpdateRequest request = new TransactionUpdateRequest();
        request.setTransactionType(TransactionType.FOREX);

        ServiceException exception = assertThrows(ServiceException.class,
                () -> TransactionValidator.validateTransactionRequest(request));
        assertEquals(Status.REQUEST_PARAMS_NOT_VALID_ERROR, exception.getCode());
        assertTrue(exception.getMessage().contains("Transaction amount must be greater than zero."));
    }

    @Test
    public void testValidateTransactionRequest_Update_ZeroAmount_ShouldThrowServiceException() {
        TransactionUpdateRequest request = new TransactionUpdateRequest();
        request.setTransactionType(TransactionType.FUTURES);
        request.setAmount(BigDecimal.ZERO);

        ServiceException exception = assertThrows(ServiceException.class,
                () -> TransactionValidator.validateTransactionRequest(request));
        assertEquals(Status.REQUEST_PARAMS_NOT_VALID_ERROR, exception.getCode());
        assertTrue(exception.getMessage().contains("Transaction amount must be greater than zero."));
    }

    @Test
    public void testValidateTransactionRequest_Update_ValidRequest_ShouldNotThrowException() {
        TransactionUpdateRequest request = new TransactionUpdateRequest();
        request.setTransactionType(TransactionType.FUTURES);
        request.setAmount(new BigDecimal("200.00"));
        request.setAccountId("2");

        assertDoesNotThrow(() -> TransactionValidator.validateTransactionRequest(request));
    }

    // -----------------------------
    // 测试 checkPageParams(int pageNo, int pageSize)
    // -----------------------------

    @Test
    public void testCheckPageParams_PageNumberLessThanOrEqualToZero_ShouldThrowServiceException() {
        ServiceException exception = assertThrows(ServiceException.class,
                () -> TransactionValidator.checkPageParams(0, 10));
        assertEquals(Status.REQUEST_PARAMS_NOT_VALID_ERROR, exception.getCode());
        assertEquals("PAGE_NUMBER", exception.getMessage());
    }

    @Test
    public void testCheckPageParams_PageSizeLessThanOrEqualToZero_ShouldThrowServiceException() {
        ServiceException exception = assertThrows(ServiceException.class,
                () -> TransactionValidator.checkPageParams(1, 0));
        assertEquals(Status.REQUEST_PARAMS_NOT_VALID_ERROR, exception.getCode());
        assertEquals("PAGE_SIZE", exception.getMessage());
    }

    @Test
    public void testCheckPageParams_ValidParams_ShouldNotThrowException() {
        assertDoesNotThrow(() -> TransactionValidator.checkPageParams(1, 10));
    }
}