package com.financial.transaction.api.enums;

import java.util.Locale;
import java.util.Optional;

import org.springframework.context.i18n.LocaleContextHolder;

/**
 * status enum
 */
public enum Status {

    SUCCESS(0, "success", "成功"),
    INTERNAL_SERVER_ERROR(1000, "Internal Server Error: {0}", "服务端异常: {0}"),
    REQUEST_PARAMS_NOT_VALID_ERROR(1001, "request parameter {0} is not valid", "请求参数无效[{0}]"),
    TRANSACTION_NOT_EXIST(1002, "Transaction [{0}] not exists", "交易流水不存在:{0}"),
    TRANSACTION_ALREADY_EXISTS(1003, "Transaction already exists: {0}", "交易流水重复提交: {0}"),

    CREATE_TRANSACTION_ERROR(1004, "Create transaction error", "创建交易失败"),
    UPDATE_TRANSACTION_ERROR(1005, "Update transaction error", "更新交易失败"),
    DELETE_TRANSACTION_ERROR(1006, "Delete transaction error", "删除交易失败"),
    FIND_TRANSACTION_ERROR(1007, "Find transaction error", "查找交易失败"),
    FIND_TRANSACTION_LIST_ERROR(1008, "Find transaction list error", "查找交易列表失败"),



    ;

    private final int code;
    private final String enMsg;
    private final String zhMsg;

    Status(int code, String enMsg, String zhMsg) {
        this.code = code;
        this.enMsg = enMsg;
        this.zhMsg = zhMsg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        if (Locale.SIMPLIFIED_CHINESE.getLanguage().equals(LocaleContextHolder.getLocale().getLanguage())) {
            return this.zhMsg;
        } else {
            return this.enMsg;
        }
    }

    /**
     * Retrieve Status enum entity by status code.
     */
    public static Optional<Status> findStatusBy(int code) {
        for (Status status : Status.values()) {
            if (code == status.getCode()) {
                return Optional.of(status);
            }
        }
        return Optional.empty();
    }
}
