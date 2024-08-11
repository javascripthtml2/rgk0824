package com.company.rentalapplication.holiday;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

public class LaborDayTest {

    @Test
    public void isHolidayDateNotSepTest() {
        LaborDay laborDay = new LaborDay();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, Calendar.AUGUST);
        assertFalse(laborDay.isHolidayDate(calendar.getTime()));
    }

    @Test
    public void isHolidayDateExactDayTest() {
        LaborDay laborDay = new LaborDay();
        Calendar calendar = Calendar.getInstance();
        //Exact date
        calendar.set(Calendar.YEAR, 2024);
        calendar.set(Calendar.MONTH, Calendar.SEPTEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 2);
        assertTrue(laborDay.isHolidayDate(calendar.getTime()));

        //Not match
        calendar.set(Calendar.YEAR, 2024);
        calendar.set(Calendar.MONTH, Calendar.SEPTEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        assertFalse(laborDay.isHolidayDate(calendar.getTime()));

    }
}
