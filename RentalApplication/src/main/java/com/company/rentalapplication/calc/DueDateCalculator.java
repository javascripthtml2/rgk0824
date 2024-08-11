package com.company.rentalapplication.calc;

import java.util.Calendar;
import java.util.Date;

public class DueDateCalculator {

    public Date caculateDueDate(Date checkoutDate, int numberRentalDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(checkoutDate);
        calendar.add(Calendar.DAY_OF_MONTH, numberRentalDays);
        return calendar.getTime();
    }
}
