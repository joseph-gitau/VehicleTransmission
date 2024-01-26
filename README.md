# Manual Transmission Vehicle Simulator

## 1. Introduction

Vehicles with manual transmissions require the driver to actively change gears in accordance with the changing speeds. This process involves a direct interaction between the engine's rotation and the rotation of the axles. Different gears correspond to different ranges of speed, and the incorrect gear selection can lead to vehicle knocking or engine revving without corresponding increases in speed. This simulator aims to replicate the gear-speed behavior of a manual transmission vehicle in a computer program. It's an algorithmic representation, similar in concept to the automatic transmission where gear control is managed by the vehicle's computer.

## 2. Transmission

In real vehicles, the driver changes the speed using the gas pedal and the gears using the stick shift. The vehicle responds with various feedback such as engine sounds or vibrations. This simulator mimics these interactions through a series of operations:

- Increase the speed by a fixed amount.
- Decrease the speed by a fixed amount.
- Increase the gear by one.
- Decrease the gear by one.
- Report the vehicle's status following any of these operations.

## 3. Implementation Details

### 3.1 The Interface

The `ManualTransmission` interface provides the following methods:

- `getStatus`: Returns the current transmission status as a String.
- `getSpeed`: Returns the current vehicle speed as a whole number.
- `getGear`: Returns the current gear of the vehicle as a whole number.
- `increaseSpeed`: Increases the speed by a fixed amount without changing gears. Returns the updated transmission object. If the speed can't be increased, it returns an object with the same speed.
- `decreaseSpeed`: Decreases the speed by a fixed amount without changing gears. Returns the updated transmission object. If the speed can't be decreased, it returns an object with the same speed.
- `increaseGear`: Increases the gear by one without changing speed. Returns the updated transmission object. If the gear can't be increased, it returns an object with the same gear.
- `decreaseGear`: Decreases the gear by one without changing speed. Returns the updated transmission object. If the gear can't be decreased, it returns an object with the same gear.

### 3.2 The Implementation - `RegularManualTransmission`

The `RegularManualTransmission` class implements the `ManualTransmission` interface with these characteristics:

- Speed changes by 1 unit at a time.
- Supports 5 gears (1-5), with Gear 1 having the lowest speed range.
- Constructor accepts speed ranges for each gear (l1,h1,l2,h2,...,l5,h5) with specific rules for validation.
- Returns specific status messages based on the transmission state and the last attempted operation.

### 3.3 Testing

Tests for the `RegularManualTransmission` class should cover various scenarios, ensuring the correct functioning of all operations and status updates. The tests should be written prior to the implementation, following the principles of Test-Driven Development (TDD).
