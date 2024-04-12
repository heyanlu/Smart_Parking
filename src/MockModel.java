//implement the interface of two models
public class MockModel implements ParkingSystemModel{
  private String input;
  private String output;

  @Override
  public void setInput(String input) {
    this.input = input;
  }

  @Override
  public String getOutput() {
    return output;
  }
}

