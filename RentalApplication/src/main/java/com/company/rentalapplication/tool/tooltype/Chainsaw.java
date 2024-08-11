package com.company.rentalapplication.tool.tooltype;

import java.math.BigDecimal;

public class Chainsaw implements IToolType{
    public BigDecimal getDailyCharge() {
        return new BigDecimal("1.49");
    }

    public boolean hasWeekdayCharge() {
        return true;
    }

    public boolean hasWeekendCharge() {
        return false;
    }

    public boolean hasHolidayCharge() {
        return true;
    }

    public String getName() {
        return "Chainsaw";
    }
}
