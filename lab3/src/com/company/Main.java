package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        CustomStringBuilder stringBuilder = new CustomStringBuilder("обычная строка");;
        stringBuilder.append("новая обычная строка");
        stringBuilder.insert(14, " + ");

        System.out.println(stringBuilder.toString());
        System.out.println("3x undo:");
        // последнее undo() не делает ничего
        stringBuilder.undo();
        stringBuilder.undo();
        stringBuilder.undo();
        System.out.println(stringBuilder.toString());

    }
}
