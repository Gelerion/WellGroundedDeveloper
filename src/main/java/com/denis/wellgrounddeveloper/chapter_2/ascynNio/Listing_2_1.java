package com.denis.wellgrounddeveloper.chapter_2.ascynNio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Listing_2_1
{
//    ����� AsynchronousFileChannel ������������ � ����� �������, � ������� �������� ������ ��� ��������� ������� �����-������
// � ��������������� ����������� � ������������ ���������� (����������� ���������� ��������� �� ������ ���������� �������� �����-������).
// ���������� ���������� ��� �������� �����-������, �������������� �� ������, �������������� �������������� ����� �� �������,
// ����������� � ���� �������.
//    ��� �������� AsynchronousFileChannel ��� �������� ���� ������� ���� ����� ����� ������������ � ���������������� ����� �������,
// �������� �� ��������� (�������, ��������, ����� �������������� ��������� � ������� ��������). ���������� �� ��������� ��� �������
// ��������������� � ������� ��������� �������, ������������ � ������ AsynchronousChannelGroup.
    public static void main(String[] args)
    {
        try
        {
            Path file = Paths.get("D:\\Games\\Test.txt");

            AsynchronousFileChannel asyncChannel = AsynchronousFileChannel.open(file);

            ByteBuffer buffer = ByteBuffer.allocate(6_518_784); //6.5mb

            Future<Integer> result = asyncChannel.read(buffer, 0);

            while (!result.isDone())
            {
                ProfitCalculator.calculateTax();
            }

            Integer bytesRead = result.get();
            System.out.println("Bytes read [" + bytesRead + "]");

        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        } catch (InterruptedException | ExecutionException e)
        {
            e.printStackTrace();
        }
    }

    private static class ProfitCalculator
    {
        private static int count = 0;

        private static void calculateTax()
        {
            count++;
            for (int i = 1; i < 11; i++)
            {
                System.out.print("Iteration count N:" + count + " ");
                System.out.println("Calculating tax N: " + i);
            }
        }
    }
}
