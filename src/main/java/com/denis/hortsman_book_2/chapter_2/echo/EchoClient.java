package com.denis.hortsman_book_2.chapter_2.echo;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
    //полузакрытие позволяет прерывать соединение на одной стороне
    public static void main(String[] args) throws IOException {
        try(Socket socket = new Socket(InetAddress.getLocalHost(), 8090)) {

            try(PrintWriter writer = new PrintWriter(socket.getOutputStream())) {
                try(Scanner in = new Scanner(socket.getInputStream())) {
                    writer.print("Hello server");
                    writer.flush();
//                    теперь сокет полузыкрыт принять ответ
                    socket.shutdownOutput();

                    while (in.hasNext()) {
                        String message = in.nextLine();
                        System.out.println(message);
                    }

                    socket.close();
                }
            }



        }
    }
}
