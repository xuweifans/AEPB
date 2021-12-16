package com.example.AEPB.ParkingLot;

import com.example.AEPB.ParkingLot.Exceptions.CarNotFoundException;
import com.example.AEPB.ParkingLot.Exceptions.FakeTicketException;

import java.util.*;

/**
 * @author mark
 */
public class ParkingLot {

    private final Integer id;

    private final Integer maxParkingSpaceCount;

    @Deprecated
    private LinkedList<ParkingTicket> historyTickets = new LinkedList<>();

    private List<Car> cars = new ArrayList<>();

    public ParkingLot(Integer id, Integer maxParkingSpaceCount) {
        this.id = id;
        this.maxParkingSpaceCount = maxParkingSpaceCount;
    }

    public Integer getMaxParkingSpaceCount() {
        return maxParkingSpaceCount;
    }

    public Integer getId() {
        return id;
    }

    public List<Car> getCars() {
        return cars;
    }

    public ParkingTicket park(Car car) {
        if (cars.size() == maxParkingSpaceCount) {
            return null;
        }
        String licensePlate = car.getLicensePlate();
        ParkingTicket ticket = new ParkingTicket(id, licensePlate);
        historyTickets.add(ticket);
        cars.add(car);
        return ticket;
    }

    public Optional<Car> pickUpCar(ParkingTicket ticket) {
        String licensePlate = ticket.getLicensePlate();

        Optional<ParkingTicket> parkingTicket = historyTickets.stream().filter(t -> t.getLicensePlate().equals(licensePlate)).findFirst();
        if (parkingTicket.isEmpty()) {
            throw new FakeTicketException();
        }
        if (!parkingTicket.get().equals(ticket)) {
            throw new CarNotFoundException();
        }
        Optional<Car> car = cars.stream().filter(c -> c.getLicensePlate().equals(licensePlate)).findAny();
        historyTickets.remove(parkingTicket.get());
        return car;
    }

}
