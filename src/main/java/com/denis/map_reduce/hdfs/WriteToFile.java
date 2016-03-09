package com.denis.map_reduce.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class WriteToFile {
    public static void main(String[] args) throws IOException {
        String text = "Hello world in HDFS";
        BufferedInputStream in = new BufferedInputStream(new ByteArrayInputStream(text.getBytes()));

        Path file = new Path("/path/file.txt");
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        FSDataOutputStream out = fs.create(file);
        IOUtils.copyBytes(in, out, conf);

    }
}
