
public class MainCustomer{
  public static void main(String[] args) {
    BaseSetUpTest baseSetUpTest = new BaseSetUpTest();
    baseSetUpTest.setUp();

    ParkingManager parkingManager = BaseSetUpTest.parkingManager;

    PaymentSystem paymentSystem = BaseSetUpTest.paymentSystem;

    ParkingCustomerJFrameView frameView = new ParkingCustomerJFrameView("Parking Manager");

    ParkingCustomerController controller = new ParkingCustomerController(parkingManager, paymentSystem);

    frameView.addFeatures(controller);
    controller.setView(frameView);
  }

}
