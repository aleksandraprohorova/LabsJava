package com.company;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

public class PropertiesParser {
    public static HashMap<String, String> parse(String path, String delimiter) throws Exception {
        HashMap<String, String> results = new HashMap<String, String>();
        FileReader inputFile = new FileReader(path);
        Scanner in = new Scanner(inputFile);
        while(in.hasNext())
        {
            String key, value;
            key = in.next();
            if (!in.hasNext() || !in.next().equals(delimiter))
            {
                throw new Exception("Некорректный формат данных.");
            }
            if (!in.hasNext())
            {
                throw new Exception("Некорректный формат данных.");
            }
            value = in.next();
            results.put(key, value);
        }
        in.close();
        inputFile.close();
        return results;
    }
}
