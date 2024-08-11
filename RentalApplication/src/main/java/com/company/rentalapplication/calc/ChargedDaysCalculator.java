package com.company.rentalapplication.calc;

import com.company.rentalapplication.holiday.HolidayManager;
import com.company.rentalapplication.tool.Tool;
import com.company.rentalapplication.tool.tooltype.IToolType;

import java.util.Calendar;
import java.util.Date;

public class ChargedDaysCalculator {

    private HolidayManager holidayManager;

    public ChargedDaysCalculator() {
        holidayManager = new HolidayManager();
    }

    public int calculateChargedDays(Tool tool, Date checkoutDate, Date dueDate) {
        int result = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(checkoutDate);
        //Do not include checkout day
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        while (calendar.getTime().compareTo(dueDate) <= 0) {
            if (isChargeableDay(calendar, tool.getToolType())) {
                result++;
            }
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return result;
    }

    private boolean isChargeableDay(Calendar calendar, IToolType toolType) {
        boolean result = true;
        boolean isWeekdayDate = calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
                                && calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY;

        if (isWeekdayDate && !toolType.hasWeekdayCharge()) {
           result = false;
        }
        if (result && !isWeekdayDate && !toolType.hasWeekendCharge()) {
            result = false;
        }
        if (result && holidayManager.isConsideredAHoliday(calendar.getTime()) && !toolType.hasHolidayCharge()) {
            result = false;
        }
        return result;
    }

}
