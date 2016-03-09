package com.denis.wellgrounddeveloper.chapter_2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Listing_2_5_1SeekableByteChannel
{
//    ¬ следующем фрагменте показано, как можно использовать новые свойства FileChannel, обеспечивающие возможность
//  поиска (так называемые seekable-свойства) дл€ считывани€ последних 1000 символов из файла журнала.
    public static void main(String[] args) throws IOException
    {
        Path dir = Paths.get("D:\\Games\\Test.txt");
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        FileChannel channel = FileChannel.open(dir, StandardOpenOption.READ);
        int read = channel.read(buffer, channel.size() - 1000);
        System.out.println("read = " + read);
    }
}
