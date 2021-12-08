package com.example.AEPB.ParkingLot;

public class CarModel {
    private int carId;

    private String carNumber;

    public int getCarId() {
        return carId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public CarModel(int carId, String carNumber) {
        this.carId = carId;
        this.carNumber = carNumber;
    }
}
