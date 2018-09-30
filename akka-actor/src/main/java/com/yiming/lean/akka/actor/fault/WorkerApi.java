package com.yiming.lean.akka.actor.fault;


public interface WorkerApi {
    Object Start = "Start";
    Object Do = "Do";

    class Progress {
        public final double percent;

        public Progress(double percent) {
            this.percent = percent;
        }

        @Override
        public String toString() {
            return String.format("%s(%s)", getClass().getSimpleName(), percent);
        }
    }
}
