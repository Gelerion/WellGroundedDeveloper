package com.denis.design_patterns.mixin_pattern.ref_test;

public class Test {
    public static void main(String[] args) {
        Person denis = new Person("Denis", "1", 1987);
        Person denis2 = new Person("Denis", "1", 1987);

        System.out.println("Compare to: " + denis.compareTo(denis2));
        System.out.println(denis);
    }
}
