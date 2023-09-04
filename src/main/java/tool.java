import java.util.Date;

public class tool {
	// Base class for Chainsaw, Ladder, Jackhammer

	public enum TOOL_CODES { CHNS, LADW, JAKD, JAKR };
	public enum TOOL_TYPES { chainsaw, ladder,jackhammer };
	public enum BRANDS { Stihl, Werner, DeWalt, Ridgid };

	private String toolCode;
	private String toolType;
	private String brand;
	private double dailyCharge;

	private boolean hasHolidayCharge;
	private boolean hasWeekdayCharge;
	private boolean hasWeekendCharge;

	public static void main(String[] args) {
		tool chainsaw1 = new Chainsaw();
		System.out.println(chainsaw1.getBrand());
	}

	public void checkout(String toolCode, int rentalDays, int discount, Date checkoutDate) {
		RentalAgreement ra = new RentalAgreement(this, rentalDays, discount, checkoutDate);
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
		return toolCode;
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

	public void setWeekdayCharge(boolean hasWeekdayCharge) {
		hasWeekdayCharge = hasWeekdayCharge;
	}

	public boolean hasWeekdayCharge() {
		return hasWeekdayCharge;
	}

	public void setWeekendCharge(boolean hasWeekendCharge) {
		hasWeekendCharge = hasWeekendCharge;
	}

	public boolean hasWeekendCharge() {
		return hasWeekendCharge;
	}

	public void setHolidayCharge(boolean hasHolidayCharge) {
		hasHolidayCharge = hasHolidayCharge;
	}

	public boolean hasHolidayCharge() {
		return hasHolidayCharge;
	}
	
}