import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class ParkingManagerControllerTest {

  private ParkingManagerController parkingManagerController;
  private ParkingManager parkingManagerMock;
  private PaymentSystem paymentSystemMock;
  private IParkingManagerView viewMock;

  @BeforeEach
  void setUp() {
    parkingManagerMock = mock(ParkingManager.class);
    paymentSystemMock = mock(PaymentSystem.class);
    viewMock = mock(IParkingManagerView.class);

    parkingManagerController = new ParkingManagerController(parkingManagerMock, paymentSystemMock);
    parkingManagerController.setView(viewMock);
  }

  @Test
  void testTotalParkingCapacity() {
    when(viewMock.chooseVehicleType()).thenReturn(VehicleType.CAR);
    when(parkingManagerMock.getTotalCapacity(VehicleType.CAR)).thenReturn(100);

    parkingManagerController.optionExecution("Total Parking Capacity");

    verify(viewMock).displayMessage("Total Parking Capacity: 100");
  }
}
