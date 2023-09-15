package main.java;

import java.util.Calendar;
import java.util.Date;

public class Ladder extends Tool {
	private static final double dailyRate = 1.99;

	public Ladder() throws Exception {
		// Default to January 1st, 2023 with 10 rental days and 0% discount
		this(10, 0, 2023, Calendar.JANUARY, 1);
	}
	public Ladder(int rentalDays, int discount, int year, int month, int day) throws Exception {
		setToolCode(TOOL_CODES.LADW.name());
		setToolType(TOOL_TYPES.ladder.name());
		setBrand(BRANDS.Werner.name());
		setDailyCharge(dailyRate);
		setWeekdayCharge(true);
		setWeekendCharge(true);
		setHolidayCharge(false);
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);
		Date startDate = calendar.getTime();
		RentalAgreement ra = new RentalAgreement(this, rentalDays, discount, startDate);
		setRentalAgreement(ra);
	}
}