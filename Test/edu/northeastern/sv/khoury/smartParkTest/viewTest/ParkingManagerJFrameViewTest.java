package edu.northeastern.sv.khoury.smartParkTest.viewTest;

import edu.northeastern.sv.khoury.smartParkTest.mock.ParkingManagerJFrameViewMock;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Test code for ParkingManagerJFrameView.
 */
public class ParkingManagerJFrameViewTest {
  private ParkingManagerJFrameViewMock view;

  /**
   * Initialize component for the test class
   */
  @Before
  public void setUp() {
    view = new ParkingManagerJFrameViewMock("Test");
  }

  /**
   * Test that ensures all buttons in the view are not null.
   */
  @Test
  public void testButtonsNotNull() {
    assertNotNull(view.totalParkingCapacityButton());
    assertNotNull( view.availableParkingCapacityButton());
    assertNotNull(view.vehicleDetailsButton());
    assertNotNull(view.membershipStatusButton());
    assertNotNull( view.getExitButton());
  }

  /**
   * Test that verifies the action commands of the buttons.
   */
  @Test
  public void testButtonsActionCommands() {
    assertEquals("Total Parking Capacity", view.totalParkingCapacityButton().getActionCommand());
    assertEquals("Available Parking Capacity", view.availableParkingCapacityButton().getActionCommand());
    assertEquals( "Vehicle Details", view.vehicleDetailsButton().getActionCommand());
    assertEquals("Membership Status", view.membershipStatusButton().getActionCommand());
    assertEquals("Exit", view.getExitButton().getActionCommand());
  }

  /**
   * Test the getInput() method by setting an input and then retrieving it to ensure it matches the expected value.
   */
  @Test
  public void testGetInput() {
    String expect = "Test input";
    view.setInput(expect);
    assertEquals(expect, view.getInput("Prompt"));
  }

  /**
   * Test the getInput() method when no input has been set, ensuring it returns null.
   */
  @Test
  public void testGetInputNull() {
    assertNull(view.getInput("Prompt"));
  }

  /**
   * Test the displayMessage() method by setting a message and then retrieving the last displayed message to ensure it matches the expected value.
   */
  @Test
  public void testDisplayMessage() {
    String expect = "Test message";
    view.displayMessage(expect);
    assertEquals(expect, view.getLastDisplayedMessage());
  }

  /**
   * Test the getLastDisplayedMessage() method when no message has been displayed, ensuring it returns null.
   */
  @Test
  public void testDisplayMessageNull() {
    assertNull(view.getLastDisplayedMessage());
  }

  /**
   * Test the showOptionError() method.
   */
  @Test
  public void testShowOptionError() {
    view.showOptionError();
  }
}
