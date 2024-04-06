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
          break;

        case 1:
          String licensePlateToPay = ParkingSystemView.getInput("Enter your license plate number: ");
          Vehicle vehicleToPay = (Vehicle) parkingManager.getParkedVehicles().get(licensePlateToPay);
          if (vehicleToPay != null) {
            boolean paymentSuccess = paymentSystem.processPayment(vehicleToPay);
            if (paymentSuccess) {
              ParkingSystemView.displayMessage("Payment processed successfully! Please leave within 20 minutes.");
            } else {
              ParkingSystemView.displayMessage("Payment Failed! Please Try Again.");
            }
          } else {
            ParkingSystemView.displayMessage("Vehicle with license plate " + licensePlateToPay + " not found in the parking lot.");
          }
          break;

        case 2:
          String licensePlateToProcess = ParkingSystemView.getInput("Enter your license plate number to leave: ");
          Vehicle vehicleToProcess = (Vehicle) parkingManager.getParkedVehicles().get(licensePlateToProcess);
          if (vehicleToProcess != null) {
            parkingManager.processToLeave(vehicleToProcess);
            ParkingSystemView.displayMessage("Gate opened! See you next time!");
          } else {
            ParkingSystemView.displayMessage("Vehicle with license plate " + licensePlateToProcess + " not found in the parking lot.");
          }
          break;
      }
    }
  }

}
