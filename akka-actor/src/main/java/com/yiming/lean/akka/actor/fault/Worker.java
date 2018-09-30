package com.yiming.lean.akka.actor.fault;


import akka.actor.*;
import akka.dispatch.Mapper;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Util;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.concurrent.duration.Duration;

/**
 * Worker performs some work when it receives the Start message. It will
 * continuously notify the sender of the Start message of current Progress.
 * The Worker supervise the CounterService.
 */
public class Worker extends UntypedActor {
    // Stop the CounterService child if it throws ServiceUnavailable
    private static SupervisorStrategy strategy = new OneForOneStrategy(-1,
            Duration.Inf(), t -> {
        if (t instanceof CounterServiceApi.ServiceUnavailable) {
            return SupervisorStrategy.stop();
        } else {
            return SupervisorStrategy.escalate();
        }
    });
    final LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    final Timeout askTimeout = new Timeout(Duration.create(5, "seconds"));
    final ActorRef counterService = getContext().actorOf(
            Props.create(CounterService.class), "counter");
    final int totalCount = 51;
    // The sender of the initial Start message will continuously be notified
    // about progress
    ActorRef progressListener;

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }

    @Override
    public void onReceive(Object msg) {
        log.debug("received message {}", msg);
        if (msg.equals(WorkerApi.Start) && progressListener == null) {
            progressListener = getSender();
            getContext().system().scheduler().schedule(
                    Duration.Zero(), Duration.create(1, "second"), getSelf(), WorkerApi.Do,
                    getContext().dispatcher(), null
            );
        } else if (msg.equals(WorkerApi.Do)) {
            counterService.tell(new CounterServiceApi.Increment(1), getSelf());
            counterService.tell(new CounterServiceApi.Increment(1), getSelf());
            counterService.tell(new CounterServiceApi.Increment(1), getSelf());

            // Send current progress to the initial sender
            Patterns.pipe(Patterns.ask(counterService, CounterServiceApi.GetCurrentCount, askTimeout)
                    .mapTo(Util.classTag(CounterServiceApi.CurrentCount.class))
                    .map(new Mapper<CounterServiceApi.CurrentCount, WorkerApi.Progress>() {
                        @Override
                        public WorkerApi.Progress apply(CounterServiceApi.CurrentCount c) {
                            return new WorkerApi.Progress(100.0 * c.count / totalCount);
                        }
                    }, getContext().dispatcher()), getContext().dispatcher())
                    .to(progressListener);
        } else {
            unhandled(msg);
        }
    }
}
