public class Chainsaw extends tool {

	private static final double dailyCharge = 1.49;

	public Chainsaw() {
		setToolCode(TOOL_CODES.CHNS.name());
		setToolType(TOOL_TYPES.chainsaw.name());
		setBrand(BRANDS.Stihl.name());
		setDailyCharge(dailyCharge);
		setWeekdayCharge(true);
		setWeekendCharge(false);
		setHolidayCharge(true);
	}
}