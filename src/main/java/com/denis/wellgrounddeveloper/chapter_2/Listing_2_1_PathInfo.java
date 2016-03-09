package com.denis.wellgrounddeveloper.chapter_2;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Listing_2_1_PathInfo
{
    public static void main(String[] args)
    {
        Path listing = Paths.get("D:\\Java\\Git");


        System.out.println("File Name [" + listing.getFileName() + "]");
        System.out.println("Number of Name Elements in the Path [" + listing.getNameCount() + "]");
        System.out.println("Parent Path [" + listing.getParent() + "]");
        System.out.println("Root of Path [" + listing.getRoot() + "]");
        System.out.println("Subpath from Root, 2 elements deep [" + listing.subpath(0, 2) + "]");


        // ./ (���������� ��� ��� ����� �������, �� �������� �� ������������) �������� �� �����.
        // � ������ ������ ���������� ������� ����� ������� ���� � Listing_2_1.java
        Path normalizedPath = Paths.get("./Listing_2_1.java").normalize();
        // ����� ������ �� Path ���������� ����������, ���������������� ������� normalize() ����� ����
        System.out.println("normalizedPath = " + normalizedPath);


        // �������� � �������, ��� �� �������� � ������������ �������� *nix � ����� ���� � ������ log1.txt,
        // ����������� � �������� /usr/logs.
        // ���� ������� �� ����� ���� ������������ ����� ���������� ������ �� ������� /application/logs.
        // � ������� ������ toRealPath() �� ��������� �������� ���� Path � /application/logs/log1.txt.

//        Path realPath = Paths.get("/usr/logs/log1.txt").toRealPath();

    }
}
