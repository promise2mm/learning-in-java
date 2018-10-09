package com.yiming.lean.akka.actor.fault.actor;

import akka.actor.AbstractActor;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class ChildActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder().matchAny(o -> {
            if (o instanceof Integer) {

                Integer num = (Integer) o;
                if (num % 5 == 0) {
                    if (new Random().nextBoolean()) {
                        throw new ArithmeticException("Simulate ArithmeticException. -> resume...");
                    }
                    Storage.arr[num] = true;
                    log.info("arr: {}", Storage.arr);
                } else if (num % 5 == 1) {
                    if (new Random().nextBoolean()) {
                        throw new NullPointerException("Simulate NullPointerException. -> restart...");
                    }
                    Storage.arr[num] = true;
                    log.info("arr: {}", Storage.arr);
                } else if (num % 5 == 2) {
                    if (new Random().nextBoolean()) {
                        //throw new IllegalArgumentException("Simulate IllegalArgumentException -> stop.");
                    }
                    Storage.arr[num] = true;
                    log.info("arr: {}", Storage.arr);
                } else {
                    Storage.arr[num] = true;
                    log.info("arr: {}", Storage.arr);
                }
                if (allTrue()){
                    getSender().tell("stop", getSelf());
                }
            }
        }).build();
    }

    private boolean allTrue() {
        boolean res = true;
        for (int i = 0; i < Storage.arr.length; i++) {
            res = res && Storage.arr[i];
        }
        return res;
    }
}
