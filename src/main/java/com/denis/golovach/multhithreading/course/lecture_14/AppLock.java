package com.denis.golovach.multhithreading.course.lecture_14;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

/**
 * On of possibilities to avoid dead lock is to sort
 * lock handling. Thy should be one sided (ex: from smaller to bigger, left to right etc)
 */
public class AppLock {
    private static ExecutorService executors = Executors.newCachedThreadPool();
    private static Random random = new Random();

    public static void main(String[] args) throws Exception {

        AccountLock[] accounts = {
                new AccountLock(100), new AccountLock(0),
                new AccountLock(100), new AccountLock(0),
                new AccountLock(100), new AccountLock(0),
                new AccountLock(100), new AccountLock(0),
                new AccountLock(100), new AccountLock(0)
        };

        for (int i = 0; i < 4 * Runtime.getRuntime().availableProcessors(); i++) {
            executors.execute(() -> {
                while (true) {
                    int from = random.nextInt(accounts.length);
                    int to = random.nextInt(accounts.length);

                    if (from != to) {
                        int delta = random.nextInt(50);
                        transfer(accounts[from], accounts[to], delta);
                    }
                }
            });
        }

        sleep(500);
        System.out.println(sum(accounts));
        System.out.println(toStr(accounts));
    }

    /**
     * Order of lock is VERY important
     */
    public static int sum(final AccountLock[] accounts) throws Exception {
        final AccountLock[] tmp = accounts.clone();
        Arrays.sort(tmp, (fst, snd) -> fst.id - snd.id);

        return lockRecursively(tmp, new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                int result = 0;
                for (AccountLock acc : tmp) {
                    result += acc.getBalance();
                }
                return result;
            }
        });
    }

    public static String toStr(AccountLock[] accounts) throws Exception {
        final AccountLock[] tmp = accounts.clone();
        Arrays.sort(tmp, (fst, snd) -> fst.id - snd.id);

        return lockRecursively(tmp, () -> {
            StringBuilder sb = new StringBuilder();
            for (AccountLock acc : tmp) {
                sb.append(acc.toString()).append(" ");
            }
            return sb.toString();
        });
    }

    private static <T> T lockRecursively(AccountLock[] accounts, Callable<T> c) throws Exception {
        if (accounts.length > 0) {
            accounts[0].locker.lock();
            try {
                return lockRecursively(Arrays.copyOfRange(accounts, 1, accounts.length), c);
            } finally {
                accounts[0].locker.unlock();
            }
        } else {
            return c.call();
        }
    }

    private static void transfer(AccountLock from, AccountLock to, int amount) {
        AccountLock fst = (from.id < to.id) ? from : to;
        AccountLock snd = (from.id >= to.id) ? from : to;
        fst.locker.lock();
        try {
            snd.locker.lock();
            try {
                if (from.incBalance(-amount)) {
                    if (!to.incBalance(amount)) {
                        from.incBalance(amount);
                    }
                }
            } finally {
                snd.locker.unlock();
            }
        } finally {
            fst.locker.unlock();
        }
    }

    /**
     * DEAD LOCK SAMPLE!
     */
    private static void transferDL(AccountLock from, AccountLock to, int amount) {
        from.locker.lock();
        try {
            to.locker.lock();
            try {
                if (from.incBalance(-amount)) {
                    if (!to.incBalance(amount)) {
                        from.incBalance(amount);
                    }
                }
            } finally {
                to.locker.unlock();
            }
        } finally {
            from.locker.unlock();
        }
    }
}
