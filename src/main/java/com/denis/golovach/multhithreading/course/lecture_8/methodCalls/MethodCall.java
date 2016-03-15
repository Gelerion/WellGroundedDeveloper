package com.denis.golovach.multhithreading.course.lecture_8.methodCalls;

public class MethodCall {
    public static void main(String[] args) {
//        MethodCall::printHello; //нельзя так просто написать как нельзя написать 1;

        // так как ничего не позвращяет то можно. Важны возвращаяемые типы метода
        V voidRef = MethodCall::printHello;

        //Это не совсем функциональное прогрммирование
        I intRef = MethodCall::returnInt;
        int x = intRef.f();
        System.out.println("x = " + x);

        //НО эти типы никак не связаны!
//        I1 ref = intRef;

        //Зато можно в полу функциональном стиле
        //Функционально: есть общий потомок который нечего неполучает и возвращяет число
        I1 ref1 = intRef::f;

        //А можно и так
        //Принцип Барбары Лиско
        //!!!Возвращяемы тип НЕ важен. Важно количество и тип аргументов
        I2 ref2 = intRef::f;
    }

    public static void printHello() {
        System.out.println("Hello!");
    }

    public static int returnInt() {
        return 42;
    }

    interface V {
        void f();
    }

    interface I {
        int f();
    }

    interface I1 {
        int g();
    }

    interface I2 {
        int g();
    }

}
