package com.denis.wellgrounddeveloper.chpter_1;

import java.io.IOException;
import java.sql.SQLException;

public class FinalRethrow
{
    public static void main(String[] args)
    {
        try
        {
//            withoutFinal();
            withFinal();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    static void withFinal() throws Exception
    {
        try {
            doSomethingWhichMightThrowIOException();
            doSomethingElseWhichMightThrowSQLException();
        } catch (final Exception e) {
            throw e;
        }
    }

    static void withoutFinal() throws Exception
    {
        try {
            doSomethingWhichMightThrowIOException();
            doSomethingElseWhichMightThrowSQLException();
        } catch (Exception e) {
            throw e;
        }
    }

    static void doSomethingWhichMightThrowIOException() throws IOException
    {
        throw new IOException("IOEx");
    }

    static void doSomethingElseWhichMightThrowSQLException() throws SQLException
    {
        throw new SQLException("IOEx");
    }
}
