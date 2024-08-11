package com.company.rentalapplication;

import com.company.rentalapplication.calc.ChargedDaysCalculator;
import com.company.rentalapplication.calc.DiscountAmountCalculator;
import com.company.rentalapplication.calc.DueDateCalculator;
import com.company.rentalapplication.calc.PreDiscountChargeCalculator;
import com.company.rentalapplication.tool.Tool;

import java.math.BigDecimal;
import java.util.Date;

public class RentalAgreementBuilder {

    public RentalAgreement buildAgreement(Tool tool, int rentalDays, Date checkoutDate, int discountPercent) {
        RentalAgreement result = new RentalAgreement();
        result.setToolCode(tool.getCode());
        result.setToolType(tool.getToolType());
        result.setToolBrand(tool.getBrand());
        result.setRentalDays(rentalDays);
        result.setCheckoutDate(checkoutDate);
        populateDueDate(result, rentalDays, checkoutDate);
        result.setDailyRentalCharge(tool.getToolType().getDailyCharge());
        populateChargeDays(result, tool, checkoutDate, result.getDueDate());
        populatePreDiscountCharge(result, result.getChargeDays(), tool);
        result.setDiscountPercent(discountPercent);
        populateDiscountAmount(result, result.getPreDiscountCharge(), result.getDiscountPercent());
        result.setFinalCharge(result.getPreDiscountCharge().subtract(result.getDiscountAmount()));
        return result;
    }

    private void populateDiscountAmount(RentalAgreement rentalAgreement, BigDecimal preDiscountCharge, int discountPercent) {
        DiscountAmountCalculator discountAmountCalculator = new DiscountAmountCalculator();
        BigDecimal discountAmount = discountAmountCalculator.calculateDiscountAmount(preDiscountCharge, discountPercent);
        rentalAgreement.setDiscountAmount(discountAmount);
    }

    private void populatePreDiscountCharge(RentalAgreement rentalAgreement, int chargeDays, Tool tool) {
        PreDiscountChargeCalculator preDiscountChargeCalculator = new PreDiscountChargeCalculator();
        BigDecimal preDiscountCharge = preDiscountChargeCalculator.calculatePreDiscountCharge(new BigDecimal(String.valueOf(chargeDays)), tool.getToolType().getDailyCharge());
        rentalAgreement.setPreDiscountCharge(preDiscountCharge);
    }

    private void populateDueDate(RentalAgreement rentalAgreement, int rentalDays, Date checkoutDate) {
        DueDateCalculator dueDateCalculator = new DueDateCalculator();
        rentalAgreement.setDueDate(dueDateCalculator.caculateDueDate(checkoutDate, rentalDays));
    }

    private void populateChargeDays(RentalAgreement rentalAgreement, Tool tool, Date checkoutDate, Date dueDate) {
        ChargedDaysCalculator chargedDaysCalculator = new ChargedDaysCalculator();
        rentalAgreement.setChargeDays(chargedDaysCalculator.calculateChargedDays(tool, checkoutDate, dueDate));
    }

}
