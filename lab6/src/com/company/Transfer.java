package com.company;

import java.util.HashMap;

public class Transfer implements Runnable {
    public Transfer(HashMap<Integer, Account> accounts, int idOfSender, int idOfRecipient, int amountOfMoney)
    {
        this.accounts = accounts;
        this.idOfSender = idOfSender;
        this.idOfRecipient = idOfRecipient;
        this.amountOfMoney = amountOfMoney;
    }
    @Override
    public void run() {
        System.out.println("Выполняется перевод со счета " + idOfSender + " на счет " + idOfRecipient + ". Сумма: " + amountOfMoney);
        if (accounts.get(idOfSender).getRubles() < amountOfMoney)
        {
            System.out.println("На счете недостаточно средств. Перевод не осуществлен.");
        }
        else
        {
            accounts.get(idOfSender).withdrow(amountOfMoney);
            accounts.get(idOfRecipient).replenish(amountOfMoney);
        }
    }
    private HashMap<Integer, Account> accounts;
    private int idOfSender;
    private int idOfRecipient;
    private int amountOfMoney;
}
