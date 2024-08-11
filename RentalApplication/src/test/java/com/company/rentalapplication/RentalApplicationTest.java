package com.company.rentalapplication;

import com.company.rentalapplication.tool.ToolHolder;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

public class RentalApplicationTest {

    private static final String TEST_2_EXPECTED = "Type code: LADW" +  System.lineSeparator() +
            "Type type: Ladder" + System.lineSeparator() +
            "Type brand: Werner" + System.lineSeparator() +
            "Rental days: 3" + System.lineSeparator() +
            "Checkout date: 07/02/20" + System.lineSeparator() +
            "Due date: 07/05/20" + System.lineSeparator() +
            "Daily rental charge: $1.99" + System.lineSeparator() +
            "Charge days: 2" + System.lineSeparator() +
            "Pre-discount charge: $3.98" + System.lineSeparator() +
            "Discount percent: 10%" + System.lineSeparator() +
            "Discount amount: $0.40" + System.lineSeparator() +
            "Final amount: $3.58";

    private static final String TEST_3_EXPECTED = "Type code: CHNS" +  System.lineSeparator() +
            "Type type: Chainsaw" +  System.lineSeparator() +
            "Type brand: Stihl" +  System.lineSeparator() +
            "Rental days: 5" +  System.lineSeparator() +
            "Checkout date: 07/02/15" +  System.lineSeparator() +
            "Due date: 07/07/15" +  System.lineSeparator() +
            "Daily rental charge: $1.49" +  System.lineSeparator() +
            "Charge days: 3" +  System.lineSeparator() +
            "Pre-discount charge: $4.47" +  System.lineSeparator() +
            "Discount percent: 25%" +  System.lineSeparator() +
            "Discount amount: $1.12" +  System.lineSeparator() +
            "Final amount: $3.35";


    private static final String TEST_4_EXPECTED = "Type code: JAKD" +  System.lineSeparator() +
            "Type type: Jackhammer" +  System.lineSeparator() +
            "Type brand: DeWalt" +  System.lineSeparator() +
            "Rental days: 6" +  System.lineSeparator() +
            "Checkout date: 09/03/15" +  System.lineSeparator() +
            "Due date: 09/09/15" +  System.lineSeparator() +
            "Daily rental charge: $2.99" +  System.lineSeparator() +
            "Charge days: 3" +  System.lineSeparator() +
            "Pre-discount charge: $8.97" +  System.lineSeparator() +
            "Discount percent: 0%" +  System.lineSeparator() +
            "Discount amount: $0.00" +  System.lineSeparator() +
            "Final amount: $8.97";

    private static final String TEST_5_EXPECTED = "Type code: JAKR" +  System.lineSeparator() +
            "Type type: Jackhammer" +  System.lineSeparator() +
            "Type brand: Ridgid" +  System.lineSeparator() +
            "Rental days: 9" +  System.lineSeparator() +
            "Checkout date: 07/02/15" +  System.lineSeparator() +
            "Due date: 07/11/15" +  System.lineSeparator() +
            "Daily rental charge: $2.99" +  System.lineSeparator() +
            "Charge days: 5" +  System.lineSeparator() +
            "Pre-discount charge: $14.95" +  System.lineSeparator() +
            "Discount percent: 0%" +  System.lineSeparator() +
            "Discount amount: $0.00" +  System.lineSeparator() +
            "Final amount: $14.95";

    private static final String TEST_6_EXPECTED = "Type code: JAKR" +  System.lineSeparator() +
            "Type type: Jackhammer" +  System.lineSeparator() +
            "Type brand: Ridgid" +  System.lineSeparator() +
            "Rental days: 4" +  System.lineSeparator() +
            "Checkout date: 07/02/20" +  System.lineSeparator() +
            "Due date: 07/06/20" +  System.lineSeparator() +
            "Daily rental charge: $2.99" +  System.lineSeparator() +
            "Charge days: 1" +  System.lineSeparator() +
            "Pre-discount charge: $2.99" +  System.lineSeparator() +
            "Discount percent: 50%" +  System.lineSeparator() +
            "Discount amount: $1.50" +  System.lineSeparator() +
            "Final amount: $1.49";

