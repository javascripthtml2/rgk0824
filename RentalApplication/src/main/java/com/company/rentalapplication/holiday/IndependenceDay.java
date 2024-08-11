package com.company.rentalapplication.holiday;

import java.util.Calendar;
import java.util.Date;

public class IndependenceDay implements IHoliday{

    public boolean isHolidayDate(Date dateToExamine) {
        boolean result = false;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateToExamine);
        if (calendar.get(Calendar.MONTH) == Calendar.JULY) {
            Calendar holidayDateForYear = calculateYearHolidayDate(calendar.get(Calendar.YEAR));
            if (holidayDateForYear.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)) {
                result = true;
            }
        }
        return result;
    }

    private Calendar calculateYearHolidayDate(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JULY);
        calendar.set(Calendar.DAY_OF_MONTH, 4);
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            calendar.add(Calendar.DAY_OF_MONTH, -1);
        } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return calendar;
    }
}
