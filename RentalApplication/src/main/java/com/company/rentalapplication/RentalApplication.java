package com.company.rentalapplication;

import com.company.rentalapplication.tool.Tool;

import java.math.BigDecimal;
import java.util.Date;

public class RentalApplication {

    private RentalAgreementBuilder rentalAgreementBuilder;

    private Tool tool;

    public RentalApplication(RentalAgreementBuilder rentalAgreementBuilder) {
        this.rentalAgreementBuilder = rentalAgreementBuilder;
    }

    public void setToolToRent(Tool tool) {
        this.tool = tool;
    }

    public RentalAgreement checkout(int rentalDays, Date checkoutDate, int discountPercent) throws RentalApplicationException {
        RentalAgreement rentalAgreement;
        validateCheckout(rentalDays, discountPercent);
        rentalAgreement = rentalAgreementBuilder.buildAgreement(tool, rentalDays, checkoutDate, discountPercent);
        return rentalAgreement;
    }

    private void validateCheckout(int rentalDays, int discountPercent) throws RentalApplicationException{
        if (tool == null) {
            throw new RentalApplicationException("A tool must be selected.");
        }
        if (rentalDays <= 0) {
            throw new RentalApplicationException("Rental days must be 1 or greater.");
        }
        if (discountPercent > 100 || discountPercent < 0) {
            throw new RentalApplicationException("Discount percent must be in the range of 0 to 100.");
        }
    }
}
