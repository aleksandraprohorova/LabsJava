package com.company;

public class Predator extends Animal {
    Predator(int id, String name, int quantityOfFood) {
        super(id, name, quantityOfFood, "животные");
    }

    @Override
    public int getQuantityOfFood() {
        return quantityOfFood;
    }
}
