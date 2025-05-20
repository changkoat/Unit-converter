package com.unitconverter.controller;

import com.unitconverter.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ConverterTest {
    
    private Converter converter;
    private static final double DELTA = 0.0001; // Delta for floating point comparisons
    
    @BeforeEach
    public void setUp() {
        converter = new Converter();
    }
    
    @Test
    public void testGetCategories() {
        List<UnitCategory> categories = converter.getCategories();
        assertNotNull(categories);
        assertFalse(categories.isEmpty());
        assertTrue(categories.contains(UnitCategory.LENGTH));
        assertTrue(categories.contains(UnitCategory.WEIGHT));
        assertTrue(categories.contains(UnitCategory.TEMPERATURE));
    }
    
    @Test
    public void testGetUnitsForCategory() {
        // Test getting length units
        List<Unit> lengthUnits = converter.getUnitsForCategory(UnitCategory.LENGTH);
        assertNotNull(lengthUnits);
        assertFalse(lengthUnits.isEmpty());
        
        // Verify we have some expected units
        boolean hasMeter = false;
        boolean hasKilometer = false;
        boolean hasFoot = false;
        
        for (Unit unit : lengthUnits) {
            if ("Meter".equals(unit.getName())) hasMeter = true;
            if ("Kilometer".equals(unit.getName())) hasKilometer = true;
            if ("Foot".equals(unit.getName())) hasFoot = true;
        }
        
        assertTrue(hasMeter, "Should have Meter unit");
        assertTrue(hasKilometer, "Should have Kilometer unit");
        assertTrue(hasFoot, "Should have Foot unit");
        
        // Check that all units have the correct category
        for (Unit unit : lengthUnits) {
            assertEquals(UnitCategory.LENGTH, unit.getCategory());
        }
        
        // Test getting weight units
        List<Unit> weightUnits =
