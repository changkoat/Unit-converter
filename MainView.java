package com.unitconverter.view;

import com.unitconverter.controller.ConversionController;
import com.unitconverter.model.UnitCategory;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main view class for the unit converter application.
 */
public class MainView extends Application {
    private ConversionController controller;
    private ConversionPanel conversionPanel;
    
    @Override
    public void start(Stage primaryStage) {
        controller = new ConversionController();
        
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        
        // Left panel with categories
        VBox categoryPanel = createCategoryPanel();
        root.setLeft(categoryPanel);
        
        // Center panel for conversions (initially empty)
        conversionPanel = new ConversionPanel(controller);
        root.setCenter(conversionPanel);
        
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Unit Converter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private VBox createCategoryPanel() {
        VBox panel = new VBox(10);
        panel.setPadding(new Insets(10));
        
        Label titleLabel = new Label("Categories");
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
        
        ListView<UnitCategory> categoryListView = new ListView<>();
        categoryListView.getItems().addAll(controller.getAllCategories());
        
        // When a category is selected, update the conversion panel
        categoryListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                conversionPanel.updateForCategory(newVal);
            }
        });
        
        panel.getChildren().addAll(titleLabel, categoryListView);
        return panel;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
