package com.denis.golovach.multhithreading.course.lecture_12_akka.first_example;

import akka.actor.UntypedActor;

import java.util.Arrays;

public class Callback extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Object[]) {
            System.out.println("result: " + Arrays.toString((Object[]) message));
        }
        else {
            System.out.println("result: " + message);
        }

    }
}
