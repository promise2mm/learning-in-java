package com.yiming.lean.akka.actor.fault;

public interface CounterServiceApi {

    Object GetCurrentCount = "GetCurrentCount";

    class CurrentCount {
        public final String key;
        public final long count;

        public CurrentCount(String key, long count) {
            this.key = key;
            this.count = count;
        }

        @Override
        public String toString() {
            return String.format("%s(%s, %s)", getClass().getSimpleName(), key, count);
        }
    }

    class Increment {
        public final long n;

        public Increment(long n) {
            this.n = n;
        }

        @Override
        public String toString() {
            return String.format("%s(%s)", getClass().getSimpleName(), n);
        }
    }

    class ServiceUnavailable extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public ServiceUnavailable(String msg) {
            super(msg);
        }
    }

}
