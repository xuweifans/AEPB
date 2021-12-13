package com.example.AEPB.ParkingLot;

import com.example.AEPB.ParkingLot.Exceptions.SameCarParkingSameTimeException;
import org.junit.Assert;
import org.junit.Test;

public class GraduteParkingLotTest {

    @Test
    public void should_park_success_when_enter_first_parking_lot_given_first_parkingLot_can_park(){
        //given
        PLAdmin plAdmin = new PLAdmin();

        GraduteParkingLot p = new GraduteParkingLot(plAdmin, 2, 0);

        CarModel car1 = new CarModel(1,"A01");

        CarModel car2 = new CarModel(2,"A02");

        //when
        int park1 = p.park(car1);

        int park2 = p.park(car2);

        //then
        Assert.assertEquals(1, park1);

        Assert.assertEquals(2, park2);
    }

    @Test(expected = SameCarParkingSameTimeException.class)
    public void should_park_failed_when_one_car_enter_parking_lot_given_same_car_has_parking() {
        PLAdmin plAdmin = new PLAdmin();

        GraduteParkingLot p = new GraduteParkingLot(plAdmin, 2, 0);

        CarModel car1 = new CarModel(1,"A01");

        CarModel car2 = new CarModel(2,"A01");

        // when
        // then
        p.park(car1);

        p.park(car2);
    }
}
