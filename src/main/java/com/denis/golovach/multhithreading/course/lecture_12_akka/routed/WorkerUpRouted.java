package com.denis.golovach.multhithreading.course.lecture_12_akka.routed;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

public class WorkerUpRouted  extends UntypedActor{

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof String){
            String response = ((String)message).toUpperCase();
            ActorRef sender = getSender();
            sender.tell(response, getSelf());
            System.out.println(">> " + getSelf());
            while (true); //infinity
        }
        else
        {
            unhandled(message);
        }
    }
}
