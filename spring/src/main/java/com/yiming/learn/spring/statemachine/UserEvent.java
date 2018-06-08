package com.yiming.learn.spring.statemachine;

/**
 * Created by yiming on 2018-06-05 16:55.
 *
 * @author yiming Description: 用户事件枚举
 */
public enum UserEvent {
    /**
     * 支付
     */
    PAY,
    /**
     * 签收
     */
    RECEIVE,

    /**
     * 发起退款
     */
    REFUND,

    /**
     * 商家确认并退款给买家
     */
    SUPPLIER_CONFIRM,

    /**
     * 买家确认收到退款
     */
    BUYER_CONFIRM
}
