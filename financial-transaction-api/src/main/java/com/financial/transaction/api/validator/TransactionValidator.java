package com.financial.transaction.api.validator;

import com.financial.transaction.api.enums.Status;
import com.financial.transaction.api.exceptions.ServiceException;
import com.financial.transaction.api.vo.TransactionCreateRequest;
import com.financial.transaction.api.vo.TransactionUpdateRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * TransactionValidator class is used for validating transaction related requests.
 */
@Component
public class TransactionValidator {
    /**
     * Validates the transaction creation request.
     *
     * @param request The transaction creation request object, containing the information needed for transaction creation.
     * @throws ServiceException If the request parameters are invalid, throw a service exception.
     */
    public static void validateTransactionRequest(TransactionCreateRequest request) {
        // Check if the transaction type is empty
        if (request.getTransactionType() == null) {
            throw new ServiceException(Status.REQUEST_PARAMS_NOT_VALID_ERROR, "Transaction type cannot be empty.");
        }
        // Check if the transaction amount is greater than zero
        if (request.getAmount() == null || request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ServiceException(Status.REQUEST_PARAMS_NOT_VALID_ERROR,"Transaction amount must be greater than zero.");
        }
        // Check if the transaction account ID is null
        if (request.getAccountId() == null || "".equals(request.getAccountId())) {
            throw new ServiceException(Status.REQUEST_PARAMS_NOT_VALID_ERROR, "Transaction account cannot be null.");
        }
    }

    /**
     * Validates the transaction update request.
     *
     * @param request The transaction update request object, containing the information needed for transaction updates.
     * @throws ServiceException If the request parameters are invalid, throw a service exception.
     */
    public static void validateTransactionRequest(TransactionUpdateRequest request) {
        // Check if the transaction type is empty
        if (request.getTransactionType() == null) {
            throw new ServiceException(Status.REQUEST_PARAMS_NOT_VALID_ERROR, "Transaction type cannot be empty.");
        }
        // Check if the transaction amount is greater than zero
        if (request.getAmount() == null || request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ServiceException(Status.REQUEST_PARAMS_NOT_VALID_ERROR,"Transaction amount must be greater than zero.");
        }
        // Check if the transaction account ID is null
        if (request.getAccountId() == null || "".equals(request.getAccountId())) {
            throw new ServiceException(Status.REQUEST_PARAMS_NOT_VALID_ERROR, "Transaction account cannot be null.");
        }
    }

    /**
     * check params
     *
     * @param pageNo   page number
     * @param pageSize page size
     * @throws ServiceException exception
     */
    public static void checkPageParams(int pageNo, int pageSize) throws ServiceException {
        // Check if the page number is greater than zero
        if (pageNo <= 0) {
            throw new ServiceException(Status.REQUEST_PARAMS_NOT_VALID_ERROR,  "PAGE_NUMBER");
        }
        // Check if the page size is greater than zero
        if (pageSize <= 0) {
            throw new ServiceException(Status.REQUEST_PARAMS_NOT_VALID_ERROR, "PAGE_SIZE");
        }
    }
}
