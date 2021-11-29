package com.example.AEPB;

public class AmountUtils {


    public static Boolean compareTwoAirCoinAmount(AirCoin airCoin1, AirCoin airCoin2) {
        if (airCoin1.amount > 5000000.0 ){
            throw new OutOfRangeException();
        }
        if (airCoin2.amount > 5000000.0 ){
            throw new OutOfRangeException();
        }
        return airCoin1.amount.equals(airCoin2.amount);
    }
}
