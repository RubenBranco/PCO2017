package fcul.pco.dentalclinic.domain;

public class Date {
	private int hour;
	private int minute;
	private int day;
	private int month;
	private int year;
	private static final Date[] HOLIDAYS = {
			new Date(1, 1),
			new Date(4, 25),
			new Date(5, 1),
			new Date(6, 10),
			new Date(6, 15),
			new Date(8, 15),
			new Date(10, 5),
			new Date(11, 1),
			new Date(12, 1),
			new Date(12, 8),
			new Date(12, 25)
	};
	private static final Date STARTDATE = new Date(2000, 1, 1);
	private static final int STARTDATEINT = STARTDATE.intValue();
	
	public Date(int hour, int minute, int day, int month, int year) {
		this.hour = hour;
		this.minute = minute;
		this.day = day;
		this.month = month;
		this.year = year;
	}
	private Date(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	private Date(int month, int day) {
		this.month = month;
		this.day = day;
	}
	
	private boolean sameDay(Date other) {
		return other.day == day && other.month == month;
	}
	
	public boolean isBefore(Date other) {
		return this.year <= other.year && this.month <= other.month && this.day < other.day;
	}

	public boolean isHoliday() {
		for (int i = 0; i < HOLIDAYS.length; i++) {
			if (this.sameDay(HOLIDAYS[i])) return true;
		}
		return false;
	}
	
	private static boolean isLeapYear(int year) {
		if(year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) return true;
		return false;
	}
	
	public static int daysInMonth(int month, int year) {
		if (month == 2) {
			if (Date.isLeapYear(year)) {
				return 28;
			} else {
				return 27;
			} 
		}
		else if (month < 8 && month % 2 == 0) {
			return 30;
		}
		else if (month < 8 && month % 2 != 0) {
			return 31;
		}
		else if (month > 8 && month % 2 == 0) {
			return 31;
		}
		else if (month > 8 && month % 2 != 0) {
			return 30;
		}
		else {
			return 31;
		}
	}
	
	private static int daysInYear(int year) {
		if (Date.isLeapYear(year)) return 365;
		return 364;
	}
	
	private int intValue() {
		int value = minute;
		value += hour * 60;
		value += (day - 1) * 1440;
		for (int m = 1; m < month; m++) {
			value += daysInMonth(m, year) * 1440;
		}
		for (int y = STARTDATE.year; y < year; y++) {
			value += daysInYear(y) * 1440;
		}
			return value - STARTDATEINT;
	}

	private int daysSinceStartDate() {
		return this.intValue();
	}
	
	public int dayOfWeek() {
		return (5 + this.daysSinceStartDate()) % 7;
	}
	
	public int getMinute() {
		return minute;
	}
	
	public int getHour() {
		return hour;
	}
	
	public int getDay() {
		return day;
	}
	
	public int getMonth() {
		return month;
	}
	
	public int getYear() {
		return year;
	}
}
