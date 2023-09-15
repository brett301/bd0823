package main.java;

import java.util.Calendar;
import java.util.Date;

public class Jackhammer extends Tool {

	private static final double dailyRate = 2.99;
	public Jackhammer() throws Exception {
		// Default to JAKD, DeWalt on January 1st, 2023 with 10 rental days and 0% discount
		this(10, 0, 2023, Calendar.JANUARY, 1, BRANDS.DeWalt.name());
	}

	public Jackhammer(int rentalDays, int discount, int year, int month, int day) throws Exception {
		// If no brand & tool code was specified, default to JAKD and DeWalt.
		this(rentalDays, discount, year, month, day, BRANDS.DeWalt.name());
	}

	public Jackhammer(int rentalDays, int discount, int year, int month, int day,
					  String brand) throws Exception {
		if (brand.equalsIgnoreCase(BRANDS.DeWalt.name())) {
			setToolCode(TOOL_CODES.JAKD.name());
		} else {
			setToolCode(TOOL_CODES.JAKR.name());
		}
		setToolType(TOOL_TYPES.jackhammer.name());
		setBrand(brand);
		setDailyCharge(dailyRate);
		setWeekdayCharge(true);
		setWeekendCharge(false);
		setHolidayCharge(false);
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);
		Date startDate = calendar.getTime();
		RentalAgreement ra = new RentalAgreement(this, rentalDays, discount, startDate);
		setRentalAgreement(ra);
	}
}