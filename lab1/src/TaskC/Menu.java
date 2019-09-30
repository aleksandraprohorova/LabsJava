package TaskC;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    public abstract class Command{
        public abstract void execute(Table table);
    }
    public class CommandAddRecord extends Command {
        @Override
        public void execute(Table table) {
            Scanner in = new Scanner(System.in);
            System.out.println("Введите данные записи:");
            System.out.println("id:");
            int id = in.nextInt();
            System.out.println("author:");
            String author = in.next();
            System.out.println("title:");
            String title = in.next();
            System.out.println("year:");
            int year = in.nextInt();
            table.add(id, author, title, year);
        }
    }
    public class CommandFindRecord extends Command {
        @Override
        public void execute(Table table) {
            System.out.println("Введите поле для поиска: (ID, AUTHOR, TITLE, YEAR)");
            Scanner in = new Scanner(System.in);
            Table.FIELDS field = Table.FIELDS.valueOf(in.next());
            System.out.println("Введите значение для поиска:");
            String value = in.next();
            Table.Record record = table.find(field, value);
            if (record != null)
            {
                record.show();
            }
            else
            {
                System.out.println("There is no such a record");
            }
        }
    }
    public class CommandShowAll extends Command{
        @Override
        public void execute(Table table) {
            table.showAll();
        }
    }
    public class CommandDeleteRecord extends Command {
        @Override
        public void execute(Table table) {
            System.out.println("Введите id удаляемой записи:");
            Scanner in = new Scanner(System.in);
            String id = in.next();
            table.delete(table.find(Table.FIELDS.ID, id));
        }
    }
    public class CommandChangeRecord extends Command {
        @Override
        public void execute(Table table) {
            System.out.println("Введите id изменяемой записи:");
            Scanner in = new Scanner(System.in);
            int id = in.nextInt();
            System.out.println("Введите данные записи:");
            System.out.println("author:");
            String author = in.next();
            System.out.println("title:");
            String title = in.next();
            System.out.println("year:");
            int year = in.nextInt();

            table.change(id, author, title, year);
        }
    }
    enum COMMANDS
    {
        ADD,
        DELETE,
        FIND,
        SHOW
    }
    public static Menu getInstance()
    {
        if (instance == null)
        {
            instance = new Menu();
        }
        return instance;
    }
    public void run()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите команду (ADD / FIND / DELETE / SHOW / ESCAPE):");
        String commandString = in.next();
        while (!commandString.equalsIgnoreCase("escape")){
            try
            {
                commands.get(COMMANDS.valueOf(commandString)).execute(Table.getInstance());
            }catch (RuntimeException exception)
            {
                System.out.println("Invalid data");
            }
            System.out.println("Введите команду (ADD / FIND / DELETE / SHOW / ESCAPE):");
            commandString = in.next();

        }
    }
    private static Menu instance;
    private Menu()
    {
        commands = new HashMap<COMMANDS, Command>();

        commands.put(COMMANDS.ADD, new CommandAddRecord());
        commands.put(COMMANDS.DELETE, new CommandDeleteRecord());
        commands.put(COMMANDS.FIND, new CommandFindRecord());
        commands.put(COMMANDS.SHOW, new CommandShowAll());
    }
    private Map<COMMANDS, Command> commands;

}
