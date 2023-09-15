package main.java;

import main.java.Chainsaw;
import main.java.Jackhammer;
import main.java.Ladder;
import main.java.Tool;

import java.util.Calendar;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws Exception {

        // No arg examples
        Tool chainsaw1 = new Chainsaw();
        Tool jackhammer1 = new Jackhammer();
        Tool ladder1 = new Ladder();

        // Examples with date & rental day parameters
        Tool chainsaw2 = new Chainsaw(4, 0, 2023, Calendar.FEBRUARY, 9);
        Tool jackhammer2 = new Jackhammer(4, 0, 2023, Calendar.FEBRUARY, 9);
        Tool ladder2 = new Ladder(4, 0, 2023, Calendar.FEBRUARY, 9);

        // Example for Jackhammer with brand parameter
        Tool jackhammer3 = new Jackhammer(4, 0, 2023, Calendar.FEBRUARY, 9,
                Tool.BRANDS.Ridgid.name());

    }
}