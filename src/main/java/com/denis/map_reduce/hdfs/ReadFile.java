package com.denis.map_reduce.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class ReadFile {
    public static void main(String[] args) throws IOException {
        Path file = new Path("/path/file.txt");
        FileSystem fs = FileSystem.get(new Configuration());

        InputStream in = null;
        try {
            in = fs.open(file);
            IOUtils.copyBytes(in, System.out, 4096);
        } finally {
            IOUtils.closeStream(in);
        }
    }
}
