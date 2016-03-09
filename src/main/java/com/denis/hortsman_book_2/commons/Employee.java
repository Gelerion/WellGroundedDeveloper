package com.denis.hortsman_book_2.commons;

import java.io.Serializable;
import java.time.LocalDate;

public class Employee implements Serializable
{

    private String name;
    private LocalDate hireDay;
    private int salary;

    public Employee()
    {
    }

    public Employee(String name, int salary, int year, int month, int day)
    {

        this.name = name;
        this.salary = salary;
        this.hireDay = LocalDate.of(year, month, day);
    }

    public String getName()
    {
        return name;
    }

    public int getSalary()
    {
        return salary;
    }

    public LocalDate getHireDay()
    {
        return hireDay;
    }

    public Employee setSalary(int salary)
    {
        this.salary = salary;
        return this;
    }

    public Employee setName(String name)
    {
        this.name = name;
        return this;
    }



    public Employee setHireDay(LocalDate hireDay)
    {
        this.hireDay = hireDay;
        return this;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("Employee{");
        sb.append("name='").append(name).append('\'');
        sb.append(", hireDay=").append(hireDay);
        sb.append(", salary=").append(salary);
        sb.append('}');
        return sb.toString();
    }
}

