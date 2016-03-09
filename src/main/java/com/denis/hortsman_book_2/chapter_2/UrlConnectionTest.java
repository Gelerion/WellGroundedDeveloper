package com.denis.hortsman_book_2.chapter_2;

import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UrlConnectionTest {
    public static void main(String[] args) throws IOException {

        String urlName;
        if (args.length > 0) urlName = args[0];
        else urlName = "http://horstmann.com";

        URL url = new URL(urlName);
        URLConnection connection = url.openConnection();

        //������ ����� ��������

        //���������� ��� ������������ � ������
//        if (args.length > -1) {
//            String username = args[1];
//            String password = args[2];
        String username = "denis";
        String password = "shuvalov";
        String input = username + ":" + password;
        String encoding = base64Encode(input);
        System.out.println("encoding = " + encoding);
        connection.setRequestProperty("Authorization", "Basic " + encoding);
//        }
        Map<String, List<String>> headers = connection.getHeaderFields();

        for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
            String key = entry.getKey();
            for (String value : entry.getValue()) {
                System.out.println(key + ":" + value);
            }
        }

        System.out.println("--------------------------------");
        System.out.println("getContentType: " + connection.getContentType());
        System.out.println("getContentLength: " + connection.getContentLength());
        System.out.println("getContentEncoding: " + connection.getContentEncoding());
        System.out.println("getDate: " + connection.getDate());
        System.out.println("getExpiration: " + connection.getExpiration());
        System.out.println("getLastModified: " + connection.getLastModified());
        System.out.println("--------------------------------");

        Scanner in = new Scanner(connection.getInputStream());

        for (int n = 1; in.hasNextLine() && n <= 10; n++) {
            System.out.println(in.nextLine());
        }

        if(in.hasNextLine()) System.out.println("....");
    }

    private static String base64Encode(String input) {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        Base64OutputStream out = new Base64OutputStream(bOut);
        try {
            out.write(input.getBytes());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bOut.toString();
    }
}

/**
 * ���� ��������� ������ ����������� ����� ������ ������ � �� ��������� ��
 * ��������� Base64 ��� ������ ��� ����� ���������� �������� ���������.
 * ������ ����� ����� ���������� �� ������� (�������)
 * ���� ���-�� �������� ������ �� ������ ��� �� ��������� ������ ��
 * ������ �������� ����������� ���� ��� ����� �������� ���������
 * ������ ������� ������ ������� �� ����� ��� �� 76 ��������
 */
class Base64OutputStream extends FilterOutputStream {

    private static char[] toBase64 = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private int col = 0;
    private int i = 0;
    private int[] inbuf = new int[3];

    public Base64OutputStream(OutputStream out) {
        super(out);
    }

    public void write(int c) throws IOException {
        inbuf[i] = c;
        i++;
        if (i == 3) {

            if (col > 76) {
                super.write('\n');
                col = 0;
            }

            super.write(toBase64[(inbuf[0] & 0xFC) >> 2]);
            super.write(toBase64[(inbuf[0] & 0x03) << 4] | ((inbuf[1] & 0xF0) >> 4));
            super.write(toBase64[(inbuf[1] & 0x0F) << 2] | ((inbuf[2] & 0xC0) >> 6));
            super.write(toBase64[(inbuf[2] & 0x3F) >> 2]);
            col += 4;
            i = 0;
        }
    }

    public void flush() throws IOException {
        if (i > 0 && col >= 76) {
            super.write('\n');
            col = 0;
        }

        if (i == 1) {
            super.write(toBase64[(inbuf[0] & 0xFC) >> 2]);
            super.write(toBase64[(inbuf[0] & 0xFC) << 4]);
            super.write('=');
            super.write('=');
        } else if (i == 2) {
            super.write(toBase64[(inbuf[0] & 0xFC) >> 2]);
            super.write(toBase64[(inbuf[0] & 0x03) << 4] | ((inbuf[1] & 0xF0) >> 4));
            super.write(toBase64[(inbuf[1] & 0x0F) << 2]);
            super.write('=');
        }
    }
}




























