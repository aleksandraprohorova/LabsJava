package com.company;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;


public class Main {

    public static Animal createAnimal(int id, String name, int quantityOfFood, String typeOfFood)
    {
        switch (typeOfFood)
        {
            case "животные":
            {
                return new Predator(id, name, quantityOfFood);
            }
            case "растения":
            {
                return new Herbivorous(id, name, quantityOfFood);
            }
            case "все":
            {
                return new Omnivorous(id, name, quantityOfFood);
            }
        }
        throw new IllegalArgumentException("Неправильный тип еды.");
    }

    public static void main(String[] args) {
        //Vector<Animal> vector = new Vector<Animal>();
        Comparator<Animal> comparator = Comparator.comparing(Animal::getQuantityOfFood).thenComparing(Animal::getName);

        SortedSet<Animal> animals = new TreeSet<Animal>(comparator);
        try {
            FileReader inputFile = new FileReader("input.txt");
            Scanner in = new Scanner(inputFile);
            while (in.hasNextLine())
            {
                int id = in.nextInt();
                String name = in.next();
                String typeOfFood = in.next();
                int quantityOfFood = in.nextInt();

                Animal animal = createAnimal(id, name, quantityOfFood, typeOfFood);
                animals.add(animal);
            }
            inputFile.close();
        } catch (IOException e) {
            System.err.println("Ошибка при открытии или чтении из файла");
            return;
        }
        int size = animals.size();
        Animal[] array = animals.toArray(new Animal[size]);
        FileWriter out = null;
        System.out.println("Имена первых пяти животных в списке:");
        for (int i = 0; i < 5 && i < size; ++i)
        {
            System.out.println(array[i].getName());
        }
        System.out.println("Идентификаторы последних трех животных в списке:");
        for (int i = size - 1; i >= size - 3 && i >= 0; --i)
        {
            System.out.println(array[i].getId());
        }
        try {
            out = new FileWriter("output.txt");
            for (Animal animal: animals)
            {
                animal.show(out);
            }
            out.close();

        } catch (IOException e) {
            System.err.println("Ошибка во время создания или записи в файл");
        }
    }
}
