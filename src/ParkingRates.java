public enum ParkingRates {
  CAR(2.0F),
  MOTORBIKE(1.5F),
  TRUCK(4.5F);

  private final float rate;

  ParkingRates(float rate) {
    this.rate = rate;
  }

  public float getRate() {
    return rate;
  }

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