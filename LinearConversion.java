package com.unitconverter.model;

/**
 * Strategy for linear conversions (most common case).
 */
public class LinearConversion implements ConversionStrategy {
    
    @Override
    public double convert(double value, Unit sourceUnit, Unit targetUnit) {
        // Convert to base unit first, then to target unit
        double valueInBaseUnit = value * sourceUnit.getConversionFactor();
        return valueInBaseUnit / targetUnit.getConversionFactor();
    }
}
