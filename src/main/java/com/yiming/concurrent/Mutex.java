package com.yiming.concurrent;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by yiming on 2018/1/30.
 */
public class Mutex implements Lock, Serializable {

    private Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    boolean isLocked() {
        return sync.isHeldExclusively();
    }


    boolean hasQueueThreads() {
        return sync.hasQueuedThreads();
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    private static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int aqueries) {
            assert aqueries == 1;
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int releases) {
            assert releases == 1;
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        /**
         * 是否被占用
         * <p>
         * waitStatus value to indicate thread has cancelled
         * static final int CANCELLED =  1;
         * <p>
         * waitStatus value to indicate successor's thread needs unparking
         * static final int SIGNAL    = -1;
         * <p>
         * waitStatus value to indicate thread is waiting on condition
         * static final int CONDITION = -2;
         * <p>
         * waitStatus value to indicate the next acquireShared should unconditionally propagate
         * static final int PROPAGATE = -3;
         *
         * @return
         */
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        Condition newCondition() {
            return new ConditionObject();
        }
    }
}
