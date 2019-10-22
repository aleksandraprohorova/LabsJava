package com.company;

public class Herbivorous extends Animal {
    Herbivorous(int id, String name, int quantityOfFood) {
        super(id, name, quantityOfFood, "растения");
    }

    @Override
    public int getQuantityOfFood() {
        return quantityOfFood;
    }
}
