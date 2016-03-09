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


        // ./ (фактически это тот самый каталог, из которого вы запускаетесь) значения не имеет.
        // В данном случае достаточно указать более краткий путь — Listing_2_1.java
        Path normalizedPath = Paths.get("./Listing_2_1.java").normalize();
        // можно отсечь от Path избыточную информацию, воспользовавшись методом normalize() этого пути
        System.out.println("normalizedPath = " + normalizedPath);


        // Вернемся к примеру, где мы работали с операционной системой *nix и имели дело с файлом log1.txt,
        // находящимся в каталоге /usr/logs.
        // Этот каталог на самом деле представляет собой символьную ссылку на каталог /application/logs.
        // С помощью метода toRealPath() вы получаете реальный путь Path к /application/logs/log1.txt.

//        Path realPath = Paths.get("/usr/logs/log1.txt").toRealPath();

    }
}
