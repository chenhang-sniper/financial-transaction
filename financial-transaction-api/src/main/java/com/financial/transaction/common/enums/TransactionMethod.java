package com.financial.transaction.common.enums;



public enum TransactionMethod {
    BUY(1, "买入"),
    SELL(2, "卖出");
    ;

    private int code;
    private String desc;
    private TransactionMethod(int code, String desc) {
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
