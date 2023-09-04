public class Ladder extends tool {
	public Ladder() {
		setToolCode(TOOL_CODES.LADW.name());
		setToolType(TOOL_TYPES.ladder.name());
		setBrand(BRANDS.Werner.name());
		setDailyCharge(1.99);
		setWeekdayCharge(true);
		setWeekendCharge(true);
		setHolidayCharge(false);
	}
}