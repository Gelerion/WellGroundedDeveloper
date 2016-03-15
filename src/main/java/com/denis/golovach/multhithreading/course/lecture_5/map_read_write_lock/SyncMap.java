package com.denis.golovach.multhithreading.course.lecture_5.map_read_write_lock;

import java.util.HashMap;
import java.util.Map;

/**
 * Ћучше использовать идиому приватный монитор
 * снаружи она многопоточно защищЄнна€
 */
public class SyncMap {
    //если селать привтный лок, то мы используем внутренний монитор
    //и поэтому синхронизаци€ снаружи не повесит наш класс
    //и не сможет забрать наш notify

    //важно что она приватна€
    private Map<Integer, String> map = new HashMap<>();

    public synchronized String get(Integer key) {
        return map.get(key);
    }

    public synchronized String put(Integer key, String value) {
        return map.put(key, value);
    }
}


//„еловек захватил монитор и повис, теперь не могу пользоватьс€ своим классом
//все потоки попдают в BlockedSet и вис€т
//так как это всЄ по тому же монитору
class X {
    //ќѕј—Ќќ—“№!!!!
    public static void main(String[] args) {
        SyncMap map = null;
        synchronized (map) {
            while (true) {
                //...
            }
        }
    }
}