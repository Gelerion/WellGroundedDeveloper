package com.denis.golovach.multhithreading.course.lecture_3;

public class SyncOnRef {
    //non volatile
    static boolean run = true;
    static Object ref = new Object();

    public static void main(String[] args) {
        Thread newThread = new Thread(() -> {
            synchronized (ref) {
                run = false;
            }
        });

        newThread.start();

        //будет писать true а в КАКОЙ-ТО момент будет писать false
        for (int i = 0; i < 5000; i++) {
            synchronized (ref) {
                System.out.println(run);
            }
        }
/*        while (true) {
            synchronized (ref) {
                System.out.println(run);
            }
        }*/
    }
}

/**
 * Если убрать хоть один synchronized
 * то может и не завершиться
 */
class BetterSync {
    //non volatile
    static boolean run = true;

    public static synchronized boolean isRun() {
        return run;
    }

    public static synchronized void setRun(boolean run) {
        BetterSync.run = run;
    }

    public static void main(String[] args) {
        Thread newThread = new Thread(() -> setRun(false));

        newThread.start();

        //будет писать true а в КАКОЙ-ТО момент будет писать false
        while (isRun());
        //end
    }
}
/**
 * Программа может и не узнать
 * об изменениях в run
 */
class TwoFieldsSync {
    //non volatile
    static boolean run = true;
    static Object refA = new Object();
    static Object refB = new Object();

    public static void main(String[] args) {
        Thread newThread = new Thread(() -> {
            synchronized (refA) {
                run = false;
            }
        });

        newThread.start();

        for (int i = 0; i < 5000; i++) {
            synchronized (refB) {
                System.out.println(run);
            }
        }
    }
}