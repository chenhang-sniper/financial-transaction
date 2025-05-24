package com.financial.transaction.common.enums;


public enum TransactionStatus {
    PENDING(0, "交易中"), // 交易尚未完成，正在处理中
    SUCCESS(1, "已成功"), // 交易成功完成
    CANCELLED(2, "已撤销"), // 交易已被撤销
    ;

    private int code;
    private String desc;
    private TransactionStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
