package main.java;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class ToolTest {

    public static void main(String[] args) throws Exception {
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
    }

    @Test
    public static void test1() throws Exception {
        boolean exceptionThrown = false;
        try {
            Tool jackhammer = new Jackhammer(5, 101, 2015, Calendar.SEPTEMBER, 3);
        } catch (Exception e) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    @Test
    public static void test2() throws Exception {
        Tool ladder = new Ladder(3, 10, 2020, Calendar.JULY, 2);
        assertEquals(ladder.getFinalCost(), ladder.getDailyCharge() * 0.9 * 2, 0.0);
    }

    @Test
    public static void test3() throws Exception {
        Tool chainsaw = new Chainsaw(5, 25, 2015, Calendar.JULY, 2);
        assertEquals(chainsaw.getFinalCost(), chainsaw.getDailyCharge() * .75 * 3, 0.0);
    }
    @Test
    public static void test4() throws Exception {
        Tool jackhammer = new Jackhammer(6, 0, 2015, Calendar.SEPTEMBER, 3,
                Tool.TOOL_CODES.JAKD.name(), Tool.BRANDS.DeWalt.name());
        assertEquals(jackhammer.getFinalCost(), jackhammer.getDailyCharge() * 3, 0.0);
    }

    @Test
    public static void test5() throws Exception {
        // No charge on July 3rd because July 4th is a Saturday
        // This test should only have 5 charge days
        Tool jackhammer = new Jackhammer(9, 0, 2015, Calendar.JULY, 2,
                Tool.TOOL_CODES.JAKR.name(), Tool.BRANDS.Ridgid.name());
        assertEquals(jackhammer.getFinalCost(), jackhammer.getDailyCharge() * 6, 0.0);
    }

    @Test
    public static void test6() throws Exception {
        Tool jackhammer = new Jackhammer(4, 50, 2020, Calendar.JULY, 2,
                Tool.TOOL_CODES.JAKR.name(), Tool.BRANDS.Ridgid.name());
        assertEquals(jackhammer.getFinalCost(), jackhammer.getDailyCharge() * 0.5, 0.0);

    }

}