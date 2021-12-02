package com.example.AEPB.AirCoin;

public class AmountUtils {


    public static Boolean compareTwoAirCoinAmount(AirCoin airCoin1, AirCoin airCoin2) {
        if (airCoin1.isUpperAmount()) {
            throw new OutOfAmountRangeException();
        }
        if (airCoin2.isUpperAmount()) {
            throw new OutOfAmountRangeException();
        }
        return airCoin1.amount.equals(airCoin2.amount);
    }
}
