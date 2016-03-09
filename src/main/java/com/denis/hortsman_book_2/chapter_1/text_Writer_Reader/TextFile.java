package com.denis.hortsman_book_2.chapter_1.text_Writer_Reader;

import com.denis.hortsman_book_2.commons.Employee;
import com.denis.hortsman_book_2.commons.utils.Employees;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.Scanner;

public class TextFile
{
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, InterruptedException
    {
        Employee[] employees = Employees.createMockEmployees();

        try (PrintWriter out = new PrintWriter("employee.txt", "UTF-8"))
        {
            writeData(out, employees);
        }

        try(Scanner in = new Scanner(new FileInputStream("employee.txt"), "UTF-8"))
        {
            Thread t1 = new Thread(() -> {
                try (Scanner in1 = new Scanner(new FileInputStream("employee.txt"), "UTF-8"))
                {
                    Employee[] data = readData(in1);
                    for (Employee e : data)
                    {
                        System.out.println(e);
                    }
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            });

            Thread t2 = new Thread(() -> {
                try (Scanner in1 = new Scanner(new FileInputStream("employee.txt"), "UTF-8"))
                {
                    Employee[] data = readData(in1);
                    for (Employee e : data)
                    {
                        System.out.println(e);
                    }
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            });

            Thread t3 = new Thread(() -> {
                try (Scanner in1 = new Scanner(new FileInputStream("employee.txt"), "UTF-8"))
                {
                    Employee[] data = readData(in1);
                    for (Employee e : data)
                    {
                        System.out.println(e);
                    }
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            });

            t1.start();
            t2.start();
            t3.start();

//            t1.join();
//            t2.join();




/*            Employee[] data = readData(in);
            for (Employee e : data)
            {
                System.out.println(e);
            }*/
        }
    }

    private static Employee[] readData(Scanner in)
    {
        int length = in.nextInt();
        System.out.println("length = " + length);
        in.nextLine(); ////!!
        Employee[] result = new Employee[length];
        for (int i = 0; i < length; i++)
        {
            result[i] = readEmployee(in);
        }

        return result;
    }

    private static Employee readEmployee(Scanner in)
    {
        Employee result = new Employee();
        String str = in.nextLine();
        String[] tokens = str.split("\\|");
        int year = Integer.parseInt(tokens[1]);
        int month = Integer.parseInt(tokens[2]);
        int day = Integer.parseInt(tokens[3]);
        result.setName(tokens[0]);
        result.setHireDay(LocalDate.of(year, month, day));
        return result;
    }

    private static void writeData(PrintWriter out, Employee... employees)
    {
        //write count of empl's
        out.println(employees.length);

        for (Employee employee : employees)
        {
            writeEmployee(out, employee);
        }
    }

    private static void writeEmployee(PrintWriter out, Employee e)
    {
        LocalDate hireDay = e.getHireDay();
        out.println(e.getName()
                + "|" + hireDay.getYear()
                + "|" + hireDay.getMonthValue()
                + "|" + hireDay.getDayOfMonth());
    }
}
