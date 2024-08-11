package com.company.rentalapplication.tool.tooltype;

import java.math.BigDecimal;

public class Ladder implements IToolType {

    public BigDecimal getDailyCharge() {
        return new BigDecimal("1.99");
    }

    public boolean hasWeekdayCharge() {
        return true;
    }

    public boolean hasWeekendCharge() {
        return true;
    }

    public boolean hasHolidayCharge() {
        return false;
    }

    public String getName() {
        return "Ladder";
    }
}
