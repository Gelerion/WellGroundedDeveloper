package com.denis.golovach.multhithreading.course.lecture_5.customHandler;

/**
 * Когда потом выбрасывает ошибку можно её поймать
 */
public class ThreadExHandler {
    public static void main(String[] args) {
        Thread me = Thread.currentThread();

        me.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

            //t - тот поток который её бросил
            //e - ошибка
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                // Вместо stackTrace
                System.out.println("Bad");
            }
        });

        throw new Error();

/*        ThreadGroup group = new ThreadGroup("stub");
        new Thread(group, () -> {
            System.out.println("Never");
        });*/
    }
}
