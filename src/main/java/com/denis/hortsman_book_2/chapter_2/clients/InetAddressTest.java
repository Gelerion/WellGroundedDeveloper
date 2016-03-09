package com.denis.hortsman_book_2.chapter_2.clients;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
    public static void main(String[] args) throws UnknownHostException {
        if (args.length > 0) {
            InetAddress[] addresses = InetAddress.getAllByName(args[0]);
            for (InetAddress address : addresses) {
                System.out.println(address);
            }
        } else {
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println("localHost = " + localHost);
        }
    }
}
