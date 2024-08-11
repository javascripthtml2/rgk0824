package com.company.rentalapplication.tool.tooltype;

import java.math.BigDecimal;

public interface IToolType {

    BigDecimal getDailyCharge();

    boolean hasWeekdayCharge();

    boolean hasWeekendCharge();

    boolean hasHolidayCharge();

    String getName();
}
