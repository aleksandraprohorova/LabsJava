package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<Integer, Account> accounts = AccountsParser.parse("accounts");
        ArrayList<Transfer> transfers = TransferParser.parse("transfers", accounts);
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
