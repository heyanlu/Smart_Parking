import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

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
    assertNotNull("Add Membership button should not be null", view.getAddMembershipButton());
    assertNotNull("Remove Membership button should not be null", view.getRemoveMembershipButton());
    assertNotNull("Get Membership button should not be null", view.getGetMembershipButton());
    assertNotNull("Get Membership Count button should not be null", view.getGetMembershipCountButton());
    assertNotNull("Exit button should not be null", view.getExitButton());
  }

  @Test
  public void testButtonsActionCommands() {
    assertEquals("Add Membership button should have action command 'Add Membership Button'", "Add Membership Button", view.getAddMembershipButton().getActionCommand());
    assertEquals("Remove Membership button should have action command 'Remove Membership Button'", "Remove Membership Button", view.getRemoveMembershipButton().getActionCommand());
    assertEquals("Get Membership button should have action command 'Get Membership Button'", "Get Membership Button", view.getGetMembershipButton().getActionCommand());
    assertEquals("Get Membership Count button should have action command 'Get Membership Count Button'", "Get Membership Count Button", view.getGetMembershipCountButton().getActionCommand());
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
