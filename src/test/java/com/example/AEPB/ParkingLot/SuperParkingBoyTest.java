package com.example.AEPB.ParkingLot;

import com.example.AEPB.ParkingLot.Exceptions.FakeTicketException;
import com.example.AEPB.ParkingLot.Exceptions.SameCarParkingSameTimeException;
import com.example.AEPB.ParkingLot.ParkingBoy.SuperParkingBoy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertThrows;

public class SuperParkingBoyTest {
    //happy path
    @Test
    public void should_park_in_0_dot_7_vacancy_rate_parking_lot_when_park_given_parking_lots_0_dot_6_rate_and_0_dot_7_rate_and_0_dot_25_rate() {
        //given
        //0.6
        ParkingLot parkingLot1 = new ParkingLot(1, 500);
        for (int i = 0; i < 200; i++) {
            Car car = new Car(UUID.randomUUID().toString());
            parkingLot1.park(car);
        }

        //0.7
        ParkingLot parkingLot2 = new ParkingLot(2, 600);
        for (int i = 0; i < 180; i++) {
            Car car = new Car(UUID.randomUUID().toString());
            parkingLot2.park(car);
        }

        //0.25
        ParkingLot parkingLot3 = new ParkingLot(3, 800);
        for (int i = 0; i < 600; i++) {
            Car car = new Car(UUID.randomUUID().toString());
            parkingLot3.park(car);
        }

        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        parkingLots.add(parkingLot3);


        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);
        Car car = new Car("陕A 12SF32");

        //when
        ParkingTicket ticket = superParkingBoy.park(car);

        //then
        Assertions.assertNotNull(ticket);
        Assertions.assertEquals(ticket.getLicensePlate(), car.getLicensePlate());
        Assertions.assertEquals(ticket.getParkingLotId(), 2);
    }

    @Test
    public void should_given_a_car_when_pick_up_given_a_right_ticket() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(1, 500);
        ParkingLot parkingLot2 = new ParkingLot(2, 500);
        ParkingTicket ticket = parkingLot1.park(new Car("陕A 12SF32"));
        parkingLot2.park(new Car("陕A 12SF32"));
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);

        //when
        Optional<Car> car = superParkingBoy.pick(ticket);

        //then
        Assertions.assertTrue(car.isPresent());
        Assertions.assertEquals(new Car("陕A 12SF32").getLicensePlate(), car.get().getLicensePlate());
    }

    //sad path
    @Test
    public void should_block_when_enter_given_parking_lot_current_space_count_full_with_500() {
        ParkingLot parkingLot1 = new ParkingLot(1, 500);
        for (int i = 0; i < 500; i++) {
            Car car = new Car(UUID.randomUUID().toString());
            parkingLot1.park(car);
        }
        ParkingLot parkingLot2 = new ParkingLot(2, 800);
        for (int i = 0; i < 800; i++) {
            Car car = new Car(UUID.randomUUID().toString());
            parkingLot2.park(car);
        }
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);

        Car car = new Car("陕A 12SF32");

        //when
        ParkingTicket ticket = superParkingBoy.park(car);

        //then
        Assertions.assertNull(ticket);

    }

    //exception
    @Test
    public void should_throw_fake_ticket_exception_when_pick_up_given_fake_paring_ticket() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(1, 500);
        ParkingLot parkingLot2 = new ParkingLot(2, 500);
        parkingLot1.park(new Car("陕A 12SF32"));
        parkingLot2.park(new Car("陕A 31F131"));
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);


        ParkingTicket ticket = new ParkingTicket(1, "陕A 88888");

        //when
        assertThrows(FakeTicketException.class, () -> superParkingBoy.pick(ticket));
    }

    //exception
    @Test
    public void should_throw_same_car_exception_when_pick_up_given_parked_car() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(1, 500);
        ParkingLot parkingLot2 = new ParkingLot(2, 500);

        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);
        superParkingBoy.park(new Car("陕A 12SF32"));
        superParkingBoy.park(new Car("陕A 31F131"));

        Car parkedCar = new Car("陕A 31F131");

        //when
        assertThrows(SameCarParkingSameTimeException.class, () -> superParkingBoy.park(parkedCar));
    }
}
