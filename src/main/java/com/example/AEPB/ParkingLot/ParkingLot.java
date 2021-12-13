package com.example.AEPB.ParkingLot;

import com.example.AEPB.ParkingLot.Exceptions.CarNotFoundException;
import com.example.AEPB.ParkingLot.Exceptions.FakeTicketException;

import java.util.*;

/**
 * @author mark
 */
public class ParkingLot {

    public final int maxParkingSpaceCount = 500;

    private HashMap<String, ParkingTicket> parkingSelfTickets = new HashMap<>();

    private List<Car> cars = new ArrayList<>();


    @Deprecated
    public ParkingTicket entry(Car car) {
        if (cars.size() == maxParkingSpaceCount) {
            return null;
        }
        String licensePlate = car.getLicensePlate();
        ParkingTicket ticket = new ParkingTicket(licensePlate);
        parkingSelfTickets.put(licensePlate, ticket);
        cars.add(car);
        return ticket;
    }

    @Deprecated
    public Optional<Car> pickUpCar(ParkingTicket ticket) {
        String licensePlate = ticket.getLicensePlate();
        ParkingTicket parkingTicket = parkingSelfTickets.get(licensePlate);
        if (parkingTicket == null) {
            throw new FakeTicketException();
        }
        boolean equals = parkingTicket.equals(ticket);
        if (!equals) {
            throw new CarNotFoundException();
        }
        Optional<Car> car = cars.stream().filter(c -> c.getLicensePlate().equals(licensePlate)).findAny();
        parkingSelfTickets.remove(licensePlate);
        return car;
    }

}
