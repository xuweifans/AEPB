package com.example.AEPB.AirCoin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class AirCoinTest {

    @Test
    void should_equals_when_compare_two_airCoin_amount_given_amount_is_11_and_amount_is_11() {
        //given
        AirCoin AirCoin1 = new AirCoin();
        AirCoin1.amount = BigDecimal.valueOf(11.0);
        AirCoin AirCoin2 = new AirCoin();
        AirCoin2.amount = BigDecimal.valueOf(11.0);

        //when
        Boolean b = AmountUtils.compareTwoAirCoinAmount(AirCoin1, AirCoin2);

        //then
        Assertions.assertTrue(b);
    }

    @Test
    void should_not_equals_when_compare_two_airCoin_amount_given_amount_is_11_dot_0_and_amount_is_12_dot_0() {
        //given
        AirCoin AirCoin1 = new AirCoin();
        AirCoin1.amount = BigDecimal.valueOf(11.0);
        AirCoin AirCoin2 = new AirCoin();
        AirCoin2.amount = BigDecimal.valueOf(12.0);

        //when
        Boolean b = AmountUtils.compareTwoAirCoinAmount(AirCoin1, AirCoin2);

        //then
        Assertions.assertFalse(b);
    }

    @Test
    public void should_throw_exception_when_amount_is_11_and_amount_999999999999999999999999999() {
        //given
        AirCoin AirCoin1 = new AirCoin();
        AirCoin1.amount = BigDecimal.valueOf(11.0);
        AirCoin AirCoin2 = new AirCoin();
        AirCoin2.amount = BigDecimal.valueOf(999999999999999999999999999.0);

        // when
        Throwable t = null;
        try {
            AmountUtils.compareTwoAirCoinAmount(AirCoin1, AirCoin2);
        } catch (Exception e) {
            t = e;
        }
        Assertions.assertTrue(t instanceof OutOfAmountRangeException);
    }
}
