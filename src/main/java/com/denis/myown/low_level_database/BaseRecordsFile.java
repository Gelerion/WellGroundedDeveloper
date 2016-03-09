package com.denis.myown.low_level_database;

import com.denis.myown.low_level_database.exception.RecordsFileException;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class BaseRecordsFile {

    //the database file
    private RandomAccessFile file;

    protected BaseRecordsFile(String dbPath, int initialSize) throws IOException {
        File f = new File(dbPath);

        if (f.exists()) {
            throw new RecordsFileException("Database already exist: " + dbPath);
        }

        this.file = new RandomAccessFile(f, "rw");


    }
}
