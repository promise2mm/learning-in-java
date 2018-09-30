package com.yiming.lean.akka.actor.fault;

import akka.actor.ActorRef;

public interface CounterApi {
    class UseStorage {
        public final ActorRef storage;

        public UseStorage(ActorRef storage) {
            this.storage = storage;
        }

        @Override
        public String toString() {
            return String.format("%s(%s)", getClass().getSimpleName(), storage);
        }
    }
}
