package main.java;

import java.util.Calendar;
import java.util.Date;

public class Jackhammer extends Tool {

	private static final double dailyRate = 2.99;
	public Jackhammer() throws Exception {
		// Default to JAKD, DeWalt on January 1st, 2023 with 10 rental days and 0% discount
		this(10, 0, 2023, Calendar.JANUARY, 1, TOOL_CODES.JAKD.name(), BRANDS.DeWalt.name());
	}

	public Jackhammer(int rentalDays, int discount, int year, int month, int day) throws Exception {
		// If no brand & tool code was specified, default to JAKD and DeWalt.
		this(rentalDays, discount, year, month, day, TOOL_CODES.JAKD.name(), BRANDS.DeWalt.name());
	}

	public Jackhammer(int rentalDays, int discount, int year, int month, int day,
					  String toolCode, String brand) throws Exception {
		if ((toolCode.equalsIgnoreCase(TOOL_CODES.JAKD.name()) && !brand.equalsIgnoreCase(BRANDS.DeWalt.name()))
			|| (toolCode.equalsIgnoreCase(TOOL_CODES.JAKR.name()) && !brand.equalsIgnoreCase(BRANDS.Ridgid.name()))) {
			System.out.println("Error: Jack hammer must have tool code JAKD with brand DeWalt or " +
					"tool code JAKR with brand Ridgid");
			return;
		}
		setToolCode(toolCode);
		setToolType(TOOL_TYPES.jackhammer.name());
		setBrand(brand);
		setDailyCharge(dailyRate);
		setWeekdayCharge(true);
		setWeekendCharge(false);
		setHolidayCharge(false);
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);
		Date startDate = calendar.getTime();
		checkout(this, rentalDays, discount, startDate);
	}
}