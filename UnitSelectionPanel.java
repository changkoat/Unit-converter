package com.unitconverter.view;

import com.unitconverter.model.Unit;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * A reusable panel for selecting units.
 */
public class UnitSelectionPanel extends VBox {
    private final Label titleLabel;
    private final ComboBox<Unit> unitComboBox;
    
    public UnitSelectionPanel(String title) {
        setSpacing(5);
        setPadding(new Insets(10));
        
        titleLabel = new Label(title);
        unitComboBox = new ComboBox<>();
        
        getChildren().addAll(titleLabel, unitComboBox);
    }
    
    public void setUnits(Iterable<Unit> units) {
        unitComboBox.getItems().clear();
        for (Unit unit : units) {
            unitComboBox.getItems().add(unit);
        }
        
        if (!unitComboBox.getItems().isEmpty()) {
            unitComboBox.getSelectionModel().select(0);
        }
    }
    
    public Unit getSelectedUnit() {
        return unitComboBox.getValue();
    }
    
    public void setSelectedUnit(Unit unit) {
        unitComboBox.setValue(unit);
    }
    
    public ComboBox<Unit> getUnitComboBox() {
        return unitComboBox;
    }
}
