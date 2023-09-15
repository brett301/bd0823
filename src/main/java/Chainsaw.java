package main.java;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class Chainsaw extends Tool {
	private static final double dailyRate = 1.49;

	private LocalDate date;


	public Chainsaw() throws Exception {
		// Default to January 1st, 2023 with 10 rental days and 0% discount
		this(10, 0, 2023, Calendar.JANUARY, 1);
	}

	public Chainsaw(int rentalDays, int discount, int year, int month, int day) throws Exception {
		setToolCode(TOOL_CODES.CHNS.name());
		setToolType(TOOL_TYPES.chainsaw.name());
		setBrand(BRANDS.Stihl.name());
		setDailyCharge(dailyRate);
		setWeekdayCharge(true);
		setWeekendCharge(false);
		setHolidayCharge(true);
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);
		Date startDate = calendar.getTime();
		checkout(this, rentalDays, discount, startDate);
	}
}