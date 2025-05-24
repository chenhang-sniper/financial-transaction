package com.financial.transaction.api.vo;

import com.financial.transaction.common.enums.TransactionMethod;
import com.financial.transaction.common.enums.TransactionStatus;
import com.financial.transaction.common.enums.TransactionType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


import java.time.LocalDateTime;

@Data
public class TransactionSearchRequest {
    @Schema(description = "账户ID，用于筛选特定账户的交易记录", example = "ACC123456")
    private String accountId; // 账户ID

    @Schema(description = "交易类型，例如 STOCK(股票), BOND(债券) 等", example = "STOCK")
    private TransactionType transactionType; // 交易类型

    @Schema(description = "交易方式，BUY(买入) 或 SELL(卖出)", example = "BUY")
    private TransactionMethod transactionMethod; // 交易方式

    @Schema(description = "交易开始时间，格式：yyyy-MM-dd'T'HH:mm:ss", example = "2025-01-01T00:00:00")
    private LocalDateTime starTime; // 交易开始时间

    @Schema(description = "交易结束时间，格式：yyyy-MM-dd'T'HH:mm:ss", example = "2025-12-31T23:59:59")
    private LocalDateTime endTime; // 交易结束时间

    @Schema(description = "交易状态，PENDING(交易中), SUCCESS(已成功), CANCELLED(已撤销)", example = "PENDING")
    private TransactionStatus transactionStatus; // 交易状态

    @Schema(description = "当前页码，默认为1", example = "1")
    private Integer pageNo = 1; // 当前页码

    @Schema(description = "每页大小，默认为10", example = "10")
    private Integer pageSize = 10; // 每页大小
}