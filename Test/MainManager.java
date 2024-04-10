public class MainManager {
  public static void main(String[] args) {
    BaseSetUpTest baseSetUpTest = new BaseSetUpTest();
    baseSetUpTest.setUp();

    ParkingManager parkingManager = BaseSetUpTest.parkingManager;

    PaymentSystem paymentSystem = BaseSetUpTest.paymentSystem;

    ParkingManagerJFrameView frameView = new ParkingManagerJFrameView("Parking Manager");

    ParkingManagerController controller = new ParkingManagerController(parkingManager, paymentSystem);

    frameView.addFeatures(controller);
    controller.setView(frameView);
  }
}

