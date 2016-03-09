package com.denis.java_network_programming.single_file.server;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SingleFileHttpServer {

    private static final Logger logger = Logger.getLogger("SingleFileHTTPServer");

    private final byte[] content;
    private final byte[] header;
    private final String encoding;
    private final int port;

    public SingleFileHttpServer(String data, String mimeType, String encoding, int port) throws UnsupportedEncodingException {
        this(data.getBytes(encoding), mimeType, encoding, port);
    }

    public SingleFileHttpServer(byte[] data, String mimeType, String encoding, int port) {

        this.encoding = encoding;
        this.content = data;
        this.port = port;

        String header = "HTTP/1.0 OK \r\n" +
                "Server: OnFile 2.0 \r\n" +
                "Content-Length: " + content.length + " \r\n" +
                "Content-Type: " + mimeType + ", charset: " + encoding + "\r\n\r\n";
        this.header = header.getBytes(Charset.forName("US-ASCII"));
    }

    public void start() {
        ExecutorService pool = Executors.newFixedThreadPool(15);
        try (ServerSocket server = new ServerSocket(port)) {

            logger.info("Accepting connections on port " + server.getLocalPort());
            logger.info("Data to be sent:");
            logger.info(new String(this.content, encoding));

            while (true) {
                Socket connect = server.accept();
                pool.submit(new HttpHandler(connect, content, header));
            }

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Could not start server", e);
        }
    }
}
