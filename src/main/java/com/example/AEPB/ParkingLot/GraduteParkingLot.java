package com.example.AEPB.ParkingLot;

import com.example.AEPB.ParkingLot.Exceptions.SameCarParkingSameTimeException;

import java.util.HashMap;
import java.util.Map;

@Deprecated
public class GraduteParkingLot {

    private PLAdmin plAdmin;

    private final int eachParkingLotSize;

    private int ticketNum;

    private Map<Integer, CarModel> parkingLotMap;

    public GraduteParkingLot(PLAdmin plAdmin, int eachParkingLotSize, int ticketNum) {
        this.plAdmin = plAdmin;
        this.eachParkingLotSize = eachParkingLotSize;
        this.ticketNum = ticketNum;

        this.parkingLotMap = new HashMap<Integer, CarModel>();
    }

    public int park(CarModel car) {
        this.ticketNum++;
        if (this.parkingLotMap.size() >= eachParkingLotSize) {
            // throw parking is full
        } else {
            if (parkingLotMap.containsValue(car)) {
                throw new SameCarParkingSameTimeException();
            } else {
                parkingLotMap.put(ticketNum, car);
            }
        }
        return ticketNum;
    }

}
