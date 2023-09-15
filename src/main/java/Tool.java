package main.java;

import java.util.Calendar;
import java.util.Date;

public class Tool {
	// Base class for Chainsaw, Ladder, Jackhammer

	public enum TOOL_CODES { CHNS, LADW, JAKD, JAKR };
	public enum TOOL_TYPES { chainsaw, ladder, jackhammer };
	public enum BRANDS { Stihl, Werner, DeWalt, Ridgid };

	private String toolCode;
	private String toolType;
	private String brand;
	private double dailyCharge;
	private static RentalAgreement rentalAgreement;
	private boolean hasHolidayCharge;
	private boolean hasWeekdayCharge;
	private boolean hasWeekendCharge;

	public RentalAgreement getRentalAgreement() {
		return rentalAgreement;
	}

	public void setRentalAgreement(RentalAgreement ra) {
		rentalAgreement = ra;
	}

	public void setToolCode(String str) {
		toolCode = str;
	}

	public String getToolCode() {
		return toolCode;
	}

	public void setToolType(String str) {
		toolType = str;
	}

	public String getToolType() {
		return toolType;
	}

	public void setBrand(String str) {
		brand = str;
	}

	public String getBrand() {
		return brand;
	}

	public void setDailyCharge(double charge) {
		dailyCharge = charge;
	}

	public double getDailyCharge() {
		return dailyCharge;
	}

	public void setWeekdayCharge(boolean weekdayCharge) {
		hasWeekdayCharge = weekdayCharge;
	}

	public boolean hasWeekdayCharge() {
		return hasWeekdayCharge;
	}

	public void setWeekendCharge(boolean weekendCharge) {
		hasWeekendCharge = weekendCharge;
	}

	public boolean hasWeekendCharge() {
		return hasWeekendCharge;
	}

	public void setHolidayCharge(boolean holidayCharge) {
		hasHolidayCharge = holidayCharge;
	}

	public boolean hasHolidayCharge() {
		return hasHolidayCharge;
	}
}