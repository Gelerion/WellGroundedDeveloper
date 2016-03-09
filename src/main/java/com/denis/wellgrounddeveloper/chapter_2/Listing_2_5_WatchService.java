package com.denis.wellgrounddeveloper.chapter_2;

import java.io.IOException;
import java.nio.file.*;

public class Listing_2_5_WatchService
{
    public static void main(String[] args)
    {
        boolean shootdown = false;

        try
        {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path dir = FileSystems.getDefault().getPath("D:\\Games");

//            �� ������ ����������� � ������� ������ �����, �������� ENTRY_CREATE, ENTRY_DELETE � OVERFLOW
//          (��������� ������ ����� ��������, ��� ������� ���� �������� ��� ��������).
            WatchKey watchKey = dir.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_CREATE);

            while (!shootdown)
            {
                watchKey = watchService.take();

                for (WatchEvent<?> event : watchKey.pollEvents())
                {
                    if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY)
                    {
                        System.out.println("Some changes occurs: " + event.context());
                        System.out.println("Count: " + event.count());
                    }

                    if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE)
                    {
                        System.out.println("Some file was created: " + event.context());
                        System.out.println("Count: " + event.count());
                    }
                }

                watchKey.reset();
            }

        } catch (IOException | InterruptedException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
