public class ParkingSystemController {
  private ParkingManager parkingManager;
  private PaymentSystem paymentSystem;

  public ParkingSystemController(ParkingManager parkingManager, PaymentSystem paymentSystem) {
    this.parkingManager = parkingManager;
    this.paymentSystem = paymentSystem;
  }

  public void go() {

    boolean parking = true;

    while (parking) {
      int option = ParkingSystemView.displayMenuAndGetOption();

      switch (option) {
        case 0:
          VehicleType vehicleType = ParkingSystemView.chooseVehicleType();
          String licensePlateToPark = ParkingSystemView.getInput("Enter your license plate number: ");
          Vehicle newVehicle = parkingManager.createVehicle(licensePlateToPark, vehicleType);
          parkingManager.parkVehicle(newVehicle);

          ParkingSystemView.displayMessage("Welcome to smartPark, " + licensePlateToPark + "!");
          break;

        case 1:
          String licensePlateToPay = ParkingSystemView.getInput("Enter your license plate number: ");
          Vehicle vehicleToPay = (Vehicle) parkingManager.getParkedVehicles().get(licensePlateToPay);
          if (vehicleToPay != null) {
            paymentSystem.processPayment(vehicleToPay);
          } else {
            ParkingSystemView.displayMessage("Vehicle with license plate " + licensePlateToPay + " not found in the parking lot.");
          }
          break;

        case 2:
          String licensePlateToProcess = ParkingSystemView.getInput("Enter your license plate number to leave: ");
          Vehicle vehicleToProcess = (Vehicle) parkingManager.getParkedVehicles().get(licensePlateToProcess);
          if (vehicleToProcess != null) {
            if (parkingManager.processToLeave(vehicleToProcess)) {
              ParkingSystemView.displayParkedDuration(vehicleToProcess.getArrivalTime(), vehicleToProcess.getLeaveTime());
              ParkingSystemView.displayMessage("Payment processed successfully. You may leave the parking lot.");
              //parking = false;
            } else {
              ParkingSystemView.displayMessage("Error processing payment. Please try again.");
            }
          } else {
            ParkingSystemView.displayMessage("Vehicle with license plate " + licensePlateToProcess + " not found in the parking lot.");
          }
          break;
      }
    }
  }

}
