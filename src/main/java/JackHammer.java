public class JackHammer extends tool {
	public JackHammer() {
		// default to JAKD, DeWalt when no info passed in
		this(TOOL_CODES.JAKD.name(), BRANDS.DeWalt.name());
	}

	public JackHammer(String toolCode, String brand) {
		setToolCode(toolCode);
		setToolType(TOOL_TYPES.JackHammer.name());
		setBrand(brand);
		setDailyCharge(2.99);
		setWeekdayCharge(true);
		setWeekendCharge(false);
		setHolidayCharge(false);
	}
}