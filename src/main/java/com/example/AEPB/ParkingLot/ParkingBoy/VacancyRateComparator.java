package com.example.AEPB.ParkingLot.ParkingBoy;

import com.example.AEPB.ParkingLot.ParkingLot;

import java.util.Comparator;

/**
 * @author mark
 */
public class VacancyRateComparator implements Comparator<ParkingLot> {

    @Override
    public int compare(ParkingLot val1, ParkingLot val2) {
        return calculateVacancyRate(val1).compareTo(calculateVacancyRate(val2));
    }

    private static Float calculateVacancyRate(ParkingLot parkingLot) {
        int carCount = parkingLot.getCars().size();
        int spaceCount = parkingLot.getMaxParkingSpaceCount();
        return (float)(spaceCount-carCount)/spaceCount;
    }


}