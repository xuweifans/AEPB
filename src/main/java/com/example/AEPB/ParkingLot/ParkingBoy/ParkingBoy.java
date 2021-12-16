package com.example.AEPB.ParkingLot.ParkingBoy;

import com.example.AEPB.ParkingLot.Car;
import com.example.AEPB.ParkingLot.ParkingTicket;

import java.util.Optional;

/**
 * @author mark
 */
public abstract class ParkingBoy {
    public abstract ParkingTicket park(Car car);
    public abstract Optional<Car> pick(ParkingTicket parkingTicket);

}
