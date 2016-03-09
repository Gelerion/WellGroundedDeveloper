package com.denis.golovach.multhithreading.course.lecture_3;

public class FinalFields {
    static NonFinalStr nonFinalStr; //UNsafe publishing
//    или мы можем добавить
    volatile static NonFinalStr nonFinalStr1; //safe publishing

    public static void main(String[] args) {

        new Thread(() -> nonFinalStr = new NonFinalStr(new char[]{'H', 'e', 'l', 'l', 'o'}, 1, 5)).start();


        for (int i = 0; i < 2; i++) {
            try {
                System.out.println(nonFinalStr.value);
                System.out.println(nonFinalStr.offset);
                System.out.println(nonFinalStr.size);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

/**
 * В теории может быть испорченная инициализация
 */
class NonFinalStr {
    char[] value;
    int offset;
    int size;

    public NonFinalStr(char[] value, int offset, int size) {
        this.value = value;
        this.offset = offset;
        this.size = size;
    }
}

/**
 * Класс ВСЕГДА будет правильно инициализирован
 */
class FinalStr {
    final char[] value;
    final int offset;
    final int size;

    public FinalStr(char[] value, int offset, int size) {
        this.value = value;
        this.offset = offset;
        this.size = size;
    }
}