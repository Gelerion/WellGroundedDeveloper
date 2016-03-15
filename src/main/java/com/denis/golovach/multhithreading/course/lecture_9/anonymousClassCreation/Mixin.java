package com.denis.golovach.multhithreading.course.lecture_9.anonymousClassCreation;

import java.lang.reflect.Field;

//Mixin / trait
interface Xml {
    default String toXml() throws IllegalAccessException {
        //обходит все пояли и строит xml

        //В Runtime вызывает тот класс который унаследовал интерфейс
//        this.getClass()

        Class clazz = this.getClass();
        Field[] fields = clazz.getDeclaredFields();
        fields[0].setAccessible(true);
        String result = "";
        result += "<" + clazz.getSimpleName() + ">";
        result += fields[0].getName() + " = " + fields[0].get(this);
        result += "</" + clazz.getSimpleName() + ">";
        return result;
    }
}

public class Mixin implements Xml {
    private int age = 43;
    private String name = "Denis";
}

class Test{
    public static void main(String[] args) throws IllegalAccessException {
        Mixin mixin = new Mixin();
        String xml = mixin.toXml();
        System.out.println("xml = " + xml);
    }
}
