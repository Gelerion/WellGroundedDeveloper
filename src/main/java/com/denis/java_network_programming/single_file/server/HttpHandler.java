package com.denis.java_network_programming.single_file.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.Callable;

public class HttpHandler implements Callable<Void> {

    private Socket connection;
    private byte[] content;
//    private byte[] header;
    private byte[] header;

    public HttpHandler(Socket socket, byte[] content, byte[] header) {
        this.connection = socket;
        this.content = content;
        this.header = header;
    }

    @Override
    public Void call() throws Exception {
        try {
            OutputStream out = new BufferedOutputStream(connection.getOutputStream());
            InputStream in = new BufferedInputStream(connection.getInputStream());

            // read the first line only; that's all we need
            StringBuilder request = new StringBuilder(80);
            while (true) {
                int c = in.read();
                if (c == '\r' || c == '\n' || c == -1) break;
                request.append((char) c);
            }

            // If this is HTTP/1.0 or later send a MIME header
            if (request.indexOf("HTTP/") != -1) {
                out.write(header);
            }

            out.write(content);
            out.flush();
        } finally {
            connection.close();
        }

        return null;
    }
}
