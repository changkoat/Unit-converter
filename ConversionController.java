package com.unitconverter.controller;

import com.unitconverter.model.*;
import java.util.List;

/**
 * Controller that bridges between UI and converter model.
 */
public class ConversionController {
    private final Converter converter;
    
    public ConversionController() {
        this.converter = new Converter();
    }
    
    public List<UnitCategory> getAllCategories() {
        return converter.getCategories();
    }
    
    public List<Unit> getUnitsForCategory(UnitCategory category) {
        return converter.getUnitsForCategory(category);
    }
    
    public double performConversion(double value, Unit sourceUnit, Unit targetUnit) {
        try {
            return converter.convert(value, sourceUnit, targetUnit);
        } catch (Exception e) {
            // Log the error if needed
            throw new RuntimeException("Error performing conversion: " + e.getMessage(), e);
        }
    }
    
    public boolean isValidInput(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }
        
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
