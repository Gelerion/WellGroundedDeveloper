package com.denis.wellgrounddeveloper.chapter_2;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Listing_2_2_fileSearchInCatalog
{
//    Благодаря появлению нового интерфейса java.nio.file.DirectoryStream<T> и реализующих его классов вы можете выполнять следующие функции широкого применения:
//    ??перебирать записи в каталоге, например находить в нем файлы;
//    ??находить записи в каталоге, пользуясь выражениями-масками (например, *Foobar*) и обнаружением контента по типам MIME (например, файлы text/xml);
//    ??выполнять рекурсивные операции перемещения, копирования и удаления с помощью метода walkFileTree.
    public static void main(String[] args)
    {
        Path dir = Paths.get("C:\\WellGroundDeveloper");

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.properties"))
        {
            for (Path entry : stream)
            {
                System.out.println(entry.getFileName());
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
