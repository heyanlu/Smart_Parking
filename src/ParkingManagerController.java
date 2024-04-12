public class ParkingManagerController implements Feature{
  private ParkingManager parkingManager;
  private PaymentSystem paymentSystem;

  private IParkingManagerView view;

  public ParkingManagerController(ParkingManager parkingManager, PaymentSystem paymentSystem) {
    this.parkingManager = parkingManager;
    this.paymentSystem = paymentSystem;
  }

  public void setView(IParkingManagerView v) {
    view = v;
    view.addFeatures(this);
  }

  public void optionExecution(String option) {
    switch (option) {
      case "Total Parking Capacity":
        VehicleType vehicleTypeTotalCapacity = view.chooseVehicleType();
        int totalCapacity = parkingManager.getTotalCapacity(vehicleTypeTotalCapacity);
        view.displayMessage("Total Parking Capacity: " + totalCapacity);
        break;
      case "Available Parking Capacity":
        VehicleType vehicleTypeAvailable = view.chooseVehicleType();
        int availableSpaces = parkingManager.getAvailableSpaces(vehicleTypeAvailable);
        view.displayMessage("Available Parking Spaces: " + availableSpaces);
        break;
      case "Vehicle Details":
        String parkedVehicleLicensePlate = view.getLicensePlateInput();
        Vehicle parkedVehicle = (Vehicle) parkingManager.getParkedVehicles().get(parkedVehicleLicensePlate);
        if (parkedVehicle != null) {
          view.displayMessage("Vehicle Details:");
          view.displayMessage(parkedVehicle.toString());
        } else {
          view.displayMessage("Vehicle not found.");
        }
        break;
      case "Membership Status":
        String vehicleMembershipLicensePlate = view.getLicensePlateInput();
        Vehicle vehicleMembership = (Vehicle) parkingManager.getParkedVehicles().get(vehicleMembershipLicensePlate);
        if (vehicleMembership != null) {
          boolean isMember = vehicleMembership.isMembership();
          if (isMember) {
            view.displayMessage("Vehicle is a member.");
          } else {
            view.displayMessage("Vehicle is not a member.");
          }
        } else {
          view.displayMessage("Vehicle not found.");
        }
        break;
      case "Parking Fee":
        String vehicleParkingFeeLicensePlate = view.getLicensePlateInput();
        Vehicle vehicleParkingFee = (Vehicle) parkingManager.getParkedVehicles().get(vehicleParkingFeeLicensePlate);
        if (vehicleParkingFee != null) {
          float parkingFee = vehicleParkingFee.getParkingFee();
          view.displayMessage("Parking fee: $" + parkingFee);
        } else {
          view.displayMessage("Vehicle not found.");
        }
        break;
      case "Recharge Parking Fee":
        String vehicleRechargeLicensePlate = view.getLicensePlateInput();
        Vehicle vehicleRecharge = (Vehicle) parkingManager.getParkedVehicles().get(vehicleRechargeLicensePlate);
        if (vehicleRecharge != null) {
          float rechargedFee = vehicleRecharge.rechargeParkingFee();
          view.displayMessage("Recharged parking fee: $" + rechargedFee);
        } else {
          view.displayMessage("Vehicle not found.");
        }
        break;
      case "Payment Status":
        String vehicleIsPaidLicensePlate = view.getLicensePlateInput();
        Vehicle vehicleIsPaid = (Vehicle) parkingManager.getParkedVehicles().get(vehicleIsPaidLicensePlate);
        if (vehicleIsPaid != null) {
          boolean isPaid = vehicleIsPaid.isPaidRechargeParkingFee();
          view.displayMessage("Is parking fee paid: " + isPaid);
        } else {
          view.displayMessage("Vehicle not found.");
        }
        break;
      case "Exit":
        exitProgram();
        break;
      default:
        view.showOptionError();
    }
  }

  public void exitProgram() {
    System.exit(0);
  }
}
