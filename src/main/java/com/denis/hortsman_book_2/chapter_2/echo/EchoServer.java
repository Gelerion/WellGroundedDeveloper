package com.denis.hortsman_book_2.chapter_2.echo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {

    public static void main(String[] args) throws IOException {

        try (ServerSocket s = new ServerSocket(8189)){

            try(Socket socket = s.accept()) {
                //Получаю входящие данные
                InputStream in = socket.getInputStream();
                //Отправляю данные обратно
                OutputStream out = socket.getOutputStream();

                try(Scanner scn = new Scanner(in)) {
                    PrintWriter writer = new PrintWriter(out, true);
                    writer.println("Hello on our channel! Enter BYE to exit.");

                    boolean done = false;

                    while (!done && scn.hasNextLine()) {
                        String message = scn.nextLine();
                        writer.println("Echo " + message);
                        System.out.println("Echo " + message);
                        if (message.trim().equals("BYE")) {
                            done = true;
                        }
                    }
                }

            }
        }

    }
}

