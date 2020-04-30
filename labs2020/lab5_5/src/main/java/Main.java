import javafx.application.Application;
import javafx.stage.Stage;
import lab4.DataBaseManager;
import lab4.DataBaseMySqlManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Main extends Application {
    private Connection connection;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop()
    {
        try{
            connection.close();
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("lab5");

        String url = "jdbc:mysql://localhost/mydb?serverTimezone=Europe/Moscow&useSSL=false&useUnicode=true&characterEncoding=UTF8";
        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "polypoly120");

        //initializeInterface(primaryStage);

        try {
            connection = DriverManager.getConnection(url, properties);
            lab4.Main.initialize(connection);
            DataBaseManager dataBaseManager = new DataBaseMySqlManager(connection, "product");
            Form form = new Form(primaryStage, dataBaseManager);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
