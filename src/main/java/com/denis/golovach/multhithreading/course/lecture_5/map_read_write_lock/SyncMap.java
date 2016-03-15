package com.denis.golovach.multhithreading.course.lecture_5.map_read_write_lock;

import java.util.HashMap;
import java.util.Map;

/**
 * ����� ������������ ������ ��������� �������
 * ������� ��� ������������ ����������
 */
public class SyncMap {
    //���� ������ �������� ���, �� �� ���������� ���������� �������
    //� ������� ������������� ������� �� ������� ��� �����
    //� �� ������ ������� ��� notify

    //����� ��� ��� ���������
    private Map<Integer, String> map = new HashMap<>();

    public synchronized String get(Integer key) {
        return map.get(key);
    }

    public synchronized String put(Integer key, String value) {
        return map.put(key, value);
    }
}


//������� �������� ������� � �����, ������ �� ���� ������������ ����� �������
//��� ������ ������� � BlockedSet � �����
//��� ��� ��� �� �� ���� �� ��������
class X {
    //���������!!!!
    public static void main(String[] args) {
        SyncMap map = null;
        synchronized (map) {
            while (true) {
                //...
            }
        }
    }
}