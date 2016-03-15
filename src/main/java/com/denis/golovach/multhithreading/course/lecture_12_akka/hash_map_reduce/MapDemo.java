package com.denis.golovach.multhithreading.course.lecture_12_akka.hash_map_reduce;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.denis.golovach.multhithreading.course.lecture_12_akka.first_example.Callback;

import java.io.IOException;

public class MapDemo {
    public static void main(String[] args) throws IOException {
        ActorSystem system = ActorSystem.create("demo");
        ActorRef container = system.actorOf(Props.create(Container.class), "container");
        ActorRef callback = system.actorOf(Props.create(Callback.class), "callback");

        container.tell(msg("put", "keyA", "valueA"), ActorRef.noSender());
        container.tell(msg("put", "keyB", "valueB"), ActorRef.noSender());

        container.tell(msg("remove", "keyB"), ActorRef.noSender());

        container.tell(msg("get", "keyA"), callback);
        container.tell(msg("get", "keyB"), callback);

        System.in.read();
        system.shutdown();

    }

    private static Object[] msg(String command, String key, String value) {
        return new Object[] {command, key, value};
    }

    private static Object[] msg(String command, String key) {
        return new Object[] {command, key};
    }
}
