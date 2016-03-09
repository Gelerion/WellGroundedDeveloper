package com.denis.wellgrounddeveloper.chapter_2;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Listing_2_2_fileSearchInCatalog
{
//    ��������� ��������� ������ ���������� java.nio.file.DirectoryStream<T> � ����������� ��� ������� �� ������ ��������� ��������� ������� �������� ����������:
//    ??���������� ������ � ��������, �������� �������� � ��� �����;
//    ??�������� ������ � ��������, ��������� �����������-������� (��������, *Foobar*) � ������������ �������� �� ����� MIME (��������, ����� text/xml);
//    ??��������� ����������� �������� �����������, ����������� � �������� � ������� ������ walkFileTree.
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
