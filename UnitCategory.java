package com.unitconverter.model;

/**
 * Enum representing different categories of units that can be converted.
 */
public enum UnitCategory {
    LENGTH("Length"),
    WEIGHT("Weight"),
    TEMPERATURE("Temperature"),
    VOLUME("Volume"),
    AREA("Area"),
    SPEED("Speed"),
    TIME("Time"),
    DIGITAL_STORAGE("Digital Storage");
    
    private final String displayName;
    
    UnitCategory(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}
