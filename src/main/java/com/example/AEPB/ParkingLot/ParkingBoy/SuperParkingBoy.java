package com.example.AEPB.ParkingLot.ParkingBoy;

import com.example.AEPB.ParkingLot.Car;
import com.example.AEPB.ParkingLot.Exceptions.SameCarParkingSameTimeException;
import com.example.AEPB.ParkingLot.ParkingLot;
import com.example.AEPB.ParkingLot.ParkingTicket;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * @author mark
 */
public class SuperParkingBoy extends ParkingBoy {

    private List<ParkingLot> parkingLots;

    private LinkedList<ParkingTicket> historyTickets = new LinkedList<>();


    public SuperParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }


    private ParkingLot getHighestVacancyRateParkingLot() {
        return parkingLots.stream().max(new VacancyRateComparator()).orElse(null);
    }


    @Override
    public ParkingTicket park(Car car) {
        long count = historyTickets.stream().filter(ticket -> ticket.getLicensePlate().equals(car.getLicensePlate())).count();
        if (count > 0) {
            throw new SameCarParkingSameTimeException();
        }
        ParkingTicket ticket = getHighestVacancyRateParkingLot().park(car);
        historyTickets.add(ticket);
        return ticket;
    }


    @Override
    public Optional<Car> pick(ParkingTicket parkingTicket) {
        Optional<ParkingLot> optionalParkingLot = parkingLots
                .stream().filter(parkingLot -> parkingTicket.getParkingLotId().equals(parkingLot.getId())).findFirst();
        if (optionalParkingLot.isPresent()) {
            historyTickets.remove(parkingTicket);
        }
        return optionalParkingLot.flatMap(parkingLot -> parkingLot.pickUpCar(parkingTicket));
    }
}
