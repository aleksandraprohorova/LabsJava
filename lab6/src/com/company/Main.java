package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите путь до файла с данными про счета:");
        String pathForAccounts = in.next();
        HashMap<Integer, Account> accounts = AccountsParser.parse(pathForAccounts);
        System.out.println("Введите путь до файла с данными про переводы:");
        String pathForTransfers = in.next();
        ArrayList<Transfer> transfers = TransferParser.parse(pathForTransfers, accounts);
        if (transfers == null)
        {
            System.out.println("Ошибка при обработке входных данных.");
            return;
        }
        for (Transfer transfer: transfers)
        {
            Thread threadForTransfer = new Thread(transfer);
            threadForTransfer.start();
        }
    }
}
