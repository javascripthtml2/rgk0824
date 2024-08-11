package com.company.rentalapplication.holiday;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

public class IndependenceDayTest {

    @Test
    public void isHolidayDateNotJulyTest() {
        IndependenceDay independenceDay = new IndependenceDay();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, Calendar.JUNE);
        assertFalse(independenceDay.isHolidayDate(calendar.getTime()));
    }

    @Test
    public void isHolidayDateExactDateTest() {
        IndependenceDay independenceDay = new IndependenceDay();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, Calendar.JULY);
        calendar.set(Calendar.DAY_OF_MONTH, 4);
        calendar.set(Calendar.YEAR, 2024);
        assertTrue(independenceDay.isHolidayDate(calendar.getTime()));
    }

    @Test
    public void isHolidayDateMondayAfterTest() {
        IndependenceDay independenceDay = new IndependenceDay();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, Calendar.JULY);
        calendar.set(Calendar.DAY_OF_MONTH, 4);
        calendar.set(Calendar.YEAR, 2021);
        assertFalse(independenceDay.isHolidayDate(calendar.getTime()));
        //Move to Monday
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        assertTrue(independenceDay.isHolidayDate(calendar.getTime()));
    }

    @Test
    public void isHolidayDateFridayBeforeTest() {
        IndependenceDay independenceDay = new IndependenceDay();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, Calendar.JULY);
        calendar.set(Calendar.DAY_OF_MONTH, 4);
        calendar.set(Calendar.YEAR, 2015);
        assertFalse(independenceDay.isHolidayDate(calendar.getTime()));
        //Move to Friday
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        assertTrue(independenceDay.isHolidayDate(calendar.getTime()));
    }
}
