package com.yiming.lean.akka.actor.fault;

public interface StorageApi {

    class Store {
        public final Entry entry;

        public Store(Entry entry) {
            this.entry = entry;
        }

        @Override
        public String toString() {
            return String.format("%s(%s)", getClass().getSimpleName(), entry);
        }
    }

    class Entry {
        public final String key;
        public final long value;

        public Entry(String key, long value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("%s(%s, %s)", getClass().getSimpleName(), key, value);
        }
    }

    class Get {
        public final String key;

        public Get(String key) {
            this.key = key;
        }

        @Override
        public String toString() {
            return String.format("%s(%s)", getClass().getSimpleName(), key);
        }
    }

    class StorageException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public StorageException(String msg) {
            super(msg);
        }
    }
}


