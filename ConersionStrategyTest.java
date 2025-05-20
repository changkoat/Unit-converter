package com.unitconverter.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ConversionStrategyTest {
    
    private static final double DELTA = 0.0001; // Delta for floating point comparisons
    
    @Test
    public void testLinearConversion() {
        ConversionStrategy strategy = new LinearConversion();
        
        // Test meter to kilometer conversion
        Unit meter = new Unit("Meter", "m", UnitCategory.LENGTH, 1.0);
        Unit kilometer = new Unit("Kilometer", "km", UnitCategory.LENGTH, 1000.0);
        
        assertEquals(0.001, strategy.convert(1.0, meter, kilometer), DELTA);
        assertEquals(1.0, strategy.convert(1000.0, meter, kilometer), DELTA);
        assertEquals(5.0, strategy.convert(5000.0, meter, kilometer), DELTA);
        
        // Test kilometer to meter conversion
        assertEquals(1000.0, strategy.convert(1.0, kilometer, meter), DELTA);
        assertEquals(5000.0, strategy.convert(5.0, kilometer, meter), DELTA);
        
        // Test with other units
        Unit foot = new Unit("Foot", "ft", UnitCategory.LENGTH, 0.3048);
        Unit inch = new Unit("Inch", "in", UnitCategory.LENGTH, 0.0254);
        
        assertEquals(12.0, strategy.convert(1.0, foot, inch), DELTA);
        assertEquals(1.0, strategy.convert(12.0, inch, foot), DELTA);
    }
    
    @Test
    public void testTemperatureConversion() {
        ConversionStrategy strategy = new TemperatureConversion();
        
        // Create temperature units
        Unit kelvin = new Unit("Kelvin", "K", UnitCategory.TEMPERATURE, 1.0, 0.0);
        Unit celsius = new Unit("Celsius", "°C", UnitCategory.TEMPERATURE, 1.0, 273.15);
        Unit fahrenheit = new Unit("Fahrenheit", "°F", UnitCategory.TEMPERATURE, 5.0/9.0, 459.67);
        
        // Test Celsius to Kelvin
        assertEquals(273.15, strategy.convert(0.0, celsius, kelvin), DELTA);
        assertEquals(373.15, strategy.convert(100.0, celsius, kelvin), DELTA);
        
        // Test Kelvin to Celsius
        assertEquals(0.0, strategy.convert(273.15, kelvin, celsius), DELTA);
        assertEquals(100.0, strategy.convert(373.15, kelvin, celsius), DELTA);
        
        // Test Celsius to Fahrenheit
        assertEquals(32.0, strategy.convert(0.0, celsius, fahrenheit), DELTA);
        assertEquals(212.0, strategy.convert(100.0, celsius, fahrenheit), DELTA);
        
        // Test Fahrenheit to Celsius
        assertEquals(0.0, strategy.convert(32.0, fahrenheit, celsius), DELTA);
        assertEquals(100.0, strategy.convert(212.0, fahrenheit, celsius), DELTA);
        
        // Test Kelvin to Fahrenheit
        assertEquals(32.0, strategy.convert(273.15, kelvin, fahrenheit), DELTA);
        assertEquals(212.0, strategy.convert(373.15, kelvin, fahrenheit), DELTA);
        
        // Test Fahrenheit to Kelvin
        assertEquals(273.15, strategy.convert(32.0, fahrenheit, kelvin), DELTA);
        assertEquals(373.15, strategy.convert(212.0, fahrenheit, kelvin), DELTA);
    }
}
