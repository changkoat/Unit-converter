package com.unitconverter.view;

import com.unitconverter.controller.ConversionController;
import com.unitconverter.model.Unit;
import com.unitconverter.model.UnitCategory;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Panel for displaying and performing conversions for a specific category.
 */
public class ConversionPanel extends VBox {
    private final ConversionController controller;
    private UnitCategory currentCategory;
    
    private ComboBox<Unit> sourceUnitComboBox;
    private ComboBox<Unit> targetUnitComboBox;
    private TextField inputField;
    private Label resultLabel;
    private Label errorLabel;
    
    public ConversionPanel(ConversionController controller) {
        this.controller = controller;
        setPadding(new Insets(20));
        setSpacing(15);
        
        // Initially show a welcome message
        Label welcomeLabel = new Label("Select a category from the left panel to start converting");
        welcomeLabel.setStyle("-fx-font-size: 16px;");
        getChildren().add(welcomeLabel);
    }
    
    public void updateForCategory(UnitCategory category) {
        this.currentCategory = category;
        getChildren().clear();
        
        // Header
        Label headerLabel = new Label(category.getDisplayName() + " Conversion");
        headerLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");
        
        // Conversion form
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 0, 20, 0));
        
        // Source unit selection
        Label fromLabel = new Label("From:");
        sourceUnitComboBox = new ComboBox<>();
        sourceUnitComboBox.getItems().addAll(controller.getUnitsForCategory(category));
        if (!sourceUnitComboBox.getItems().isEmpty()) {
            sourceUnitComboBox.getSelectionModel().select(0);
        }
        
        // Target unit selection
        Label toLabel = new Label("To:");
        targetUnitComboBox = new ComboBox<>();
        targetUnitComboBox.getItems().addAll(controller.getUnitsForCategory(category));
        if (targetUnitComboBox.getItems().size() > 1) {
            targetUnitComboBox.getSelectionModel().select(1);
        } else if (!targetUnitComboBox.getItems().isEmpty()) {
            targetUnitComboBox.getSelectionModel().select(0);
        }
        
        // Input field
        Label valueLabel = new Label("Value:");
        inputField = new TextField("1");
        
        // Result display
        Label resultLabelText = new Label("Result:");
        resultLabel = new Label();
        resultLabel.setStyle("-fx-font-size: 16px;");
        
        // Error label
        errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        
        // Add elements to grid
        grid.add(fromLabel, 0, 0);
        grid.add(sourceUnitComboBox, 1, 0);
        grid.add(toLabel, 0, 1);
        grid.add(targetUnitComboBox, 1, 1);
        grid.add(valueLabel, 0, 2);
        grid.add(inputField, 1, 2);
        grid.add(resultLabelText, 0, 3);
        grid.add(resultLabel, 1, 3);
        
        // Buttons
        Button convertButton = new Button("Convert");
        Button swapButton = new Button("Swap Units");
        Button copyButton = new Button("Copy Result");
        
        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(convertButton, swapButton, copyButton);
        
        // Add everything to the panel
        getChildren().addAll(headerLabel, grid, errorLabel, buttonBox);
        
        // Set up event handlers
        setupEventHandlers(convertButton, swapButton, copyButton);
        
        // Initial conversion
        performConversion();
    }
    
    private void setupEventHandlers(Button convertButton, Button swapButton, Button copyButton) {
        // Convert when button is clicked
        convertButton.setOnAction(e -> performConversion());
        
        // Convert when input changes
        inputField.textProperty().addListener((obs, oldVal, newVal) -> performConversion());
        
        // Convert when units change
        sourceUnitComboBox.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> performConversion());
        targetUnitComboBox.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> performConversion());
        
        // Swap units
        swapButton.setOnAction(e -> {
            Unit sourceUnit = sourceUnitComboBox.getValue();
            Unit targetUnit = targetUnitComboBox.getValue();
            
            sourceUnitComboBox.setValue(targetUnit);
            targetUnitComboBox.setValue(sourceUnit);
            
            performConversion();
        });
        
        // Copy result
        copyButton.setOnAction(e -> {
            if (resultLabel.getText() != null && !resultLabel.getText().isEmpty()) {
                javafx.scene.input.Clipboard clipboard = javafx.scene.input.Clipboard.getSystemClipboard();
                javafx.scene.input.ClipboardContent content = new javafx.scene.input.ClipboardContent();
                content.putString(resultLabel.getText());
                clipboard.setContent(content);
                
                // Show feedback
                errorLabel.setTextFill(Color.GREEN);
                errorLabel.setText("Result copied to clipboard!");
            }
        });
    }
    
    private void performConversion() {
        // Clear previous error
        errorLabel.setText("");
        
        // Validate input
        String input = inputField.getText();
        if (!controller.isValidInput(input)) {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("Please enter a valid number");
            resultLabel.setText("");
            return;
        }
        
        // Get selected units
        Unit sourceUnit = sourceUnitComboBox.getValue();
        Unit targetUnit = targetUnitComboBox.getValue();
        
        if (sourceUnit == null || targetUnit == null) {
            return;
        }
        
        try {
            // Perform conversion
            double inputValue = Double.parseDouble(input);
            double result = controller.performConversion(inputValue, sourceUnit, targetUnit);
            
            // Format the result
            String formattedResult = formatResult(result, targetUnit);
            resultLabel.setText(formattedResult);
        } catch (Exception e) {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("Error: " + e.getMessage());
            resultLabel.setText("");
        }
    }
    
    private String formatResult(double result, Unit targetUnit) {
        // Use appropriate formatting based on the magnitude and unit type
        String formattedValue;
        
        if (Math.abs(result) >= 1000000 || Math.abs(result) < 0.001 && result != 0) {
            // Use scientific notation for very large or small numbers
            formattedValue = String.format("%.6e", result);
        } else {
            // Use a reasonable number of decimal places based on the size
            if (Math.abs(result) >= 100) {
                formattedValue = String.format("%.2f", result);
            } else if (Math.abs(result) >= 10) {
                formattedValue = String.format("%.3f", result);
            } else {
                formattedValue = String.format("%.4f", result);
            }
        }
        
        // Add unit symbol
        return formattedValue + " " + targetUnit.getSymbol();
    }
}
