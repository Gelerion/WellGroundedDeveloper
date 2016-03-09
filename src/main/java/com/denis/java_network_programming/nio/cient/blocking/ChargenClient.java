package com.denis.java_network_programming.nio.cient.blocking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;

public class ChargenClient {
    private static final int DEFAULT_PORT = 20;

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Usage: java ChargenClient host [port]");
            return;
        }

        int port;
        try {
            port = Integer.parseInt(args[1]);
        } catch (RuntimeException ex) {
            port = DEFAULT_PORT;
        }


        try {
//            When implementing a client that takes advantage of the new I/O APIs, begin by invoking
//            the static factory method SocketChannel.open() to create a new java.nio.chan
//            nels.SocketChannel object. The argument to this method is a java.net.SocketAd
//            dress object indicating the host and port to connect to.
            SocketAddress address = new InetSocketAddress(args[0], port);
            SocketChannel client = SocketChannel.open(address);

            /*
            The channel is opened in blocking mode, so the next line of code won’t execute until
            the connection is established. If the connection can’t be established, an IOException is
            thrown.
            */

//            objects. You’ve got a pretty good idea
//            that the lines of text are 74 ASCII characters long (72 printable characters followed by
//            a carriage return/linefeed pair) so you’ll create a ByteBuffer that has a 74-byte capacity
            ByteBuffer buffer = ByteBuffer.allocate(74);

//            Assuming there is some data in the buffer—that is, n > 0—this data can be copied to
//            System.out. There are ways to extract a byte array from a ByteBuffer that can then be
//            written on a traditional OutputStream such as System.out. However, it’s more informative
//            to stick with a pure, channel-based solution. Such a solution requires wrapping
//            the OutputStream System.out in a channel using the Channels utility class, specifically,
//            its newChannel() method:
            WritableByteChannel out = Channels.newChannel(System.out);

            while (client.read(buffer) != -1) {
                buffer.flip();
                out.write(buffer);
                buffer.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
