import edu.northeastern.sv.khoury.smartParkTest.controller.ParkingCustomerController;
import edu.northeastern.sv.khoury.smartParkTest.model.ParkingManager;
import edu.northeastern.sv.khoury.smartParkTest.model.PaymentSystem;
import edu.northeastern.sv.khoury.smartParkTest.view.ParkingCustomerJFrameView;

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
