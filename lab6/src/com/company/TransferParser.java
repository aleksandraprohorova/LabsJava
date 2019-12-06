package com.company;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TransferParser {
    public static ArrayList<Transfer> parse(String path, HashMap<Integer, Account> accounts) {
        ArrayList<Transfer> results = new ArrayList<Transfer>();

        try (FileReader inputFile = new FileReader(path))
        {
            Scanner in = new Scanner(inputFile);
            while(in.hasNext())
            {
                int idOfSender = in.nextInt();
                int idOfRecipient = in.nextInt();
                int amountOfMoney = in.nextInt();
                Transfer transfer = new Transfer(accounts, idOfSender, idOfRecipient, amountOfMoney);
                results.add(transfer);
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
