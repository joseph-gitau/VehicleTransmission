package test;

import org.junit.Before;
import org.junit.Test;
import vehicle.RegularManualTransmission;

import static org.junit.Assert.*;

/**
 * Tests for the RegularManualTransmission class.
 */
public class RegularManualTransmissionTest {

    private RegularManualTransmission transmission;

    @Before
    public void setUp() {
        // Initialize a transmission with valid gear limits for testing.
        transmission = new RegularManualTransmission(0, 10, 11, 20, 21, 30, 31, 40, 41, 50);
    }

    // Test for valid constructor input
    @Test
    public void testValidConstructor() {
        assertNotNull("Transmission should be initialized", transmission);
    }

    // Test for invalid constructor input (e.g., wrong number of arguments, invalid ranges)
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidConstructorArguments() {
        new RegularManualTransmission(0, 10, 11, 20); // Invalid number of arguments
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidGearLimits() {
        new RegularManualTransmission(0, 10, 9, 20, 21, 30, 31, 40, 41, 50); // Overlapping ranges
    }

    // Test for getting initial speed and gear
    @Test
    public void testInitialSpeedAndGear() {
        assertEquals("Initial speed should be 0", 0, transmission.getSpeed());
        assertEquals("Initial gear should be 1", 1, transmission.getGear());
    }

    // Test for increasing speed within a gear range
    @Test
    public void testIncreaseSpeedWithinGear() {
        transmission.increaseSpeed();
        assertEquals("Speed should increase within the gear limit", 1, transmission.getSpeed());
    }

    // Test for increasing speed beyond gear limit
    @Test
    public void testIncreaseSpeedBeyondGearLimit() {
        for (int i = 0; i < 10; i++) {
            transmission.increaseSpeed();
        }
        assertEquals("Speed should be at the upper limit of gear 1", 10, transmission.getSpeed());
        assertEquals("Status should indicate gear increase", "OK: everything is OK.", transmission.getStatus());
    }

    // Test for decreasing speed within a gear range
    @Test
    public void testDecreaseSpeedWithinGear() {
        transmission.increaseSpeed();
        transmission.decreaseSpeed();
        assertEquals("Speed should decrease within the gear limit", 0, transmission.getSpeed());
    }

    // Test for decreasing speed below gear limit
    @Test
    public void testDecreaseSpeedBelowGearLimit() {
        assertEquals("Status should indicate minimum speed", "Cannot decrease speed. Reached minimum speed.", transmission.decreaseSpeed().getStatus());
    }

    // Test for increasing gear
    @Test
    public void testIncreaseGear() {
        for (int i = 0; i < 10; i++) {
            transmission.increaseSpeed();
        }
        assertEquals("Gear should increase", 2, transmission.increaseGear().getGear());
    }

    // Test for increasing gear without sufficient speed
    @Test
    public void testIncreaseGearWithoutSufficientSpeed() {
        assertEquals("Status should indicate speed increase first", "Cannot increase gear, increase speed first.", transmission.increaseGear().getStatus());
    }

    // Test for decreasing gear
    @Test
    public void testDecreaseGear() {
        for (int i = 0; i < 10; i++) {
            transmission.increaseSpeed();
        }
        transmission.increaseGear();
        for (int i = 0; i < 10; i++) {
            transmission.decreaseSpeed();
        }
        assertEquals("Gear should decrease", 1, transmission.decreaseGear().getGear());
    }

    // Test for decreasing gear with too high speed
    @Test
    public void testDecreaseGearWithHighSpeed() {
        for (int i = 0; i < 10; i++) {
            transmission.increaseSpeed();
        }
        transmission.increaseGear();
        assertEquals("Status should indicate speed decrease first", "Cannot decrease gear, decrease speed first.", transmission.decreaseGear().getStatus());
    }

    @Test
    public void testStatusMessages() {
        RegularManualTransmission transmission = new RegularManualTransmission(0, 10, 11, 20, 21, 30, 31, 40, 41, 50);

        // Test initial status message
        assertEquals("Initial status should be OK", "OK: everything is OK.", transmission.getStatus());

        // Test status after increasing speed within limit
        transmission.increaseSpeed();
        assertEquals("Status should be OK after increasing speed within limit", "OK: everything is OK.", transmission.getStatus());

        // Test status when speed reaches upper limit of a gear
        for (int i = 1; i < 10; i++) {
            transmission.increaseSpeed();
        }
        assertEquals("Status should suggest gear increase at upper limit", "OK: you may increase the gear.", transmission.getStatus());

        // Test status when increasing gear
        transmission.increaseGear();
        assertEquals("Status should be OK after increasing gear", "OK: everything is OK.", transmission.getStatus());

        // Test status when decreasing speed to lower limit of a gear
        for (int i = 0; i < 10; i++) {
            transmission.decreaseSpeed();
        }
        assertEquals("Status should suggest gear decrease at lower limit", "OK: you may decrease the gear.", transmission.getStatus());

        // Test status when decreasing gear
        transmission.decreaseGear();
        assertEquals("Status should be OK after decreasing gear", "OK: everything is OK.", transmission.getStatus());

        // Test status when trying to increase speed beyond maximum
        for (int i = 0; i < 40; i++) { // increasing to top speed in gear 5
            transmission.increaseSpeed();
            if (transmission.getStatus().equals("OK: you may increase the gear.")) {
                transmission.increaseGear();
            }
        }
        transmission.increaseSpeed();
        assertEquals("Status should indicate maximum speed reached", "Cannot increase speed. Reached maximum speed.", transmission.getStatus());

        // Test status when trying to decrease speed below minimum
        for (int i = 0; i < 50; i++) { // decreasing to minimum speed in gear 1
            transmission.decreaseSpeed();
            if (transmission.getStatus().equals("OK: you may decrease the gear.")) {
                transmission.decreaseGear();
            }
        }
        transmission.decreaseSpeed();
        assertEquals("Status should indicate minimum speed reached", "Cannot decrease speed. Reached minimum speed.", transmission.getStatus());
    }
}
