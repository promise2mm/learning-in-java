package com.yiming.learn.spring.statemachine;

/**
 * Created by yiming on 2018-06-05 16:55. Description: 订单状态枚举
 *
 * @author yiming
 */
public enum OrderState {
    /**
     * 待支付
     */
    NOT_PAY("not_pay", "待支付"),
    /**
     * 配送中
     */
    IN_SHIPPING("in_shipping", "配送中"),
    /**
     * 完成
     */
    DONE("done", "完成"),

    IN_REFUND("refund", "退货中"),

    REFUND_DONE("refund_done", "退货成功");

    private String code;

    private String name;

    OrderState(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
