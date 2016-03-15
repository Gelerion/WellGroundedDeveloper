package com.denis.golovach.multhithreading.course.lecture_12_akka.hash_map_reduce;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

import java.util.HashMap;
import java.util.Map;

public class Bucket extends UntypedActor {
    private Map data = new HashMap<>();

    @Override
    public void onReceive(Object msg) throws Exception {
        Object[] msgArr = (Object[]) msg;
        String command = (String) msgArr[0];
        String key = (String) msgArr[1];

        switch (command) {
            case "put": //msg = {"put", key , value}
                Object value = msgArr[2];
                data.put(key, value);
                break;
            case "remove":
                data.remove(key);
                break;
            case "get": // {"get", key, originalSender}
                        // {"get/result", key, value, originalSender}
                ActorRef originalSender = (ActorRef) msgArr[2];
                Object[] response = msg("get/result", key, data.get(key), originalSender);
                getSender().tell(response, getSelf());
                break;
        }
    }

    private Object[] msg(String command, String key, Object value, ActorRef originalSender) {
        return new Object[] {command, key, value, originalSender};
    }
}






































