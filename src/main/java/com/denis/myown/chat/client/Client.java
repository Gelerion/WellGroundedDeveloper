package com.denis.myown.chat.client;

import com.denis.myown.chat.exceptions.ConnectException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private int DEFAULT_PORT = 8090;

    public static void main(String[] args) {
        new Client().connect();
    }

    public void connect(InetAddress host, int port) {
        if (host == null || port == 0) connect();

        doConnect(host, port);

    }

    public void connect() {
        doConnect(null, DEFAULT_PORT);
    }

    private void doConnect(InetAddress host, int port) {
        try (Socket socket = new Socket(InetAddress.getLocalHost(), port))
        {
            System.out.println("Is connected: " + socket.isConnected());


            try (OutputStream out = socket.getOutputStream()) {
                out.write("Hello World!".getBytes());
                out.flush();
            }

            try (InputStream in = socket.getInputStream()) {
                Scanner scanner = new Scanner(in);
                if (scanner.hasNextLine()) {
                    System.out.println(scanner.nextLine());
                }
            }

        }
        catch (IOException e) {
            ConnectException ce = new ConnectException("Can not connect to the server");
            ce.initCause(e);
            throw ce;
        }
    }

}
