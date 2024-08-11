package com.company.rentalapplication.holiday;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HolidayManager {

    private List<IHoliday> holidayList = new ArrayList<>();

    public HolidayManager() {
        loadHolidays();
    }

    private void loadHolidays() {
        holidayList.add(new IndependenceDay());
        holidayList.add(new LaborDay());
    }

    public boolean isConsideredAHoliday(Date holidayDate) {
        boolean result = false;
        for (IHoliday iHoliday : holidayList) {
            if (iHoliday.isHolidayDate(holidayDate)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
