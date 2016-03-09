package com.denis.design_patterns.mixin_pattern;

import java.util.Objects;

public class Person extends AbstractObjectSupport<Person> {
    private final String name;
    private final String email;
    private final int born;

    public Person(String name, String email, int born) {
        this.name = Objects.requireNonNull(name);
        this.email = email;
        this.born = born;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getBorn() {
        return born;
    }

    @Override
    protected Object[] members() {
        return mkArray(getName(), getEmail(), getBorn());
    }

    @Override
    public Object[] names() {
        return mkArray("Name", "Email", "Born");
    }

    @Override
    protected Comparable<?>[] compareToMembers() {
        return mkComparableArray(getName());
    }
}












































