package com.unitconverter.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UnitTest {
    
    @Test
    public void testUnitCreation() {
        // Test linear unit creation
        Unit meter = new Unit("Meter", "m", UnitCategory.LENGTH, 1.0);
        assertEquals("Meter", meter.getName());
        assertEquals("m", meter.getSymbol());
        assertEquals(UnitCategory.LENGTH, meter.getCategory());
        assertEquals(1.0, meter.getConversionFactor());
        assertEquals(0.0, meter.getOffset());
        
        // Test non-linear unit creation with offset
        Unit celsius = new Unit("Celsius", "°C", UnitCategory.TEMPERATURE, 1.0, 273.15);
        assertEquals("Celsius", celsius.getName());
        assertEquals("°C", celsius.getSymbol());
        assertEquals(UnitCategory.TEMPERATURE, celsius.getCategory());
        assertEquals(1.0, celsius.getConversionFactor());
        assertEquals(273.15, celsius.getOffset());
    }
    
    @Test
    public void testToString() {
        Unit kilogram = new Unit("Kilogram", "kg", UnitCategory.WEIGHT, 1.0);
        assertEquals("Kilogram (kg)", kilogram.toString());
    }
}
