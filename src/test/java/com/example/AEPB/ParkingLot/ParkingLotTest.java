package com.example.AEPB.ParkingLot;

import com.example.AEPB.ParkingLot.Exceptions.FakeTicketException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertThrows;

public class ParkingLotTest {
    //happy path
    @Test
    public void should_pass_when_enter_given_parking_lot_current_space_count_is_200() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        for (int i = 0; i < 200; i++) {
            Car car = new Car(UUID.randomUUID().toString());
            parkingLot.entry(car);
        }
        Car car = new Car("陕A 12SF32");

        //when
        ParkingTicket ticket = parkingLot.entry(car);

        //then
        Assertions.assertNotNull(ticket);
        Assertions.assertEquals(ticket.getLicensePlate(), car.getLicensePlate());
    }

    @Test
    public void should_given_a_car_when_pick_up_given_a_right_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car myCar = new Car("陕A 12SF32");
        ParkingTicket ticket = parkingLot.entry(myCar);

        //when
        Optional<Car> car = parkingLot.pickUpCar(ticket);

        //then
        Assertions.assertTrue(car.isPresent());
        Assertions.assertEquals(myCar.getLicensePlate(), car.get().getLicensePlate());
    }

    //sad path
    @Test
    public void should_block_when_enter_given_parking_lot_current_space_count_full_with_500() {
        ParkingLot parkingLot = new ParkingLot();
        for (int i = 0; i < 500; i++) {
            Car car = new Car(UUID.randomUUID().toString());
            parkingLot.entry(car);
        }
        Car car = new Car("陕A 12SF32");

        //when
        ParkingTicket ticket = parkingLot.entry(car);

        //then
        Assertions.assertNull(ticket);

    }

    //exception
    @Test
    public void should_throw_fake_ticket_exception_when_pick_up_given_fake_paring_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingTicket ticket = new ParkingTicket("陕A 12SF32");

        //when
        assertThrows(FakeTicketException.class, () -> parkingLot.pickUpCar(ticket));
    }

}
