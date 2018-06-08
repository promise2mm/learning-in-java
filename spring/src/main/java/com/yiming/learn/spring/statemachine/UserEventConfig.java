package com.yiming.learn.spring.statemachine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * Created by yiming on 2018-06-08 10:24. Description:
 *  监听用户事件配置
 * @author yiming
 */
@WithStateMachine
@Slf4j
public class UserEventConfig {

    @OnTransition(target = "NOT_PAY")
    public void createOrder() {
        log.info("创建订单成功, 待支付...");
    }

    @OnTransition(source = "NOT_PAY", target = "IN_SHIPPING")
    public void pay() {
        log.info("支付成功, 待收货...");
    }

    @OnTransition(source = "IN_SHIPPING", target = "DONE")
    public void receive() {
        log.info("签收成功, 订单完成!");
    }

    @OnTransition(source = "DONE", target = "IN_REFUND")
    public void refund() {
        log.info("发起退货, 退货中...");
    }

    @OnTransition(source = "IN_REFUND", target = "IN_REFUND")
    public void supplierConfirm() {
        log.info("供应商确认收到退货并退款给用户, 退货中...");
    }

    @OnTransition(source = "IN_REFUND", target = "REFUND_DONE")
    public void buyerConfirm() {
        log.info("用户确认收到退款, 订单完成!");
    }

}
