package com.denis.golovach.multhithreading.course.lecture_14;

public class LatchMonitor {
    private boolean open = false;

    public synchronized void doOpen() {
        open = true;
        this.notifyAll();
    }

    public synchronized void await() throws InterruptedException {
        while (!open) { //sipirious wakeup
            this.wait();
        }
    }
}
