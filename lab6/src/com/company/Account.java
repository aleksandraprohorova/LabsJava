package com.company;

import java.util.Scanner;

public class Account {
    public Account(Scanner in)
    {
        id = in.nextInt();
        rubles = in.nextInt();
    }
    public Account(int id, int rubles)
    {
        this.id = id;
        this.rubles = rubles;
    }
    public int getId()
    {
        return id;
    }
    synchronized public int getRubles()
    {
        return rubles;
    }
    synchronized public void withdrow(int amountOfRubles)
    {
        if (rubles >= amountOfRubles)
        {
            rubles -= amountOfRubles;
        }
        else
        {
            throw new IllegalArgumentException("На счёте недостаточно средств.");
        }
    }
    synchronized public void replenish(int amountOfRubles)
    {
        rubles += amountOfRubles;
    }
    private int id;
    private int rubles;
}
