package com.financial.transaction.common.enums;


public enum TransactionType {
    STOCK(1, "股票"),
    BOND(2, "债券"),
    FUND(3, "基金"),
    FUTURES(4,"期货"),
    OPTIONS(5,"期权"),
    FOREX(6, "外汇"),
    OTHERS(99, "其它");

    private int code;
    private String desc;
    private TransactionType(int code, String desc) {
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
