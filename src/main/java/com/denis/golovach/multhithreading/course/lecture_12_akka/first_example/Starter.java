package com.denis.golovach.multhithreading.course.lecture_12_akka.first_example;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import java.util.Scanner;

public class Starter {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("Demo");
        ActorRef worker = system.actorOf(Props.create(WorkerUp.class), "worker");
        ActorRef callback = system.actorOf(Props.create(Callback.class), "callback");

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String line = sc.nextLine();
            if ("exit".equals(line)) {
                system.shutdown();
                return;
            }

            worker.tell(line, callback);
        }
    }
}
