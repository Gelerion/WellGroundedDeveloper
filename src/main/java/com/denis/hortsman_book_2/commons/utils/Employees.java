package com.denis.hortsman_book_2.commons.utils;

import com.denis.hortsman_book_2.commons.Employee;

public class Employees
{
    public static Employee[] createMockEmployees()
    {
        Employee denis = new Employee("Denis Shuvalov", 75000, 1987, 2, 21);
        Employee alena = new Employee("Alena Moroz", 65000, 1991, 4, 13);
        Employee vel = new Employee("Velvel Monastyrshin", 45000, 1983, 10, 2);

        return new Employee[] {
            denis, alena, vel
        };
    }
}
