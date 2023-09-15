package main.java;
import java.util.logging.Logger;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.*;
import static java.time.temporal.TemporalAdjusters.firstInMonth;
import java.time.DayOfWeek;


public class RentalAgreement {
	private final static Logger log = Logger.getLogger(RentalAgreement.class.getName());
	private int discountPercent;  // whole number in range [0, 100]
	private static Optional<List<LocalDate>> holidays = Optional.of(new ArrayList<LocalDate>());
	private int chargeDays; // rentalDays - nonBillableDays
	private double discountAmount = 0.00;
	private LocalDate endDate;
	private LocalDate startDate;
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

	public RentalAgreement(Tool tool, int rentalDays, int discount, Date checkoutDate) throws Exception {
		finalCharge = checkout(tool, rentalDays, discount, checkoutDate);
		System.out.print("RENTAL AGREEMENT " + "\n"
		+ "TOOL CODE:" + tool.getToolCode() + "\n"
		+ "TOOL TYPE:" + tool.getToolType() + "\n"
		+ "TOOL BRAND:" + tool.getBrand() + "\n"
		+ "RENTAL DAYS:" + rentalDays + "\n"
		+ "CHECKOUT DATE:" + startDate + "\n"
		+ "DUE DATE:" + endDate + "\n"
		+ "DAILY RENTAL CHARGE:" + tool.getDailyCharge() + "\n"
		+ "CHARGE DAYS:" + chargeDays + "\n"
		+ "PRE DISCOUNT CHARGE:" + preDiscountCharge + "\n"
		+ "DISCOUNT PERCENT:" + discountPercent + "\n"
		+ "DISCOUNT AMOUNT:" + discountAmount + "\n"
		+ "FINAL CHARGE:" + finalCharge + "\n\n");
	}

	public double checkout(Tool tool, int rentalDays, int discountPercentage, Date date) throws Exception {
		if (rentalDays < 1) {
			log.warning("ERROR: number of rental days is {}. "
				+ "Please provide a number of rental days of at least 1");
			throw new Exception();
		} else if (discountPercentage < 0 || discountPercentage > 100) {
			log.warning("ERROR: discount percentage is {}. "
					+ "Please provide a discount percent that is at least 0 and at most 100.");
			throw new Exception();
		}

		SimpleDateFormat dateFormatter1 = new SimpleDateFormat("yyyy-MM-dd");
		String startDateString = dateFormatter1.format(date);
		startDate = LocalDate.parse(startDateString);

		// End date is exclusive
		// Ex):	Start = 08/23/2023, rental days = 3, Rental days are : 8/23, 8/24, 8/25, End date is: 8/26
		endDate = startDate.plusDays(rentalDays);

		determineHolidays(startDate.getYear());

		chargeDays = determineChargeDays(tool, startDate, endDate, holidays);
		preDiscountCharge = chargeDays * tool.getDailyCharge();
		discountPercent = discountPercentage;
		finalCharge = tool.getDailyCharge() * chargeDays * ((double) (100 - discountPercentage) / 100);
		discountAmount = tool.getDailyCharge() * chargeDays - finalCharge;
		return finalCharge;
	}
	private int determineChargeDays(Tool tool, LocalDate startDate,
									LocalDate endDate,
									Optional<List<LocalDate>> holidays) {
	    // Validate method arguments
	    if (startDate == null || endDate == null) {
	        throw new IllegalArgumentException("Invalid method argument(s) " +
	            "to countBusinessDaysBetween (" + startDate + "," + endDate + "," + holidays + ")");
	    }

	    // If a given date is a holiday
	    Predicate<LocalDate> isHoliday = date -> holidays.isPresent()
	                && holidays.get().contains(date);

	    // If a given date is a weekday
	    Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY
            || date.getDayOfWeek() == DayOfWeek.SUNDAY;

		List<LocalDate> businessDays = new ArrayList<>();
		// First check if tool has weekend and holiday charge.
		// Then filter out the dates that will not be charged.
	    if (!tool.hasWeekendCharge() && !tool.hasHolidayCharge()) {
			businessDays = startDate.datesUntil(endDate)
					.filter(isWeekend.or(isHoliday).negate())
					.collect(Collectors.toList());
		} else if (!tool.hasWeekendCharge()) {
			businessDays = startDate.datesUntil(endDate)
					.filter(isWeekend.negate())
					.collect(Collectors.toList());
		} else if (!tool.hasHolidayCharge()) {
			businessDays = startDate.datesUntil(endDate)
					.filter(isHoliday.negate())
					.collect(Collectors.toList());
		}
	    return businessDays.size();
	}

	private void determineHolidays(int year) {
		LocalDate julyFourth = LocalDate.of(year, 7, 4);

		// If July Fourth is on the weekend, the closest weekday is considered the holiday.
		if (holidays.isPresent()) {
			if (julyFourth.getDayOfWeek() == DayOfWeek.SUNDAY) {
				holidays.get().add(LocalDate.of(year, 7, 5));
			} else if (julyFourth.getDayOfWeek() == DayOfWeek.SATURDAY) {
				holidays.get().add(LocalDate.of(year, 7, 3));
			} else {
				holidays.get().add(LocalDate.of(year, 7, 4));
			}
		}

		// First Monday in September is also a holiday.
		LocalDate septemberFirst = LocalDate.of(year, 9, 1);
		LocalDate firstMondayInSeptember = septemberFirst.with(firstInMonth(DayOfWeek.MONDAY));
		holidays.get().add(firstMondayInSeptember);
	}

	public double getFinalCharge() {
		return finalCharge;
	}
}