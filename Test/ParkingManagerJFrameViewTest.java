import edu.northeastern.sv.khoury.smartParkTest.mock.ParkingManagerJFrameViewMock;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class ParkingManagerJFrameViewTest {
  private ParkingManagerJFrameViewMock view;

  @Before
  public void setUp() {
    view = new ParkingManagerJFrameViewMock("Test");
  }

  @Test
  public void testButtonsNotNull() {
    assertNotNull("Add edu.northeastern.sv.khoury.smartPark.model.Membership button should not be null", view.getAddMembershipButton());
    assertNotNull("Remove edu.northeastern.sv.khoury.smartPark.model.Membership button should not be null", view.getRemoveMembershipButton());
    assertNotNull("Get edu.northeastern.sv.khoury.smartPark.model.Membership button should not be null", view.getGetMembershipButton());
    assertNotNull("Get edu.northeastern.sv.khoury.smartPark.model.Membership Count button should not be null", view.getGetMembershipCountButton());
    assertNotNull("Exit button should not be null", view.getExitButton());
  }

  @Test
  public void testButtonsActionCommands() {
    assertEquals("Add edu.northeastern.sv.khoury.smartPark.model.Membership button should have action command 'Add edu.northeastern.sv.khoury.smartPark.model.Membership Button'", "Add edu.northeastern.sv.khoury.smartPark.model.Membership Button", view.getAddMembershipButton().getActionCommand());
    assertEquals("Remove edu.northeastern.sv.khoury.smartPark.model.Membership button should have action command 'Remove edu.northeastern.sv.khoury.smartPark.model.Membership Button'", "Remove edu.northeastern.sv.khoury.smartPark.model.Membership Button", view.getRemoveMembershipButton().getActionCommand());
    assertEquals("Get edu.northeastern.sv.khoury.smartPark.model.Membership button should have action command 'Get edu.northeastern.sv.khoury.smartPark.model.Membership Button'", "Get edu.northeastern.sv.khoury.smartPark.model.Membership Button", view.getGetMembershipButton().getActionCommand());
    assertEquals("Get edu.northeastern.sv.khoury.smartPark.model.Membership Count button should have action command 'Get edu.northeastern.sv.khoury.smartPark.model.Membership Count Button'", "Get edu.northeastern.sv.khoury.smartPark.model.Membership Count Button", view.getGetMembershipCountButton().getActionCommand());
    assertEquals("Exit button should have action command 'Exit Button'", "Exit Button", view.getExitButton().getActionCommand());
  }

  @Test
  public void testGetInput() {
    view.setInput("Test input");

    assertEquals("Expected input: Test input", "Test input", view.getInput("Prompt"));
  }

  @Test
  public void testDisplayMessage() {
    view.displayMessage("Test message");
  }

  @Test
  public void testShowOptionError() {
    view.showOptionError();
  }
}
