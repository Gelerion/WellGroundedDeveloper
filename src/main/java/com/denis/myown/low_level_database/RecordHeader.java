package com.denis.myown.low_level_database;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Hold information about a record
 * Record -- A collection of related data stored in a file.
 * A record typically has multiple fields, each being a named and typed item of information.
 */
public class RecordHeader {

    //File pointer to the first byte of record data (8 bytes)
    protected long dataPointer;

    //Actual number of bytes of data held in this record (4 bytes)
    protected int dataCount;

    //Number of bytes that this record can hold (4 bytes)
    protected int dataCapacity;

    //Indicates this header's position in the file index
    protected int indexPosition;

    public RecordHeader() {
    }

    public RecordHeader(long dataPointer, int dataCapacity) {

        if (dataCapacity < 1) {
            throw new IllegalArgumentException("Bad record size: " + dataCapacity);
        }

        this.dataPointer = dataPointer;
        this.dataCapacity = dataCapacity;
        this.dataCount = 0;
    }

    public int getFreeSpace() {
        return dataCapacity - dataCount;
    }

    public int getDataCapacity() {
        return dataCapacity;
    }

    public int getIndexPosition() {
        return indexPosition;
    }

    public RecordHeader setIndexPosition(int indexPosition) {
        this.indexPosition = indexPosition;
        return this;
    }

    public void read(DataInput in) throws IOException {
        dataPointer = in.readLong();
        dataCapacity = in.readInt();
        dataCount = in.readInt();
    }

    public void write(DataOutput out) throws IOException {
        out.writeLong(dataPointer);
        out.writeInt(dataCapacity);
        out.writeInt(dataCount);
    }
}
