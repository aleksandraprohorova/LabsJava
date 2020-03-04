package lab4;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void initialize(Connection connection)
    {
        try {
            Statement statement = connection.createStatement();
            String sqlQuery = "DROP TABLE if exists product";
            statement.executeUpdate(sqlQuery);
            sqlQuery = "CREATE TABLE product" + "(" +
                    "id INTEGER not NULL, " +
                    "prodid INTEGER not NULL UNIQUE," +
                    "title VARCHAR(50) not NULL UNIQUE, " +
                    "cost INTEGER not NULL," +
                    "PRIMARY KEY (id)" +
                    ");";
            statement.executeUpdate(sqlQuery);
            statement.close();

            DataBaseManager dataBaseManager = new DataBaseManager(connection, "product");
            for (int i = 1; i < 11; ++i)
            {
                dataBaseManager.add(new ProductItem("product" + i, i * 10));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String... args)
    {
        String url = "jdbc:mysql://localhost/mydb?serverTimezone=Europe/Moscow&useSSL=false&useUnicode=true&characterEncoding=UTF8";
        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "polypoly120");
        properties.put("charSet", "UTF8");

        try {
            Connection connection = DriverManager.getConnection(url, properties);
            initialize(connection);

            Scanner in = new Scanner(System.in);
            DataBaseManager dataBaseManager = new DataBaseManager(connection, "product");
            dataBaseManager.showAll();
            while(in.hasNextLine())
            {
                System.out.print(CommandManager.parse(in.nextLine()).execute(dataBaseManager));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }
}
