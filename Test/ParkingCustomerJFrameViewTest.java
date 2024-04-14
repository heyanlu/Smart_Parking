import edu.northeastern.sv.khoury.smartParkTest.mock.ParkingCustomerJFrameViewMock;
import edu.northeastern.sv.khoury.smartParkTest.model.VehicleType;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ParkingCustomerJFrameViewTest {
  private ParkingCustomerJFrameViewMock view;

  @Before
  public void setUp() {
    view = new ParkingCustomerJFrameViewMock("Test");
  }

  @Test
  public void testButtonsNotNull() {
    assertNotNull("Park edu.northeastern.sv.khoury.smartPark.model.Vehicle button should not be null", view.getParkVehicleButton());
    assertNotNull("Process Payment button should not be null", view.getProcessPaymentButton());
    assertNotNull("Leave Parking Lot button should not be null", view.getLeaveParkingLotButton());
    assertNotNull("Exit button should not be null", view.getExitButton());
  }

  @Test
  public void testButtonsActionCommands() {
    assertEquals("Park edu.northeastern.sv.khoury.smartPark.model.Vehicle button should have action command 'Park edu.northeastern.sv.khoury.smartPark.model.Vehicle Button'", "Park edu.northeastern.sv.khoury.smartPark.model.Vehicle Button", view.getParkVehicleButton().getActionCommand());
    assertEquals("Process Payment button should have action command 'Process Payment Button'", "Process Payment Button", view.getProcessPaymentButton().getActionCommand());
    assertEquals("Leave Parking Lot button should have action command 'Process to Leave Button'", "Process to Leave Button", view.getLeaveParkingLotButton().getActionCommand());
    assertEquals("Exit button should have action command 'Exit Button'", "Exit Button", view.getExitButton().getActionCommand());
  }

  @Test
  public void testLicensePlateInput() {
    view.setLicensePlateInput("ABC123");
    assertEquals("ABC123", view.getLicensePlateInput());
  }

  @Test
  public void testChooseVehicleType() {
    view.setVehicleType(VehicleType.CAR);

    assertEquals("Expected vehicle type: CAR", VehicleType.CAR, view.chooseVehicleType());
  }

  @Test
  public void testGetInput() {
    view.setInput("Test input");
    assertEquals("Expected input: Test input", "Test input", view.getInput("Prompt"));
  }

  @Test
  public void testDisplayParkedDuration() {
    Duration duration = Duration.ofHours(1).plusMinutes(30);
    view.displayParkedDuration(duration);
    assertEquals("Expected duration: 1 hour 30 minutes", duration, view.getLastDisplayedDuration());
  }

  @Test
  public void testShowOptionError() {
    view.showOptionError();
  }
}
