package com.denis.golovach.multhithreading.course.lecture_12_akka.routed;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.SmallestMailboxPool;
import com.denis.golovach.multhithreading.course.lecture_12_akka.first_example.Callback;

import java.util.Scanner;

/**
 * I am giving to the ActorSystem something like LoadBalancer for workers
 */
public class StarterRouted {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("demo");
        ActorRef router = system.actorOf(
                //i am NOT giving worker straight to the ActorSystem
                //through LoadBalancer with 5 threads
                new SmallestMailboxPool(5).props(Props.create(WorkerUpRouted.class)),
                "workers");
        ActorRef callback = system.actorOf(Props.create(Callback.class), "callback");

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String line = sc.nextLine();
            if ("exit".equals(line)) {
                system.shutdown();
                return;
            }

            router.tell(line, callback);
        }

    }
}
