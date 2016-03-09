package com.denis.hortsman_book_2.chapter_2.clients;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

public class SocketTest {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("132.163.4.104", 13)) {

            socket.setSoTimeout(10000); //10 ms

            InputStream in = socket.getInputStream();
            Scanner scanner = new Scanner(in);

            if (scanner.hasNextLong()) {
                String line = scanner.nextLine();
                System.out.println("line = " + line);
            }

            scanner.close();
        }

//        Socket s = new Socket();  создаём не соединённый сокет
//        s.connect(new InetSocketAddress(host, port), timeout); соеддиняемся с тайм аутом
    }
}
