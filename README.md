## Description: 
This parking lot system is developed in Java, focusing on Object-Oriented Design (OOD) principles and implementing 
the Model-View-Controller (MVC) pattern. It provides a comprehensive solution for managing parking spaces in various settings, 
catering to both customers and managers.

## Usage: 
Customer Module:
Run MainCustomer.java (Test - main - MainCustomer) to access the customer interface.
Manager Module:
Run MainManager.java (Test - main - MainManager) to access the manager interface.

## Structure of Model 
IParkingManger (interface) - ParkingManager 
Vehicle (interface) - AbstractVehicle (superclass) - Truck, Car, Motorbike
IPaymentSystem (interface) - PaymentSystem (ParkingFeeCalculator is a componet of PaymentSystem)
Membership is a component of MembershipSystem

## Testing: 
The testing suite includes the following components:
BaseSetUp.java: Base setup code for test classes.
ParkingManagerTest.java: Test cases for the ParkingManager class (the main part of the OOD).
ControllerTest: Package containing test code for both ParkingCustomerController and ParkingManagerController.
ViewTest: Package containing test code for both ParkingCustomerJFrameView and ParkingManagerJFrameView.

## Author:
Yanlu He


