package com.company;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class AccountsParser {
    public static HashMap<Integer, Account> parse(String path) {
        HashMap<Integer, Account> results = new HashMap<Integer, Account>();

        try (FileReader inputFile = new FileReader(path))
        {
            Scanner in = new Scanner(inputFile);
            while(in.hasNext())
            {
                Account account = new Account(in);
                results.put(account.getId(), account);
            }
            in.close();
            return results;
        } catch (IOException e)
        {
            System.out.println("Ошибка при работе с файлом.");
            System.out.println(e.getMessage());
        }
        return null;
    }
}
