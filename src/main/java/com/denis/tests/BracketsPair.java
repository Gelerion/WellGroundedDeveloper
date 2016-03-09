package com.denis.tests;

import java.util.HashMap;
import java.util.Map;

public class BracketsPair
{
    public static void main(String[] args)
    {
        System.out.println("() -> " + validate("()")); //true
        System.out.println("[) -> " + validate("[)")); //false
        System.out.println("as(as){} -> " + validate("as(as){}")); //true
        System.out.println("as(as)[} -> " + validate("as(as[}")); //false
        System.out.println("ad(()fg(as)[} -> " + validate("ad(()fg(as)[}")); //false
        System.out.println("ad(())fg(as)[()]{{}} -> " + validate("ad(())fg(as)[()]{{}}")); //true
        System.out.println("ad(())fg(as)[()]{{]}} -> " + validate("ad(())fg(as)[()]{{]}}")); //false
    }

    static boolean validate(String str)
    {
        Map<Character, Integer> brackets = new HashMap<>();
        brackets.put('(', 0); //0
        brackets.put('[', 0); //1
        brackets.put('{', 0); //2
        brackets.put(')', 0); //3
        brackets.put(']', 0); //4
        brackets.put('}', 0); //5

        for (int i = 0; i < str.length(); i++)
        {
            char current = str.charAt(i);

            if (brackets.containsKey(current))
            {
                brackets.put(current, brackets.get(current) + 1);
            }
        }

        return brackets.get('(').equals(brackets.get(')')) &&
                brackets.get('[').equals(brackets.get(']')) &&
                brackets.get('{').equals(brackets.get('}'));
    }
}
