package com.company;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        HashMap<String, String> properties;
        try {
            System.out.println("Введите путь до файла:");
            String path = in.next();
            System.out.println("Введите символ разделителя:");
            String delimiter = in.next();
            properties = PropertiesParser.parse(path, delimiter);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Введите значение ключа:");
        while (in.hasNext())
        {
            String key = in.next();
            System.out.println(properties.getOrDefault(key, "Некорректное значение ключа."));
            System.out.println("Введите значение ключа:");
        }

    }
}
