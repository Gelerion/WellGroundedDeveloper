package com.denis.map_reduce.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class SimpleLocals {
    public static void main(String[] args) throws IOException {

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf); //LocalFileSystem

        //Hadoop Path!
        Path path = new Path("/");
        FileStatus[] files = fs.listStatus(path);

        for (FileStatus file : files) {
            System.out.println(file.getPath().getName());
        }


    }
}
