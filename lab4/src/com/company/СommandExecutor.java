package com.company;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class СommandExecutor {
    public interface Command
    {
        public void execute(String[] args) throws Exception;
    }

    public static void execute(String line)
    {
        Map<String, Command> accordance = new HashMap<String, Command>();
        accordance.put(
                "pwd", (String[] args) ->
                {
                    if (args.length != 1)
                    {
                        throw new Exception("Неправильное число аргументов.");
                    }
                    System.out.println(System.getProperty("user.dir"));
                }
        );
        accordance.put(
                "ls", (String[] args) ->
                {
                    String directory;
                    if (args.length == 1)
                    {
                        directory = System.getProperty("user.dir");
                    }
                    else if (args.length == 2)
                    {
                        directory = args[1];
                    }
                    else
                    {
                        throw new Exception("Неправильное число аргументов.");
                    }
                    File current = new File(directory);
                    if (current.isDirectory())
                    {
                        File[] files = current.listFiles();
                        if (files != null)
                        {
                            for (File file : files) {
                                if (file != null)
                                {
                                    System.out.println(file.getName());
                                }
                            }
                        }
                    }
                    else
                    {
                        throw new Exception(directory + " не является директорией.");
                    }
                }
        );
        accordance.put(
                "cat", (String[] args) ->
                {
                    if (args.length < 2)
                    {
                        throw new Exception("Неправильное число аргументов.");
                    }
                    String path = args[1];
                    if (path.equals(">") || (path.equals(">>")))
                    {
                        if (args.length != 3)
                        {
                            throw new Exception("Неправильное число аргументов.");
                        }
                        path = args[2];
                        File current = new File(path);
                        if (current.isFile())
                        {
                            if (current.canWrite())
                            {
                                FileWriter out;
                                if   (args[1].equals(">>"))
                                {
                                    out = new FileWriter(path, true);
                                }
                                else
                                {
                                    out = new FileWriter(path);
                                }
                                Scanner in = new Scanner(System.in);
                                System.out.println("Введите 'esc' чтобы завершить запись в файл.");
                                while (in.hasNextLine())
                                {
                                    String tmp = in.nextLine();
                                    if (tmp.equalsIgnoreCase("esc"))
                                    {
                                        break;
                                    }
                                    out.write(tmp + "\n");
                                }
                                System.out.println("Запись в файл завершена");
                                out.close();
                            }
                            else
                            {
                                throw new Exception(path + " не доступен для записи.");
                            }
                        }
                        else
                        {
                            throw new Exception(path + " не является файлом.");
                        }
                    }
                    else
                    {
                        if (args.length != 2)
                        {
                            throw new Exception("Неправильное число аргументов.");
                        }
                        File current = new File(path);
                        if (current.isFile())
                        {
                            if (current.canRead())
                            {
                                FileReader inputFile = new FileReader(path);
                                Scanner in = new Scanner(inputFile);
                                while (in.hasNextLine())
                                {
                                    System.out.println(in.nextLine());
                                }
                            }
                            else
                            {
                                throw new Exception("Нечитаемый файл.");
                            }
                        }
                        else
                        {
                            throw new Exception(path + " не является файлом.");
                        }
                    }


                }
        );
        accordance.put(
                "touch", (String[] args) ->
                {
                    if (args.length != 2)
                    {
                        throw new Exception("Неправильное число аргументов.");
                    }
                    FileWriter out = new FileWriter(args[1]);
                    out.close();
                }
        );
        accordance.put(
                "rm", (String[] args) ->
                {
                    if (args.length != 2)
                    {
                        throw new Exception("Неправильное число аргументов.");
                    }
                    File file = new File(args[1]);
                    if (!file.delete())
                    {
                        throw new Exception(args[1] + " не существует или не может быть удален.");
                    }
                }
        );
        accordance.put(
                "help", (String[] args) ->
                {
                    if (args.length != 1)
                    {
                        throw new Exception("Неправильное число аргументов.");
                    }
                    System.out.println("Доступные команды:\n"
                    + "pwd" + "\t" + "текущая директория;" + "\n"
                    + "ls [директория]"  + "\t" + "содержимое директории;" + "\n"
                    + "cat [путь до файла]" + "\t" + "содержимое файла;" + "\n"
                    + "cat > [путь до файла]" + "\t" + "запись в файл;" + "\n"
                    + "cat >> [путь до файла]" + "\t" + "дозапись в файл;" + "\n"
                    + "touch [путь до файла]" + "\t" + "создать файл;" + "\n"
                    + "rm [путь до файла]" + "\t" + "удалить файл." + "\n");
                }
        );
        String[] args = line.split(" ");

        Command command = accordance.get(args[0]);

        try {
            if (command != null)
            {
                command.execute(args);
            }
            else
            {
                throw new Exception("Несуществующая команда.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Для получения справки по командам введите 'help'.");
        }
    }
}
