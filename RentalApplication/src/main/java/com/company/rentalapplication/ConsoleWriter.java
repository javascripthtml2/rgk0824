package com.company.rentalapplication;

import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class ConsoleWriter implements IRentalAgreementWriter{

    private PrintStream printStream;

    public ConsoleWriter(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void writeOutRentalAgreement(RentalAgreement rentalAgreement) {
        String  agreementAsString = convertToStringForm(rentalAgreement);
        printStream.println(agreementAsString);
    }

    private String convertToStringForm(RentalAgreement rentalAgreement) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yy");
        DecimalFormat decimalFormat = new DecimalFormat("$#,##0.00");
        StringBuilder result = new StringBuilder();
        addLine(result,"Type code", rentalAgreement.getToolCode());
        addLine(result,"Type type", rentalAgreement.getToolType().getName());
        addLine(result,"Type brand", rentalAgreement.getToolBrand());
        addLine(result,"Rental days", String.valueOf(rentalAgreement.getRentalDays()));
        addLine(result,"Checkout date", simpleDateFormat.format(rentalAgreement.getCheckoutDate()));
        addLine(result,"Due date", simpleDateFormat.format(rentalAgreement.getDueDate()));
        addLine(result,"Daily rental charge", decimalFormat.format(rentalAgreement.getDailyRentalCharge()));
        addLine(result,"Charge days", String.valueOf(rentalAgreement.getChargeDays()));
        addLine(result,"Pre-discount charge", decimalFormat.format(rentalAgreement.getPreDiscountCharge()));
        addLine(result,"Discount percent", String.valueOf(rentalAgreement.getDiscountPercent()) + "%");
        addLine(result,"Discount amount", decimalFormat.format(rentalAgreement.getDiscountAmount()));
        addLine(result,"Final amount", decimalFormat.format(rentalAgreement.getFinalCharge()));
        return result.toString();
    }

    private void addLine(StringBuilder stringBuilder, String label, String value) {
        stringBuilder.append(label);
        stringBuilder.append(": ");
        stringBuilder.append(value);
        stringBuilder.append(System.lineSeparator());
    }

}
