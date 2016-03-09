package com.denis.wellgrounddeveloper.chapter_2;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class listing_2_3_fileTreeWalk
{
    public static void main(String[] args) throws IOException
    {
        Path startingDir = Paths.get("D:\\WellGroundDeveloper");

        Files.walkFileTree(startingDir, new FindJavaVisitor());

    }

    private static class FindJavaVisitor extends SimpleFileVisitor<Path>
    {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
        {
            if (file.toString().endsWith(".java"))
            {
                System.out.println(file.getFileName());
            }
            return FileVisitResult.CONTINUE;
        }
    }

}
