package com.company.rentalapplication.calc;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PreDiscountChargeCalculator {

    public BigDecimal calculatePreDiscountCharge(BigDecimal chargeDays, BigDecimal dailyRate) {

        return dailyRate.multiply(chargeDays).setScale(2, RoundingMode.CEILING);
    }

}
