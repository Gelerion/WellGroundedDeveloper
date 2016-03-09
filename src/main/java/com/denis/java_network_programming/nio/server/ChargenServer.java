package com.denis.java_network_programming.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ChargenServer {
    private static final int DEFAULT_PORT = 20;

    public static void main(String[] args) {

        int port;
        try {
            port = Integer.parseInt(args[0]);
        } catch (RuntimeException ex) {
            port = DEFAULT_PORT;
        }
        System.out.println("Listening for connection on port: " + port);

        byte[] rotation = new byte[95 * 2];
//        System.out.println("i = ' ' -> " + (int) ' ');
//        System.out.println("i = '~' -> " + (int)  '~');
        for (byte i = ' '; i <= '~'; i++) {
//            System.out.println("Char: " + (char) i + " number: " + i);
            rotation[i - ' '] = i;
            rotation[i + 95 - ' '] = i;
        }

        ServerSocketChannel serverChannel;
        Selector selector;

        try {
            // Вначале созаём серверный канал
            serverChannel = ServerSocketChannel.open();
            // канал не слушает никакого порта, на нужно указать ему на каком порте ждать
            // в 7ой версии можно упростить - serverChannel.bind(new InetSocketAddress(19));
            ServerSocket ss = serverChannel.socket();
            InetSocketAddress address = new InetSocketAddress(port);
            // этот соквет теперь связан с каналом
            ss.bind(address);

            serverChannel.configureBlocking(false);
            selector = Selector.open();
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        while (true) {
            try {
                selector.select();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }

            Set<SelectionKey> readKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = readKeys.iterator();

            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                try {
                    if (key.isAcceptable()) {

                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel client = server.accept();
                        System.out.println("Accepted connection from " + client);

                        client.configureBlocking(false);
                        SelectionKey clientKey = client.register(selector, SelectionKey.OP_WRITE);

                        ByteBuffer buffer = ByteBuffer.allocate(74);
                        buffer.put(rotation, 0, 72);
                        buffer.put((byte) '\r');
                        buffer.put((byte) '\n');
                        buffer.flip();
                        clientKey.attach(buffer);
                    }
                    else if (key.isWritable()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment();

                        if (!buffer.hasRemaining()) {
                            // Refill the buffer with the next line
                            buffer.rewind();
                            // Get the old first character
                            int first = buffer.get();
                            // Get ready to change the data in the buffer
                            buffer.rewind();
                            // Find the new first characters position in rotation
                            int position = first - ' ' + 1;
                            // copy the data from rotation into the buffer
                            buffer.put(rotation, position, 72);
                            // Store a line break at the end of the buffer
                            buffer.put((byte) '\r');
                            buffer.put((byte) '\n');
                            // Prepare the buffer for writing
                            buffer.flip();
                        }

                        client.write(buffer);
                    }
                } catch (IOException e) {
                    key.cancel();
                    try {
                        key.channel().close();
                    } catch (IOException ignore) { /*NOP*/ }
                    e.printStackTrace();
                }
            }
        }

    }


    private static void printRotationPretty(byte[] rotation) {
        for (int i = 0; i < rotation.length; i++) {
            if (i <= rotation.length / 2) {
                System.out.println("0 - 95 characters: " + (char) rotation[i] + "(" + (int) rotation[i] + ")" + " ");
            } else {
                System.out.println("96 - 190 characters: " + (char) rotation[i] + "(" + (int) rotation[i] + ")" + " ");
            }
        }
    }
}
