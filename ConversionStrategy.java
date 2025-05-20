package com.unitconverter.model;

/**
 * Interface for different conversion algorithms.
 */
public interface ConversionStrategy {
    /**
     * Convert a value from source unit to target unit.
     */
    double convert(double value, Unit sourceUnit, Unit targetUnit);
}
