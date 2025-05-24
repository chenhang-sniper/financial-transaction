package com.financial.transaction.api.controller;

import com.financial.transaction.api.utils.PageInfo;
import com.financial.transaction.api.vo.TransactionCreateRequest;
import com.financial.transaction.api.vo.TransactionResponseVo;
import com.financial.transaction.api.vo.TransactionSearchRequest;
import com.financial.transaction.api.vo.TransactionUpdateRequest;
import com.financial.transaction.api.exceptions.ApiException;
import com.financial.transaction.api.utils.Result;
import com.financial.transaction.api.validator.TransactionValidator;
import com.financial.transaction.common.model.PageListingResult;
import com.financial.transaction.dao.pojo.Transaction;
import com.financial.transaction.api.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.financial.transaction.api.enums.Status.*;

@RestController
@RequestMapping("/api/v1/transactions")
@Tag(name = "Transaction Management", description = "APIs for managing transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    /**
     * Create a new transaction.
     *
     * @param request The request object containing the information needed for the transaction.
     * @return Returns the result of the operation.
     */
    @Operation(summary = "createTransaction", description = "Create a new transaction")
    @ResponseStatus(HttpStatus.OK)
    @ApiException(CREATE_TRANSACTION_ERROR)
    @PostMapping
    public Result<Boolean> createTransaction(@RequestBody TransactionCreateRequest request) {
        TransactionValidator.validateTransactionRequest(request);
        Transaction transaction = new Transaction();
        BeanUtils.copyProperties(request, transaction);
        // Set the creation time
        transaction.setCreateTime(LocalDateTime.now());
        transactionService.createTransaction(transaction);
        return Result.success(true);
    }

    /**
     * Delete a transaction by ID.
     *
     * @param id The ID of the transaction to be deleted.
     * @return Returns the result of the operation.
     */
    @Operation(summary = "deleteTransaction", description = "Delete a transaction by ID")
    @ResponseStatus(HttpStatus.OK)
    @ApiException(DELETE_TRANSACTION_ERROR)
    @DeleteMapping("/{id}")
    public Result<Boolean> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return Result.success(true);
    }

    /**
     * Update a transaction by ID.
     *
     * @param id The ID of the transaction to be updated.
     * @param request The request object containing the updated transaction information.
     * @return Returns the result of the operation.
     */
    @Operation(summary = "updateTransaction", description = "Update a transaction by ID")
    @ResponseStatus(HttpStatus.OK)
    @ApiException(UPDATE_TRANSACTION_ERROR)
    @PutMapping("/{id}")
    public Result<Boolean> updateTransaction(@PathVariable Long id, @RequestBody TransactionUpdateRequest request) {
        TransactionValidator.validateTransactionRequest(request);
        Transaction transaction = new Transaction();
        BeanUtils.copyProperties(request, transaction);
        // Set the update time
        transaction.setUpdateTime(LocalDateTime.now());
        transactionService.updateTransaction(transaction);
        return Result.success(true);
    }

    /**
     * Find and display the details of a transaction by ID.
     *
     * @param id The ID of the transaction to be found.
     * @return Returns the detailed information of the transaction.
     */
    @Operation(summary = "findTransaction", description = "Find a transaction by ID and show details")
    @ResponseStatus(HttpStatus.OK)
    @ApiException(FIND_TRANSACTION_ERROR)
    @GetMapping("/{id}")
    public Result<TransactionResponseVo> findTransaction(@PathVariable Long id) {
        Transaction transaction = transactionService.findTransaction(id);
        if(transaction == null){
            return Result.errorWithArgs(TRANSACTION_NOT_EXIST, String.valueOf(id));
        }
        TransactionResponseVo transactionResponseVo = TransactionResponseVo.transform(transaction);
        return Result.success(transactionResponseVo);
    }

    /**
     * Query the transaction list with pagination.
     *
     * @param request The request object containing the query conditions and pagination information.
     * @return Returns the query result with pagination.
     */
    @Operation(summary = "queryTransactionListPaging", description = "Query transaction list with pagination")
    @PostMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    @ApiException(FIND_TRANSACTION_LIST_ERROR)
    public Result<PageInfo<TransactionResponseVo>> listPaging(@RequestBody TransactionSearchRequest request) {
        TransactionValidator.checkPageParams(request.getPageNo(), request.getPageSize());
        PageListingResult<Transaction> result = transactionService.listPaging(request.getAccountId(), request.getTransactionType(), request.getTransactionStatus(), request.getTransactionMethod(),
                request.getStarTime(), request.getEndTime(),
                request.getPageNo(), request.getPageSize());
        PageInfo resp = new PageInfo(request.getPageNo(), request.getPageSize());
        if(result == null || CollectionUtils.isEmpty(result.getRecords())){
            return Result.success(resp);
        }
        List<TransactionResponseVo> list = result.getRecords().stream()
                .map(TransactionResponseVo::transform).toList();
        resp.setTotal(result.getTotalCount());
        resp.setTotalList(list);
        resp.setCurrentPage(resp.getPageNo());
        resp.setPageNo(resp.getPageNo());
        resp.setTotalPage(resp.getTotalPage());
        resp.setPageSize(request.getPageSize());
        return Result.success(resp);
    }
}
