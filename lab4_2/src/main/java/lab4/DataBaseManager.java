package lab4;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

public class DataBaseManager {
    Connection connection;
    String tableName;

    public DataBaseManager(Connection connection, String tableName){
        this.connection = connection;
        this.tableName = tableName;
    }

    String add(Item item) {
        StringBuilder sqlQuery = new StringBuilder("INSERT INTO " + tableName + " VALUES (");
        Collection<String> args = item.getData();
        for (String arg: args) {
            sqlQuery.append('\'').append(arg).append('\'').append(",");
        }
        sqlQuery.deleteCharAt(sqlQuery.length() - 1);
        sqlQuery.append(");");

        try
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlQuery.toString());
            statement.close();
        } catch (SQLException e) {
            return e.getMessage() + "\n";
        }
        return "";
    }
    String delete(String title) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM " + tableName + " WHERE " + "title = '" + title + "';");
            statement.close();
        } catch (SQLException e) {
            return e.getMessage() + "\n";
        }
        return "";
    }
    String showPrice(String title) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName + " WHERE " + "title = '" + title + "';");
            StringBuilder result = new StringBuilder();
            if (resultSet.next())
            {
                result.append(resultSet.getString("id")).append(" ").append(resultSet.getString("prodid")).append(" ").append(resultSet.getString("title")).append(" ").append(resultSet.getString("cost"));
            }
            else{
                result.append("There is no such product.");
            }
            result.append("\n");
            resultSet.close();
            statement.close();
            return result.toString();
        } catch (SQLException e) {
            return e.getMessage() + "\n";
        }
    }
    String changePrice(String title, int newPrice) {

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE " + tableName + " SET cost = " + newPrice + " WHERE title = '" + title + "';");
            statement.close();
        } catch (SQLException e) {
            return e.getMessage() + "\n";
        }
        return "";
    }
    String filterByPrice(int priceFrom, int priceTo) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from " + tableName +
                    " WHERE cost >= " + priceFrom + " AND cost <= " + priceTo + ";");
            StringBuilder result = new StringBuilder();
            while (resultSet.next())
            {
                result.append(resultSet.getString("id")).append(" ").append(resultSet.getString("prodid")).append(" ").append(resultSet.getString("title")).append(" ").append(resultSet.getString("cost"));
                result.append("\n");
            }
            resultSet.close();
            statement.close();
            return result.toString();
        } catch (SQLException e) {
            return e.getMessage() + "\n";
        }

    }
    String showAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from " + tableName + ";");
            StringBuilder result = new StringBuilder();
            while (resultSet.next())
            {
                result.append(resultSet.getString("id")).append(" ").append(resultSet.getString("prodid")).append(" ").append(resultSet.getString("title")).append(" ").append(resultSet.getString("cost"));
                result.append("\n");
            }
            resultSet.close();
            statement.close();
            return result.toString();
        } catch (SQLException e) {
            return e.getMessage() + "\n";
        }
    }

}
