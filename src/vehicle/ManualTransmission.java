package vehicle;

/**
 * This interface represents a manual transmission system for a vehicle.
 * It allows for operations to change the vehicle's speed and gear, and to get the current status.
 */
public interface ManualTransmission {

    /**
     * Returns the current status of the transmission.
     *
     * @return the current status as a String.
     */
    String getStatus();

    /**
     * Returns the current speed of the vehicle.
     *
     * @return the current speed as a whole number.
     */
    int getSpeed();

    /**
     * Returns the current gear of the vehicle.
     *
     * @return the current gear as a whole number.
     */
    int getGear();

    /**
     * Increases the speed by a fixed amount and returns the updated transmission object.
     *
     * @return the updated transmission object.
     */
    ManualTransmission increaseSpeed();

    /**
     * Decreases the speed by a fixed amount and returns the updated transmission object.
     *
     * @return the updated transmission object.
     */
    ManualTransmission decreaseSpeed();

    /**
     * Increases the gear by one and returns the updated transmission object.
     *
     * @return the updated transmission object.
     */
    ManualTransmission increaseGear();

    /**
     * Decreases the gear by one and returns the updated transmission object.
     *
     * @return the updated transmission object.
     */
    ManualTransmission decreaseGear();
}
