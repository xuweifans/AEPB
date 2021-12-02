package com.example.AEPB.ParkingLot;

import java.util.Date;
import java.util.Objects;

/**
 * @author mark
 */
public class ParkingTicket {

    private final String licensePlate;

    private final Date entryTime;

    public ParkingTicket(String licensePlate) {
        this.licensePlate = licensePlate;
        this.entryTime = new Date();
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ParkingTicket that = (ParkingTicket) o;
        return Objects.equals(licensePlate, that.licensePlate) && Objects.equals(entryTime, that.entryTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(licensePlate, entryTime);
    }
}
