package com.yiming.lean.akka.actor.fault.actor;

import akka.actor.*;
import akka.japi.pf.DeciderBuilder;

import java.time.Duration;

/**
 * <p> ${DESCRIPTION} </p>
 *
 * @author 一鸣
 * @since 2018-09-28 10:55.
 */
public class SupervisorActor extends AbstractActor {

    private static SupervisorStrategy strategy =
            new OneForOneStrategy(3, Duration.ofMinutes(1),
                    DeciderBuilder
                            .match(ArithmeticException.class, e -> SupervisorStrategy.resume())
                            .match(NullPointerException.class, e -> SupervisorStrategy.restart())
                            .match(IllegalArgumentException.class, e -> SupervisorStrategy.stop())
                            .matchAny(o -> SupervisorStrategy.escalate())
                            .build());

    private ActorRef child = getContext().actorOf(Props.create(ChildActor.class), "child");

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }


    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals("start", s -> {
                    for (int i = 0; i < Storage.arr.length; i++) {
                        child.tell(i, getSelf());
                    }
                })
                .matchEquals("stop", s -> getContext().getSystem().terminate())
                .build();
    }

}
