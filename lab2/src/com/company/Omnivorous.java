package com.company;

public class Omnivorous extends Animal {
    Omnivorous(int id, String name, int quantityOfFood) {
        super(id, name, quantityOfFood, "всё");
    }

    @Override
    public int getQuantityOfFood() {
        return quantityOfFood;
    }
}
