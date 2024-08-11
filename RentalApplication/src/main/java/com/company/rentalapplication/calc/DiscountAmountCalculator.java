package com.company.rentalapplication.calc;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DiscountAmountCalculator {

   public BigDecimal calculateDiscountAmount(BigDecimal preDiscountCharge, int discountPercentAsInt) {
       BigDecimal discountAsPercent = new BigDecimal(String.valueOf(discountPercentAsInt/100.0));
       return preDiscountCharge.multiply(discountAsPercent).setScale(2, RoundingMode.CEILING);
   }

}
