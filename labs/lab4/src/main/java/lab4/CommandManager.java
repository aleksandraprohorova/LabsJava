package lab4;

import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.Map;

public class CommandManager {

    public interface CommandParser{
        Command parse(String[] args);
    }
    public interface Command{
        String execute(DataManager dataManager);
    }
    public static final Command commandInvalid = (DataManager)->{
        return "Error occured while parsing command.\n";
    };

    private static void checkArguments(String[] args, int expected){
        if (args.length != expected)
        {
            throw new IllegalArgumentException("Wrong number of parameters. Expected " + expected + " parameters. " +
                    args.length + " provided");
        }
    }

    public static final CommandParser parseCommandAdd = (String[] args) -> {
        checkArguments(args, 3);
        String title = args[1];
        int price = Integer.parseInt(args[2]);
        return (DataManager dataManager)->{
            return dataManager.add(new ProductItem(title, price));
        };
    };
    public static final CommandParser parseCommandDelete = (String[] args) -> {
        checkArguments(args, 2);
        String title = args[1];
        return (DataManager dataManager) -> {
            return dataManager.delete(title);
        };
    };
    public static final CommandParser parseCommandShowAll = (String[] args) -> {
        checkArguments(args, 1);
        return DataManager::showAll;
    };
    public static final CommandParser parseCommandPrice = (String[] args) -> {
        checkArguments(args, 2);
        String title = args[1];
        return (DataManager dataManager)->{
            return dataManager.showPrice(title);
        };
    };
    public static final CommandParser parseCommandChangePrice = (String[] args) -> {
        checkArguments(args, 3);
        String title = args[1];
        int newPrice = Integer.parseInt(args[2]);
        return (DataManager dataManager) -> {
            return dataManager.changePrice(title, newPrice);
        };
    };
    public static final CommandParser parseCommandFilterByPrice = (String[] args) -> {
        checkArguments(args, 3);
        int priceFrom = Integer.parseInt(args[1]);
        int priceTo = Integer.parseInt(args[2]);
        return (DataManager dataManager) -> {
            return dataManager.filterByPrice(priceFrom, priceTo);
        };
    };
    private final static Map<String, CommandParser> commands = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("/add", parseCommandAdd),
            new AbstractMap.SimpleEntry<>("/delete", parseCommandDelete),
            new AbstractMap.SimpleEntry<>("/show_all", parseCommandShowAll),
            new AbstractMap.SimpleEntry<>("/price", parseCommandPrice),
            new AbstractMap.SimpleEntry<>("/change_price", parseCommandChangePrice),
            new AbstractMap.SimpleEntry<>("/filter_by_price", parseCommandFilterByPrice)
    );

    public static Command parse(String line)
    {
        String[] args = line.split(" ");
        CommandParser commandParser = commands.get(args[0]);
        try
        {
            if (commandParser != null)
            {
                return commandParser.parse(args);
            }
            else {
                return commandInvalid;
            }
        }
        catch (Exception e){
            return commandInvalid;
        }
    }


}
