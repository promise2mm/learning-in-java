package com.yiming.learn.spring.statemachine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;

/**
 * Created by yiming on 2018-06-05 17:02. Description:
 *
 * @author yiming
 */
@SpringBootApplication
@Slf4j
public class MyApp implements CommandLineRunner {

    @Autowired
    private StateMachine<OrderState, UserEvent> stateMachine;

    public static void main(String[] args) {
        SpringApplication.run(MyApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //开始
        stateMachine.start();
        log.info("当前状态:{}", stateMachine.getState().getId().getName());
        Thread.sleep(1000L);

        //支付
        stateMachine.sendEvent(UserEvent.PAY);
        log.info("当前状态:{}", stateMachine.getState().getId().getName());
        Thread.sleep(1000L);

        //用户签收
        stateMachine.sendEvent(UserEvent.RECEIVE);
        log.info("当前状态:{}", stateMachine.getState().getId().getName());
        Thread.sleep(1000L);

        //用户退款
        stateMachine.sendEvent(UserEvent.REFUND);
        log.info("当前状态:{}", stateMachine.getState().getId().getName());
        Thread.sleep(1000L);

        //商家确认收到退货并退款给用户
        stateMachine.sendEvent(UserEvent.SUPPLIER_CONFIRM);
        log.info("当前状态:{}", stateMachine.getState().getId().getName());
        Thread.sleep(1000L);

        //用户确认收到退款
        stateMachine.sendEvent(UserEvent.BUYER_CONFIRM);
        log.info("当前状态:{}", stateMachine.getState().getId().getName());
    }
}
