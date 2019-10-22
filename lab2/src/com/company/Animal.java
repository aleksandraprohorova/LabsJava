package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class Animal {
    Animal(int id, String name, int quantityOfFood, String typeOfFood) {
        this.id = id;
        this.name = name;
        this.quantityOfFood = quantityOfFood;
        this.typeOfFood = typeOfFood;
    }
    public void show(FileWriter out) throws IOException {
        out.write(id + " " + name + " " + typeOfFood + " " + quantityOfFood + "\n");
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTypeOfFood() {
        return typeOfFood;
    }
    public abstract int getQuantityOfFood();

    protected int id;
    protected String name;
    protected int quantityOfFood;
    protected String typeOfFood;
}
