package com.denis.golovach.multhithreading.course.lecture_12_akka.first_example;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

public class WorkerUp extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof String){
            String response = ((String)message).toUpperCase();
            ActorRef sender = getSender();
            sender.tell(response, getSelf());
            //Could answer many times
            //Or i could answer to someone else
//            sender.tell(response, getSelf());
//            sender.tell(response, getSelf());
        }
        else
        {
            unhandled(message);
        }
    }
}
