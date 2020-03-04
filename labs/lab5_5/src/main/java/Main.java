import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lab4.DataBaseManager;
import lab4.ProductItem;
import org.apache.commons.lang3.math.NumberUtils;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
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
            DataBaseManager dataBaseManager = new DataBaseManager(connection, "product");
            Form form = new Form(primaryStage, dataBaseManager);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
