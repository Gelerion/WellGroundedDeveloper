package com.denis.java_network_programming.single_file;

import com.denis.java_network_programming.single_file.server.SingleFileHttpServer;

import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class StartServer {
    private static final Logger logger = Logger.getLogger("StartServer");

    public static void main(String[] args) {
        // set the port to listen on
        int port;
        try {
            port = Integer.parseInt(args[1]);
            if (port < 1 || port > 65535) port = 80;
        } catch (RuntimeException ex) {
            port = 80;
        }

        String encoding = "UTF-8";
        if (args.length > 2) encoding = args[2];

        try {

            Path path = Paths.get(args[0]);
            byte[] data = Files.readAllBytes(path);
            String mimeType = URLConnection.getFileNameMap().getContentTypeFor(args[0]);

            SingleFileHttpServer server = new SingleFileHttpServer(data, mimeType, encoding, port);
            server.start();

        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Usage: java SingleFileHTTPServer filename port encoding");
        } catch (IOException ex) {
            logger.severe(ex.getMessage());
        }

    }
}
