package vehicle;

/**
 * This class represents a regular manual transmission for a vehicle.
 * It implements the ManualTransmission interface.
 */
public class RegularManualTransmission implements ManualTransmission {

    private int speed;
    private int gear;
    private final int[] lowerGearLimits;  // Lower limits of each gear
    private final int[] upperGearLimits;  // Upper limits of each gear
    private String status;

    /**
     * Constructor for RegularManualTransmission. Initializes the gear limits.
     *
     * @param gearLimits Speed limits for each gear in the format l1, h1, l2, h2, ..., l5, h5.
     * @throws IllegalArgumentException if the gear speed ranges are invalid.
     */
    public RegularManualTransmission(int... gearLimits) {
        if (gearLimits.length != 10) {
            throw new IllegalArgumentException("Invalid number of gear limit arguments.");
        }

        lowerGearLimits = new int[5];
        upperGearLimits = new int[5];
        for (int i = 0; i < 5; i++) {
            lowerGearLimits[i] = gearLimits[2 * i];
            upperGearLimits[i] = gearLimits[2 * i + 1];

            // Validate gear speed ranges
            if (lowerGearLimits[i] > upperGearLimits[i]) {
                throw new IllegalArgumentException("Lower limit must be less than or equal to upper limit for each gear.");
            }

            if (i > 0 && lowerGearLimits[i] <= upperGearLimits[i - 1]) {
                throw new IllegalArgumentException("Lower speed for each gear should be greater than upper speed of previous gear.");
            }
        }

        // Initialize the transmission in first gear with zero speed
        gear = 1;
        speed = 0;
        status = "OK: everything is OK.";
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public int getGear() {
        return gear;
    }

    @Override
    public ManualTransmission increaseSpeed() {
        int upperLimit = upperGearLimits[gear - 1];
        if (gear == 5 && speed >= upperLimit) {
            status = "Cannot increase speed. Reached maximum speed.";
        } else if (speed < upperLimit) {
            speed++;
            updateStatusForSpeedChange();
        } else {
            status = "Cannot increase speed, increase gear first.";
        }
        return this;
    }

    @Override
    public ManualTransmission decreaseSpeed() {
        int lowerLimit = lowerGearLimits[gear - 1];
        if (gear == 1 && speed <= lowerLimit) {
            status = "Cannot decrease speed. Reached minimum speed.";
        } else if (speed > lowerLimit) {
            speed--;
            updateStatusForSpeedChange();
        } else {
            status = "Cannot decrease speed, decrease gear first.";
        }
        return this;
    }

    private void updateStatusForSpeedChange() {
        int lowerLimit = lowerGearLimits[gear - 1];
        int upperLimit = upperGearLimits[gear - 1];
        if (speed >= lowerLimit && speed <= upperLimit) {
            status = "OK: everything is OK.";
        } else if (gear < 5 && speed == upperGearLimits[gear]) {
            status = "OK: you may increase the gear.";
        } else if (gear > 1 && speed == lowerGearLimits[gear - 2]) {
            status = "OK: you may decrease the gear.";
        }
    }

    @Override
    public ManualTransmission increaseGear() {
        if (gear == 5) {
            status = "Cannot increase gear. Reached maximum gear.";
        } else if (speed < upperGearLimits[gear - 1]) {
            status = "Cannot increase gear, increase speed first.";
        } else {
            gear++;
            updateStatusForGearChange();
        }
        return this;
    }


    @Override
    public ManualTransmission decreaseGear() {
        if (gear == 1) {
            status = "Cannot decrease gear. Reached minimum gear.";
        } else if (speed > lowerGearLimits[gear - 1]) {
            status = "Cannot decrease gear, decrease speed first.";
        } else {
            gear--;
            updateStatusForGearChange();
        }
        return this;
    }

    private void updateStatusForGearChange() {
        int lowerLimit = lowerGearLimits[gear - 1];
        int upperLimit = upperGearLimits[gear - 1];
        if (speed >= lowerLimit && speed <= upperLimit) {
            status = "OK: everything is OK.";
        } else if (gear < 5 && speed == upperLimit) {
            status = "OK: you may increase the gear.";
        } else if (gear > 1 && speed == lowerLimit) {
            status = "OK: you may decrease the gear.";
        }
    }

}
