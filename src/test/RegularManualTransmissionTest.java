package test;

import org.junit.Before;
import org.junit.Test;
import vehicle.ManualTransmission;
import vehicle.RegularManualTransmission;

import static org.junit.Assert.*;

public class RegularManualTransmissionTest {

    private ManualTransmission mt;

    @Before
    public void setUp() {
        // Initialize with some hypothetical gear ranges
        mt = new RegularManualTransmission(0, 10, 5, 15, 10, 20, 15, 25, 20, 30);
    }

    @Test
    public void testInitialState() {
        assertEquals("Initial gear should be 1", 1, mt.getGear());
        assertEquals("Initial speed should be 0", 0, mt.getSpeed());
        assertEquals("Initial status should be OK", "OK: everything is OK.", mt.getStatus());
    }

    @Test
    public void testIncreaseSpeed() {
        mt.increaseSpeed();
        assertEquals("Speed should increase by 1", 1, mt.getSpeed());
        assertEquals("Status should be OK after increasing speed", "OK: everything is OK.", mt.getStatus());
    }

    @Test
    public void testDecreaseSpeed() {
        mt.increaseSpeed().decreaseSpeed();
        assertEquals("Speed should decrease to initial", 0, mt.getSpeed());
        assertEquals("Status should be OK after decreasing speed", "OK: everything is OK.", mt.getStatus());
    }

    @Test
    public void testIncreaseGear() {
        mt.increaseSpeed().increaseSpeed().increaseGear();
        assertEquals("Gear should increase by 1", 2, mt.getGear());
        assertEquals("Status should be OK after increasing gear", "OK: everything is OK.", mt.getStatus());
    }

    @Test
    public void testDecreaseGear() {
        mt.increaseSpeed().increaseSpeed().increaseGear().decreaseGear();
        assertEquals("Gear should decrease to initial", 1, mt.getGear());
        assertEquals("Status should be OK after decreasing gear", "OK: everything is OK.", mt.getStatus());
    }

    // Additional tests for edge cases and invalid scenarios
    // ...

}
