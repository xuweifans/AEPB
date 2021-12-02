package com.example.AEPB.AirCoin;

import java.math.BigDecimal;

public class AirCoin {

    BigDecimal amount;

    public boolean isUpperAmount(){
       return amount.compareTo(BigDecimal.valueOf(5000000.0)) > 0;
    }
}
