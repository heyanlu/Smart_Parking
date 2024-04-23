package edu.northeastern.sv.khoury.smartPark.controller;

/**
 * The Feature interface defines methods for executing options and exiting the program.
 */
public interface Feature {

  /**
   * Executes a specific option.
   *
   * @param option The option to be executed.
   */
  void optionExecution(String option);

  /**
   * Exits the program.
   */
  void exitProgram();

}
