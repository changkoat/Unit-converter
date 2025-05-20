package com.unitconverter.model;

/**
 * Strategy for temperature conversions (non-linear with offsets).
 */
public class TemperatureConversion implements ConversionStrategy {
    
    @Override
    public double convert(double value, Unit sourceUnit, Unit targetUnit) {
        // First convert to Kelvin (our base unit for temperature)
        double valueInKelvin = value * sourceUnit.getConversionFactor() + sourceUnit.getOffset();
        
        // Then convert from Kelvin to target unit
        return (valueInKelvin - targetUnit.getOffset()) / targetUnit.getConversionFactor();
    }
}
