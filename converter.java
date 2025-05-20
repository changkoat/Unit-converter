package com.unitconverter.controller;

import com.unitconverter.model.*;
import java.util.*;

/**
 * Main controller class for the unit converter application.
 */
public class Converter {
    private final Map<UnitCategory, List<Unit>> unitsByCategory;
    private final Map<UnitCategory, ConversionStrategy> conversionStrategies;
    
    public Converter() {
        unitsByCategory = new HashMap<>();
        conversionStrategies = new HashMap<>();
        
        // Initialize with default conversion strategies
        for (UnitCategory category : UnitCategory.values()) {
            if (category == UnitCategory.TEMPERATURE) {
                conversionStrategies.put(category, new TemperatureConversion());
            } else {
                conversionStrategies.put(category, new LinearConversion());
            }
        }
        
        initializeUnits();
    }
    
    private void initializeUnits() {
        // Initialize LENGTH units (base unit: meter)
        List<Unit> lengthUnits = new ArrayList<>();
        lengthUnits.add(new Unit("Meter", "m", UnitCategory.LENGTH, 1.0));
        lengthUnits.add(new Unit("Kilometer", "km", UnitCategory.LENGTH, 1000.0));
        lengthUnits.add(new Unit("Centimeter", "cm", UnitCategory.LENGTH, 0.01));
        lengthUnits.add(new Unit("Millimeter", "mm", UnitCategory.LENGTH, 0.001));
        lengthUnits.add(new Unit("Inch", "in", UnitCategory.LENGTH, 0.0254));
        lengthUnits.add(new Unit("Foot", "ft", UnitCategory.LENGTH, 0.3048));
        lengthUnits.add(new Unit("Yard", "yd", UnitCategory.LENGTH, 0.9144));
        lengthUnits.add(new Unit("Mile", "mi", UnitCategory.LENGTH, 1609.344));
        unitsByCategory.put(UnitCategory.LENGTH, lengthUnits);
        
        // Initialize WEIGHT units (base unit: kilogram)
        List<Unit> weightUnits = new ArrayList<>();
        weightUnits.add(new Unit("Kilogram", "kg", UnitCategory.WEIGHT, 1.0));
        weightUnits.add(new Unit("Gram", "g", UnitCategory.WEIGHT, 0.001));
        weightUnits.add(new Unit("Milligram", "mg", UnitCategory.WEIGHT, 0.000001));
        weightUnits.add(new Unit("Metric Ton", "t", UnitCategory.WEIGHT, 1000.0));
        weightUnits.add(new Unit("Pound", "lb", UnitCategory.WEIGHT, 0.45359237));
        weightUnits.add(new Unit("Ounce", "oz", UnitCategory.WEIGHT, 0.028349523125));
        unitsByCategory.put(UnitCategory.WEIGHT, weightUnits);
        
        // Initialize TEMPERATURE units (base unit: Kelvin)
        // For temperature, conversion factor is multiplier, offset is added after multiplication
        List<Unit> temperatureUnits = new ArrayList<>();
        temperatureUnits.add(new Unit("Kelvin", "K", UnitCategory.TEMPERATURE, 1.0, 0.0));
        temperatureUnits.add(new Unit("Celsius", "°C", UnitCategory.TEMPERATURE, 1.0, 273.15));
        temperatureUnits.add(new Unit("Fahrenheit", "°F", UnitCategory.TEMPERATURE, 5.0/9.0, 459.67));
        unitsByCategory.put(UnitCategory.TEMPERATURE, temperatureUnits);
        
        // Initialize VOLUME units (base unit: cubic meter)
        List<Unit> volumeUnits = new ArrayList<>();
        volumeUnits.add(new Unit("Cubic Meter", "m³", UnitCategory.VOLUME, 1.0));
        volumeUnits.add(new Unit("Liter", "L", UnitCategory.VOLUME, 0.001));
        volumeUnits.add(new Unit("Milliliter", "mL", UnitCategory.VOLUME, 0.000001));
        volumeUnits.add(new Unit("Gallon (US)", "gal", UnitCategory.VOLUME, 0.00378541));
        volumeUnits.add(new Unit("Quart (US)", "qt", UnitCategory.VOLUME, 0.000946353));
        volumeUnits.add(new Unit("Pint (US)", "pt", UnitCategory.VOLUME, 0.000473176));
        volumeUnits.add(new Unit("Cup (US)", "cup", UnitCategory.VOLUME, 0.000236588));
        volumeUnits.add(new Unit("Fluid Ounce (US)", "fl oz", UnitCategory.VOLUME, 0.0000295735));
        unitsByCategory.put(UnitCategory.VOLUME, volumeUnits);
    }
    
    /**
     * Get all available unit categories.
     */
    public List<UnitCategory> getCategories() {
        return Arrays.asList(UnitCategory.values());
    }
    
    /**
     * Get all units for a specific category.
     */
    public List<Unit> getUnitsForCategory(UnitCategory category) {
        return unitsByCategory.getOrDefault(category, Collections.emptyList());
    }
    
    /**
     * Convert a value from one unit to another.
     */
    public double convert(double value, Unit sourceUnit, Unit targetUnit) {
        if (sourceUnit.getCategory() != targetUnit.getCategory()) {
            throw new IllegalArgumentException("Cannot convert between different unit categories");
        }
        
        ConversionStrategy strategy = conversionStrategies.get(sourceUnit.getCategory());
        return strategy.convert(value, sourceUnit, targetUnit);
    }
}
