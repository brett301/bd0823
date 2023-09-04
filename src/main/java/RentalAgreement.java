import java.util.logging.Logger;
import java.util.Date;
import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.*;
import java.util.stream.Stream;
import java.util.Calendar;
import java.time.DayOfWeek;
import java.util.stream.*;


public class RentalAgreement {

	private final static Logger log = Logger.getLogger(RentalAgreement.class.getName());

	// private String toolBrand;
	// private String toolCode;
	// private String toolType;
	// private double dailyCharge;

	// private Date checkoutDate;
	private int discountPercentage;  // whole number in range [0, 100]
	private int rentalDays;


	// July 4th - or nearest weekday
	// Labor Day - first monday in september
	private static Optional<List<LocalDate>> holidays = Optional.of(new ArrayList<LocalDate>());

	private int chargeDays; // rental days - nonbillable days
	private double discountAmount = 0.00;
	private Date dueDate;
	private double finalCharge = 0.00;
	private double preDiscountCharge;
	

	/*
		Stored in passed in Tool object
			● Tool code - Specified at checkout
			● Tool type - From tool info
			● Tool brand - From tool info
			● Daily rental charge - Amount per day, specified by the tool type.

		Passed in with other "checkout" variables
			● Rental days - Specified at checkout
			● Check out date - Specified at checkout
			● Discount percent - Specified at checkout.
		Calculated
			● Due date - Calculated from checkout date and rental days.
			● Charge days - Count of chargeable days, from day after checkout through and including due
				date, excluding “no charge” days as specified by the tool type.
			● Pre-discount charge - Calculated as charge days X daily charge. Resulting total rounded half up
				to cents.
			● Discount amount - calculated from discount % and pre-discount charge. Resulting amount
				rounded half up to cents.
			● Final charge - Calculated as pre-discount charge - discount amount.
	*/

	public RentalAgreement(tool tool, int rentalDays, int discount, Date checkoutDate) {
		finalCharge = checkout(tool, rentalDays, discount, checkoutDate);
	}

	public double checkout(tool tool, int rentalDays, int discount, Date date) {
		if (rentalDays < 1) {
			log.warning("ERROR: number of rental days is {}. "
				+ "Please provide a number of rental days of at least 1");
		} else if (discount < 0 || discount > 100) {
			log.warning("ERROR: discount percentage is {}. "
				+ "Please provide a discount percent that is at least 0 and at most 100.");
		}

		rentalDays = rentalDays;

		discountPercentage = discount;
		SimpleDateFormat dateFormatter1 = new SimpleDateFormat("YYYY-mm-dd");
		String startDateString = dateFormatter1.format(date);

		preDiscountCharge = rentalDays * tool.getDailyCharge();

		// End date is exclusive
		// Ex):	Start = 08/23/2023, rental days = 3
		//		Rental days are : 8/23, 8/24, 8/25
		//		End date is: 8/26
		LocalDate startDate = LocalDate.parse(startDateString);
		LocalDate endDate = startDate.plusDays(rentalDays);

		if (tool.hasHolidayCharge()) {
			determineHolidays(startDate.getYear());
		}

		chargeDays = determineChargeDays(tool, startDate, endDate, holidays);
		finalCharge = tool.getDailyCharge() * chargeDays * (discount/100);
		displayRentalAgreement(tool);
		return finalCharge;
	}

	private int determineChargeDays(tool tool, LocalDate startDate,
        LocalDate endDate,
        Optional<List<LocalDate>> holidays) {
		// if (tool.hasWeekdayCharge() && tool.hasWeekendCharge()) {
		// 	return rentalDays;
		// } else if (tool.hasWeekendCharge()) {
		// 	return 2;
		// } else if (tool.hasWeekdayCharge()) {
		// 	return 10;
		// } else {
		// 	return 0;
		// }


	    // Validate method arguments
	    if (startDate == null || endDate == null) {
	        throw new IllegalArgumentException("Invalid method argument(s) " +
	            "to countBusinessDaysBetween (" + startDate + "," + endDate + "," + holidays + ")");
	    }

	    // Predicate 1: If a given date is a holiday
	    Predicate<LocalDate> isHoliday = date -> holidays.isPresent()
	                && holidays.get().contains(date);

	    // Predicate 2: If a given date is a weekday
	    Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY
            || date.getDayOfWeek() == DayOfWeek.SUNDAY;
	            // || date.getDayOfWeek() == Calendar.DAY_OF_WEEK.SUNDAY;

	    // Iterate over stream of all dates and check each day against any weekday or holiday
	    List<LocalDate> businessDays = startDate.datesUntil(endDate)
	            .filter(isWeekend.or(isHoliday).negate())
	            .collect(Collectors.toList());

	    return businessDays.size();

	}

	private void displayRentalAgreement(tool tool) {
		log.info("RENTAL AGREEMENT");
		log.info("Tool Code: " + tool.getToolCode());
		log.info("Tool Type: " + tool.getToolType());
		log.info("Tool Brand: " + tool.getBrand());
		log.info("Percent " + String.valueOf(100 - discountPercentage) + "%");
		log.info("Final Charge Amount: " + finalCharge);
	}

	private void determineHolidays(int year) {
		holidays.get().add(LocalDate.of(year, 7, 4));

	}
}