package com.denis.java_network_programming.nio.cient.non_bocking;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;

public class ChargenClient {
    private static final int DEFAULT_PORT = 20;

    public static void main(String[] args) {

/*        if (args.length == 0) {
            System.out.println("Usage: java ChargenClient host [port]");
            return;
        }*/

        int port;
        try {
            port = Integer.parseInt(args[1]);
        } catch (RuntimeException ex) {
            port = DEFAULT_PORT;
        }


        try {
            SocketAddress address = new InetSocketAddress(InetAddress.getLocalHost(), port);
            SocketChannel client = SocketChannel.open(address);
            client.configureBlocking(false);

            ByteBuffer buffer = ByteBuffer.allocate(74);
            WritableByteChannel out = Channels.newChannel(System.out);

            //In nonblocking mode, read() may return 0 because it doesn’t read anything. Therefore,
            //the loop needs to be a little different:
            while (true) {
                // Put whatever code here you want to run every pass through the loop
                // whether anything is read or not
                int n = client.read(buffer);
                if (n > 0) {
                    buffer.flip();
                    out.write(buffer);
                    buffer.clear();
                }
                else if (n == -1) {
                // This shouldn't happen unless the server is misbehaving.
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
