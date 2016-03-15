package com.denis.golovach.multhithreading.course.lecture_12_akka.map_reduce;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.denis.golovach.multhithreading.course.lecture_12_akka.first_example.Callback;
import com.denis.golovach.multhithreading.course.lecture_12_akka.hash_map_reduce.Container;

import java.io.IOException;
import java.util.Map;

/**
 * key sme length with value
 */
public class MrDemo {
    public static void main(String[] args) throws IOException {
        ActorSystem system = ActorSystem.create("demo");
        ActorRef container = system.actorOf(Props.create(Container.class), "container");
        ActorRef callback = system.actorOf(Props.create(Callback.class), "callback");

        container.tell(msg("put", "keyA", "valueA"), ActorRef.noSender());
        container.tell(msg("put", "keyB", "valB"), ActorRef.noSender());
        container.tell(msg("put", "keyA", "valueC"), ActorRef.noSender());
        container.tell(msg("put", "keyA", "valD"), ActorRef.noSender());

        Mapper<Map.Entry<String, String>, Integer> mapper = new Mapper<Map.Entry<String, String>, Integer>() {
            @Override
            public Integer map(Map.Entry<String, String> enrty) {
                return enrty.getKey().length() == enrty.getValue().length() ? 1 : 0;
            }
        };

        Reducer<Integer> reducer = new Reducer<Integer>() {
            @Override
            public Integer reduce(Integer left, Integer right) {
                return left + right;
            }
        };

        System.in.read();
        system.shutdown();

    }

    private static Object[] msg(String command, String key, String value) {
        return new Object[]{command, key, value};
    }

    private static Object[] msg(String command, String key) {
        return new Object[]{command, key};
    }

}

