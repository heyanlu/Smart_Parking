
public class Main extends BaseSetUpTest{
  public static void main(String[] args) {
    new Main().setUp();

    ParkingSystemController controller = new ParkingSystemController(BaseSetUpTest.parkingManager, BaseSetUpTest.paymentSystem);

    controller.go();
  }
}
