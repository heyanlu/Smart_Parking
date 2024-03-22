import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class AbstractVehicleTest extends BaseSetUpTest{

    @Test
    public void testGetLicensePlate() {
        assertEquals("ABC123", car1.getLicensePlate());
        assertEquals("HIJ234", motorbike2.getLicensePlate());
        assertEquals("ABD789", truck2.getLicensePlate());
    }

    @Test
    public void testIsMembership() {
        assertTrue(car1.isMembership());
        assertFalse(car2.isMembership());

        assertTrue(motorbike1.isMembership());
        assertFalse(motorbike2.isMembership());
    }


    @Test
    public void testGetParkingRate() {
        assertEquals(1.5F, motorbike1.getParkingRate(), 0.01);
        assertEquals(4.5F, truck2.getParkingRate(), 0.01);

        assertEquals(2.0F, car1.getParkingRate(), 0.01);
    }

    @Test
    public void testGetParkingFee() {
        //free parking for membership car
        assertEquals(0.0F, car1.getParkingFee(), 0.01);
        assertEquals(4.0F, car2.getParkingFee(), 0.01);
        assertEquals(4.5F, motorbike2.getParkingFee(), 0.01);
        //free parking for first 30 minutes
        assertEquals(0.0F, truck1.getParkingFee(), 0.01);
        assertEquals(4.5F, truck2.getParkingFee(), 0.01);

    }

}