package com.denis.myown.chat.server;

import com.denis.myown.chat.exceptions.LaunchException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServer {

    public void start() {

        try (ServerSocket serverSocket = new ServerSocket(8090)) {
            System.out.println("Server is listening on port 8090");

            try (Socket socket = serverSocket.accept()) {
                InputStream inputStream = socket.getInputStream();

                Scanner scanner = new Scanner(inputStream);
                while (scanner.hasNextLine()) {
                    System.out.println(scanner.nextLine());
                }

/*                Reader in = new InputStreamReader(new BufferedInputStream(inputStream));

                StringBuilder stringBuilder = new StringBuilder();

                while (in.read() != -1) {
                    stringBuilder.append(in.read());
                }
                System.out.println("stringBuilder = " + stringBuilder);*/


            }

        } catch (IOException e) {
            LaunchException le = new LaunchException("Can not start server");
            le.initCause(e);
            throw le;
        }
    }


}
