package com.yiming.lean.akka.actor.fault.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class ActorDemo {

    public static void main(String[] args) {

        Config config = ConfigFactory.parseString("akka.loglevel = DEBUG \n" +
                "akka.actor.debug.lifecycle = on");


        ActorSystem actorSystem = ActorSystem.create("demo", config);

        ActorRef actorRef = actorSystem.actorOf(Props.create(SupervisorActor.class));

        actorRef.tell("start", ActorRef.noSender());

    }

}
