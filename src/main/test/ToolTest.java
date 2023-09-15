package main.java.test;

import main.java.Chainsaw;
import main.java.Jackhammer;
import main.java.Ladder;
import main.java.Tool;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class ToolTest {
    public static void main(String[] args) throws Exception {
        // Each method contains a comment clarifying the dates & expected outcome.
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
        test7();
    }

    @Test
    public static void test1() throws Exception {
        // Exception should be thrown because the discount is greater than 100%
        boolean exceptionThrown = false;
        try {
            Tool jackhammer = new Jackhammer(5, 101, 2015, Calendar.SEPTEMBER, 3);
        } catch (Exception e) {
            System.out.println("Unit test: Exception thrown as expected. \n");
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    @Test
    public static void test2() throws Exception {
        // Thursday, July 2nd - Monday, July 5th (End exclusive)
        // Tool has weekend charge but no holiday charge
        // 2 charge days-> Thursday & Friday are billable. Saturday is not.
        Tool ladder = new Ladder(3, 10, 2020, Calendar.JULY, 2);
        assertEquals(ladder.getRentalAgreement().getFinalCharge(), ladder.getDailyCharge() * 0.9 * 2, 0.0);
    }

    @Test
    public static void test3() throws Exception {
        // Thursday, July 2nd - Tuesday, July 7th (End exclusive)
        // Tool has holiday charge but no weekend charge.
        // 3 charge days: Thursday, Friday, Monday
        Tool chainsaw = new Chainsaw(5, 25, 2015, Calendar.JULY, 2);
        assertEquals(chainsaw.getRentalAgreement().getFinalCharge(), chainsaw.getDailyCharge() * .75 * 3, 0.0);
    }
    @Test
    public static void test4() throws Exception {
        // Thursday, September 3rd - Wednesday, September 9th (end exclusive)
        // Tool has no weekend or holiday charges.
        // For this date range the Monday is a holiday.
        // 3 charge days: Thursday, Friday, Tuesday
        Tool jackhammer = new Jackhammer(6, 0, 2015, Calendar.SEPTEMBER, 3, Tool.BRANDS.DeWalt.name());
        assertEquals(jackhammer.getRentalAgreement().getFinalCharge(), jackhammer.getDailyCharge() * 3, 0.0);
    }

    @Test
    public static void test5() throws Exception {
        // Thursday, July 2rd - Saturday, July 11th (end exclusive)
        // Tool has no weekend or holiday charges.
        // July 3rd works as a holiday (no charge) because July 4th is a Saturday
        // 6 charge days: Thursday, Monday-Friday
        Tool jackhammer = new Jackhammer(9, 0, 2015, Calendar.JULY, 2, Tool.BRANDS.Ridgid.name());
        assertEquals(jackhammer.getRentalAgreement().getFinalCharge(), jackhammer.getDailyCharge() * 6, 0.0);
    }

    @Test
    public static void test6() throws Exception {
        // Thursday, July 2nd - Monday, July 6th (end exclusive)
        // Tool has no weekend or holiday charges.
        // 1 charge day: Thursday
        Tool jackhammer = new Jackhammer(4, 50, 2020, Calendar.JULY, 2, Tool.BRANDS.Ridgid.name());
        assertEquals(jackhammer.getRentalAgreement().getFinalCharge(), jackhammer.getDailyCharge() * 0.5, 0.0);
    }

    @Test
    public static void test7() throws Exception {
        // Exception should be thrown because the discount is less than 0%
        boolean exceptionThrown = false;
        try {
            Tool jackhammer = new Jackhammer(5, -1, 2015, Calendar.SEPTEMBER, 3);
        } catch (Exception e) {
            System.out.println("Unit test: Exception thrown as expected.");
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }
}