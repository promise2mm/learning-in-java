package com.yiming.learn.spring.statemachine;

import java.util.EnumSet;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

/**
 * Created by yiming on 2018-06-05 16:58. Description: 状态机配置
 *
 * @author yiming
 */
@Configuration
@EnableStateMachine //启用状态机功能
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderState, UserEvent> {

    /**
     * 初始化状态机配置
     */
    @Override
    public void configure(StateMachineStateConfigurer<OrderState, UserEvent> stateConfigurer) throws Exception {
        //初始状态: 待支付
        //状态集: OrderState状态集合
        stateConfigurer.withStates()
            .initial(OrderState.NOT_PAY)
            .states(EnumSet.allOf(OrderState.class));
    }

    /**
     * 定义状态间的转移以及触发事件
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<OrderState, UserEvent> transitions) throws Exception {
        transitions.withExternal()
            //待支付  --支付-->  配送中
            .source(OrderState.NOT_PAY).target(OrderState.IN_SHIPPING).event(UserEvent.PAY)
            .and().withExternal()
            //配送中  --签收-->  完成
            .source(OrderState.IN_SHIPPING).target(OrderState.DONE).event(UserEvent.RECEIVE)
            .and().withExternal()
            //订单完成  --发起退款-->  退款中
            .source(OrderState.DONE).target(OrderState.IN_REFUND).event(UserEvent.REFUND)
            .and().withExternal()
            //退款中  --商家确认-->  商家确认并退款给买家
            .source(OrderState.IN_REFUND).target(OrderState.IN_REFUND).event(UserEvent.SUPPLIER_CONFIRM)
            .and().withExternal()
            //商家确认  --买家确认收到退款-->  订单完成
            .source(OrderState.IN_REFUND).target(OrderState.REFUND_DONE).event(UserEvent.BUYER_CONFIRM);
    }
}
