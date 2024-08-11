package com.company.rentalapplication.tool.tooltype;

import java.math.BigDecimal;

public class Jackhammer implements IToolType{
    public BigDecimal getDailyCharge() {
        return new BigDecimal("2.99");
    }

    public boolean hasWeekdayCharge() {
        return true;
    }

    public boolean hasWeekendCharge() {
        return false;
    }

    public boolean hasHolidayCharge() {
        return false;
    }

    public String getName() {
        return "Jackhammer";
    }

}