    @Test
    public void checkoutTest1() {
        try {
            ToolHolder toolHolder = new ToolHolder();
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, 2015);
            calendar.set(Calendar.MONTH, Calendar.SEPTEMBER);
            calendar.set(Calendar.DAY_OF_MONTH, 3);
            RentalApplication rentalApplication = new RentalApplication(new RentalAgreementBuilder());
            rentalApplication.setToolToRent(toolHolder.getTool("JAKR"));
            rentalApplication.checkout(5, calendar.getTime(), 101);
            fail("Did not get expected exception.");
        } catch (RentalApplicationException e) {
            assertEquals("Discount percent must be in the range of 0 to 100.", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void checkoutTest2() {
        try {

            ToolHolder toolHolder = new ToolHolder();
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, 2020);
            calendar.set(Calendar.MONTH, Calendar.JULY);
            calendar.set(Calendar.DAY_OF_MONTH, 2);
            RentalApplication rentalApplication = new RentalApplication(new RentalAgreementBuilder());
            rentalApplication.setToolToRent(toolHolder.getTool("LADW"));
            RentalAgreement rentalAgreement = rentalApplication.checkout(3, calendar.getTime(), 10);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(byteArrayOutputStream);
            ConsoleWriter consoleWriter = new ConsoleWriter(printStream);
            consoleWriter.writeOutRentalAgreement(rentalAgreement);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yy");
            assertEquals("LADW", rentalAgreement.getToolCode());
            assertEquals("Ladder", rentalAgreement.getToolType().getName());
            assertEquals("Werner", rentalAgreement.getToolBrand());
            assertEquals(3, rentalAgreement.getRentalDays());
            assertEquals("07/02/20", simpleDateFormat.format(rentalAgreement.getCheckoutDate()));
            assertEquals("07/05/20", simpleDateFormat.format(rentalAgreement.getDueDate()));
            assertEquals(1.99, rentalAgreement.getDailyRentalCharge().doubleValue());
            assertEquals(2, rentalAgreement.getChargeDays());
            assertEquals(3.98, rentalAgreement.getPreDiscountCharge().doubleValue());
            assertEquals(10, rentalAgreement.getDiscountPercent());
            assertEquals(.4, rentalAgreement.getDiscountAmount().doubleValue());
            assertEquals(3.58, rentalAgreement.getFinalCharge().doubleValue());
            assertEquals(TEST_2_EXPECTED, byteArrayOutputStream.toString().trim());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void checkoutTest3() {
        try {

            ToolHolder toolHolder = new ToolHolder();
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, 2015);
            calendar.set(Calendar.MONTH, Calendar.JULY);
            calendar.set(Calendar.DAY_OF_MONTH, 2);
            RentalApplication rentalApplication = new RentalApplication(new RentalAgreementBuilder());
            rentalApplication.setToolToRent(toolHolder.getTool("CHNS"));
            RentalAgreement rentalAgreement = rentalApplication.checkout(5, calendar.getTime(), 25);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(byteArrayOutputStream);
            ConsoleWriter consoleWriter = new ConsoleWriter(printStream);
            consoleWriter.writeOutRentalAgreement(rentalAgreement);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yy");
            assertEquals("CHNS", rentalAgreement.getToolCode());
            assertEquals("Chainsaw", rentalAgreement.getToolType().getName());
            assertEquals("Stihl", rentalAgreement.getToolBrand());
            assertEquals(5, rentalAgreement.getRentalDays());
            assertEquals("07/02/15", simpleDateFormat.format(rentalAgreement.getCheckoutDate()));
            assertEquals("07/07/15", simpleDateFormat.format(rentalAgreement.getDueDate()));
            assertEquals(1.49, rentalAgreement.getDailyRentalCharge().doubleValue());
            assertEquals(3, rentalAgreement.getChargeDays());
            assertEquals(4.47, rentalAgreement.getPreDiscountCharge().doubleValue());
            assertEquals(25, rentalAgreement.getDiscountPercent());
            assertEquals(1.12, rentalAgreement.getDiscountAmount().doubleValue());
            assertEquals(3.35, rentalAgreement.getFinalCharge().doubleValue());
            assertEquals(TEST_3_EXPECTED, byteArrayOutputStream.toString().trim());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void checkoutTest4() {
        try {

            ToolHolder toolHolder = new ToolHolder();
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, 2015);
            calendar.set(Calendar.MONTH, Calendar.SEPTEMBER);
            calendar.set(Calendar.DAY_OF_MONTH, 3);
            RentalApplication rentalApplication = new RentalApplication(new RentalAgreementBuilder());
            rentalApplication.setToolToRent(toolHolder.getTool("JAKD"));
            RentalAgreement rentalAgreement = rentalApplication.checkout(6, calendar.getTime(), 0);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(byteArrayOutputStream);
            ConsoleWriter consoleWriter = new ConsoleWriter(printStream);
            consoleWriter.writeOutRentalAgreement(rentalAgreement);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yy");
            assertEquals("JAKD", rentalAgreement.getToolCode());
            assertEquals("Jackhammer", rentalAgreement.getToolType().getName());
            assertEquals("DeWalt", rentalAgreement.getToolBrand());
            assertEquals(6, rentalAgreement.getRentalDays());
            assertEquals("09/03/15", simpleDateFormat.format(rentalAgreement.getCheckoutDate()));
            assertEquals("09/09/15", simpleDateFormat.format(rentalAgreement.getDueDate()));
            assertEquals(2.99, rentalAgreement.getDailyRentalCharge().doubleValue());
            assertEquals(3, rentalAgreement.getChargeDays());
            assertEquals(8.97, rentalAgreement.getPreDiscountCharge().doubleValue());
            assertEquals(0, rentalAgreement.getDiscountPercent());
            assertEquals(0.00, rentalAgreement.getDiscountAmount().doubleValue());
            assertEquals(8.97, rentalAgreement.getFinalCharge().doubleValue());
            assertEquals(TEST_4_EXPECTED, byteArrayOutputStream.toString().trim());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void checkoutTest5() {
        try {

            ToolHolder toolHolder = new ToolHolder();
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, 2015);
            calendar.set(Calendar.MONTH, Calendar.JULY);
            calendar.set(Calendar.DAY_OF_MONTH, 2);
            RentalApplication rentalApplication = new RentalApplication(new RentalAgreementBuilder());
            rentalApplication.setToolToRent(toolHolder.getTool("JAKR"));
            RentalAgreement rentalAgreement = rentalApplication.checkout(9, calendar.getTime(), 0);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(byteArrayOutputStream);
            ConsoleWriter consoleWriter = new ConsoleWriter(printStream);
            consoleWriter.writeOutRentalAgreement(rentalAgreement);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yy");
            assertEquals("JAKR", rentalAgreement.getToolCode());
            assertEquals("Jackhammer", rentalAgreement.getToolType().getName());
            assertEquals("Ridgid", rentalAgreement.getToolBrand());
            assertEquals(9, rentalAgreement.getRentalDays());
            assertEquals("07/02/15", simpleDateFormat.format(rentalAgreement.getCheckoutDate()));
            assertEquals("07/11/15", simpleDateFormat.format(rentalAgreement.getDueDate()));
            assertEquals(2.99, rentalAgreement.getDailyRentalCharge().doubleValue());
            assertEquals(5, rentalAgreement.getChargeDays());
            assertEquals(14.95, rentalAgreement.getPreDiscountCharge().doubleValue());
            assertEquals(0, rentalAgreement.getDiscountPercent());
            assertEquals(0.00, rentalAgreement.getDiscountAmount().doubleValue());
            assertEquals(14.95, rentalAgreement.getFinalCharge().doubleValue());
            assertEquals(TEST_5_EXPECTED, byteArrayOutputStream.toString().trim());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void checkoutTest6() {
        try {

            ToolHolder toolHolder = new ToolHolder();
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, 2020);
            calendar.set(Calendar.MONTH, Calendar.JULY);
            calendar.set(Calendar.DAY_OF_MONTH, 2);
            RentalApplication rentalApplication = new RentalApplication(new RentalAgreementBuilder());
            rentalApplication.setToolToRent(toolHolder.getTool("JAKR"));
            RentalAgreement rentalAgreement = rentalApplication.checkout(4, calendar.getTime(), 50);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(byteArrayOutputStream);
            ConsoleWriter consoleWriter = new ConsoleWriter(printStream);
            consoleWriter.writeOutRentalAgreement(rentalAgreement);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yy");
            assertEquals("JAKR", rentalAgreement.getToolCode());
            assertEquals("Jackhammer", rentalAgreement.getToolType().getName());
            assertEquals("Ridgid", rentalAgreement.getToolBrand());
            assertEquals(4, rentalAgreement.getRentalDays());
            assertEquals("07/02/20", simpleDateFormat.format(rentalAgreement.getCheckoutDate()));
            assertEquals("07/06/20", simpleDateFormat.format(rentalAgreement.getDueDate()));
            assertEquals(2.99, rentalAgreement.getDailyRentalCharge().doubleValue());
            assertEquals(1, rentalAgreement.getChargeDays());
            assertEquals(2.99, rentalAgreement.getPreDiscountCharge().doubleValue());
            assertEquals(50, rentalAgreement.getDiscountPercent());
            assertEquals(1.50, rentalAgreement.getDiscountAmount().doubleValue());
            assertEquals(1.49, rentalAgreement.getFinalCharge().doubleValue());
            assertEquals(TEST_6_EXPECTED, byteArrayOutputStream.toString().trim());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void checkoutInvalidRentalDaysTest() {
        try {
            ToolHolder toolHolder = new ToolHolder();
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, 2015);
            calendar.set(Calendar.MONTH, Calendar.SEPTEMBER);
            calendar.set(Calendar.DAY_OF_MONTH, 3);
            RentalApplication rentalApplication = new RentalApplication(new RentalAgreementBuilder());
            rentalApplication.setToolToRent(toolHolder.getTool("JAKR"));
            rentalApplication.checkout(0, calendar.getTime(), 50);
            fail("Did not get expected exception.");
        } catch (RentalApplicationException e) {
            assertEquals("Rental days must be 1 or greater.", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void checkoutNoToolTest() {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, 2015);
            calendar.set(Calendar.MONTH, Calendar.SEPTEMBER);
            calendar.set(Calendar.DAY_OF_MONTH, 3);
            RentalApplication rentalApplication = new RentalApplication(new RentalAgreementBuilder());
            rentalApplication.checkout(3, calendar.getTime(), 50);
            fail("Did not get expected exception.");
        } catch (RentalApplicationException e) {
            assertEquals("A tool must be selected.", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Unexpected exception: " + e.getMessage());
        }
    }

}
