package com.unitconverter.model;

/**
 * Represents a specific unit of measurement within a category.
 */
public class Unit {
    private final String name;
    private final String symbol;
    private final UnitCategory category;
    private final double conversionFactor;
    private final double offset;
    
    /**
     * Constructor for linear conversions (multiplication by factor).
     */
    public Unit(String name, String symbol, UnitCategory category, double conversionFactor) {
        this(name, symbol, category, conversionFactor, 0.0);
    }
    
    /**
     * Constructor for non-linear conversions (multiplication by factor + offset).
     */
    public Unit(String name, String symbol, UnitCategory category, double conversionFactor, double offset) {
        this.name = name;
        this.symbol = symbol;
        this.category = category;
        this.conversionFactor = conversionFactor;
        this.offset = offset;
    }
    
    public String getName() {
        return name;
    }
    
    public String getSymbol() {
        return symbol;
    }
    
    public UnitCategory getCategory() {
        return category;
    }
    
    public double getConversionFactor() {
        return conversionFactor;
    }
    
    public double getOffset() {
        return offset;
    }
    
    @Override
    public String toString() {
        return name + " (" + symbol + ")";
    }
}
