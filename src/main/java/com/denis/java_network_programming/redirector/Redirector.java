package com.denis.java_network_programming.redirector;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class Redirector {

    private static final Logger logger = Logger.getLogger("Redirector");

    private final int port;
    private final String newSite;

    public Redirector(int port, String newSite) {
        this.port = port;
        this.newSite = newSite;
    }

    public void start() {
        try (ServerSocket server = new ServerSocket()){
            Socket connection = server.accept();
            


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
