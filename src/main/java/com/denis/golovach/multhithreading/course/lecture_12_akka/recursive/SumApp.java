package com.denis.golovach.multhithreading.course.lecture_12_akka.recursive;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.denis.golovach.multhithreading.course.lecture_12_akka.first_example.Callback;

public class SumApp {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("demo");
        ActorRef callback = system.actorOf(
                Props.create(Callback.class), "callback");
        ActorRef kernel = system.actorOf(
                //with Constructor
                Props.create(SumKernel.class, callback), "sumKernel");

        //[0, 10] -> {1,2,4,7,8}/5
        //[0, 20] -> {1,2,4,7,8,11,13,14,16,17,19}/5
        kernel.tell(new int[]{0, 10}, ActorRef.noSender());
    }
}
