/**
 * Enumeration of parking rates for different types of vehicles.
 */
public enum ParkingRates {
  /**
   * Parking rate for a car.
   */
  CAR(2.0F),

  /**
   * Parking rate for a motorbike.
   */
  MOTORBIKE(1.5F),

  /**
   * Parking rate for a truck.
   */
  TRUCK(4.5F);

  private final float rate;

  /**
   * Constructor of a parkingRate with the specified rate
   *
   * @param rate The parking rate for the vehicle type.
   */
  ParkingRates(float rate) {
    this.rate = rate;
  }

  /**
   * Retrieves the parking rate for the vehicle type.
   *
   * @return The parking rate for the vehicle type.
   */
  public float getRate() {
    return rate;
  }

  /**
   * Retrieves the parking rate for the specified vehicle type.
   *
   * @param vehicleType The type of vehicle.
   * @return The parking rate for the specified vehicle type.
   * @throws IllegalArgumentException if the vehicle type is invalid.
   */
  public static float getRateForVehicleType(VehicleType vehicleType) {
    switch (vehicleType) {
      case CAR:
        return CAR.getRate();
      case MOTORBIKE:
        return MOTORBIKE.getRate();
      case TRUCK:
        return TRUCK.getRate();
      default:
        throw new IllegalArgumentException("Invalid vehicle type: " + vehicleType);
    }
  }
}