import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lab4.DataBaseManager;
import lab4.ProductItem;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Collection;
import java.util.List;

public class Form {
    private final TextField searchLine = new TextField();
    private final TextField titleField = new TextField();
    private final TextField costField = new TextField();
    private final TextField lowBoundPrice = new TextField();
    private final TextField upperBoundPrice = new TextField();

    private final Button addButton = new Button("Add");
    private final Button showAllButton = new Button("Show all");
    private final Button findButton = new Button("Find");
    private final Button filterByPriceButton = new Button("Filter by price");
    private final Button deleteButton = new Button("Delete");

    private final Collection<Button> buttons = List.of(
            addButton, showAllButton, findButton, filterByPriceButton, deleteButton);
    private final ObservableList<String> products = FXCollections.observableArrayList();
    private final ListView<String> productsListView = new ListView<>(products);

    public Form(Stage primaryStage, DataBaseManager dataBaseManager)
    {
        initializeInterface(primaryStage);
        setActions(dataBaseManager);
    }

    private void initializeInterface(Stage primaryStage)
    {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

        HBox boxForButtons = new HBox();
        //boxForButtons.setAlignment(Pos.TOP_CENTER);
        boxForButtons.setSpacing(10);
        boxForButtons.getChildren().addAll(buttons);

        searchLine.setPromptText("Enter the title");
        searchLine.setMinWidth(root.getMaxWidth());
        searchLine.setFocusTraversable(false);

        HBox boxForPrice = new HBox();
        boxForPrice.setSpacing(10);
        boxForPrice.setAlignment(Pos.CENTER_LEFT);
        boxForPrice.getChildren().addAll(searchLine, findButton);

        HBox boxForFiltering = new HBox();
        boxForFiltering.setSpacing(10);
        boxForFiltering.setAlignment(Pos.CENTER_RIGHT);
        boxForFiltering.getChildren().addAll(lowBoundPrice, upperBoundPrice, filterByPriceButton);

        HBox boxForSearch = new HBox();
        boxForSearch.setSpacing(10);
        boxForSearch.getChildren().addAll(boxForPrice, boxForFiltering);

        HBox boxForNewRecord = new HBox();
        boxForNewRecord.setSpacing(10);
        boxForNewRecord.setAlignment(Pos.TOP_CENTER);
        boxForNewRecord.getChildren().addAll(titleField, costField, addButton, deleteButton);

        titleField.setPromptText("title");
        costField.setPromptText("cost");
        lowBoundPrice.setPromptText("low bound");
        upperBoundPrice.setPromptText("upper bound");

        lowBoundPrice.textProperty().addListener((observable, oldValue, newValue) ->{
            if (!NumberUtils.isParsable(newValue) && !newValue.isEmpty())
            {
                lowBoundPrice.setText(oldValue);
            }
        });
        upperBoundPrice.textProperty().addListener((observable, oldValue, newValue) ->{
            if (!NumberUtils.isParsable(newValue) && !newValue.isEmpty())
            {
                upperBoundPrice.setText(oldValue);
            }
        });
        costField.textProperty().addListener((observable, oldValue, newValue) ->{
            if (!NumberUtils.isParsable(newValue) && !newValue.isEmpty())
            {
                costField.setText(oldValue);
            }
        });


        for(Button button: buttons)
        {
            setButtonStyle(button);
        }

        root.getChildren().addAll(boxForSearch, boxForNewRecord, productsListView, showAllButton);

        //primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    private void setButtonStyle(Button button)
    {
        final String IDLE_BUTTON_STYLE = "-fx-background-color: transparent;";
        final String HOWERED_BUTTON_STYLE = "-fx-background-color: -fx-shadow-highlight-color," +
                "-fx-outer-border;";

        button.setStyle(IDLE_BUTTON_STYLE);
        button.setOnMouseEntered(e -> button.setStyle(HOWERED_BUTTON_STYLE));
        button.setOnMouseExited(e -> button.setStyle(IDLE_BUTTON_STYLE));
    }
    private void setActions(DataBaseManager dataBaseManager)
    {
        showAllButton.setOnAction(actionEvent -> {
            products.clear();
            products.addAll(dataBaseManager.showAll().split("\n"));
        });
        addButton.setOnAction(actionEvent -> {
            dataBaseManager.add(new ProductItem(titleField.getText(),
                    Integer.parseInt(costField.getText())));
            products.clear();
            products.addAll(dataBaseManager.showAll().split("\n"));
        });
        findButton.setOnAction(actionEvent -> {
            products.clear();
            products.add(dataBaseManager.showPrice(searchLine.getText()));
        });
        filterByPriceButton.setOnAction(actionEvent -> {
            products.clear();
            products.addAll(dataBaseManager.filterByPrice(
                    Integer.parseInt(lowBoundPrice.getText()),
                    Integer.parseInt(upperBoundPrice.getText())
                    ).split("\n")
            );
        });
        deleteButton.setOnAction(actionEvent -> {
            dataBaseManager.delete(titleField.getText());
        });
    }
}
