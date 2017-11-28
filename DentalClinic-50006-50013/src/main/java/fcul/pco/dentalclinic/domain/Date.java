package fcul.pco.dentalclinic.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
	
	public int intValue() {
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
		return (5 + this.daysSinceStartDate() / 1440) % 7;
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

	@Override
	public String toString() {
		return String.format("%s/%s/%s@%d:%d", year, month, day, hour, minute);
	}

	public static Date incrementDate(Date d, int minutes) {
		int minute = (d.getMinute() + minutes) % 60;
		int hoursElapsed = (d.getMinute() + minutes) / 60;
		int hour = (d.getHour() + hoursElapsed) % 24;
		int daysElapsed = (d.getHour() + hoursElapsed) / 24;
		int day = d.getDay();
		int month = d.getMonth();
		int year = d.getYear();
		while (daysElapsed > 0) {
			if (daysElapsed + day > Date.daysInMonth(month, year)) {
				if (month + 1 > 12) {
					month = 1;
					year++;
				}
				else {
					month++;
				}
				daysElapsed -= day;
			}
			else {
				day += daysElapsed;
			}
		}
		return new Date(hour, minute, day, month, year);
	}

	public static Date getCurrentDate() {
		LocalDateTime now = LocalDateTime.now();
		return new Date(now.getHour(), now.getMinute(), now.getDayOfMonth(), now.getMonthValue(), now.getYear());
	}

	public List<Date> makeSmartDateList(int every, List<Date> exclude) {
		List<Date> smartDateList = new ArrayList<>();
		smartDateList.add(this);
		Date currentDate = this;
		while (smartDateList.size() < 10) {
			currentDate = Date.incrementDate(currentDate, every);
			if (!exclude.contains(currentDate) && !currentDate.isHoliday() && currentDate.dayOfWeek() != 5
					&& currentDate.dayOfWeek() != 6 && currentDate.getHour() > 8 && currentDate.getHour() < 19 &&
					currentDate.getHour() != 12 && currentDate.getHour() != 13) {
				smartDateList.add(currentDate);
			}
		}
		return smartDateList;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Date)) return false;

		Date date = (Date) o;

		if (getHour() != date.getHour()) return false;
		if (getMinute() != date.getMinute()) return false;
		if (getDay() != date.getDay()) return false;
		if (getMonth() != date.getMonth()) return false;
		return getYear() == date.getYear();
	}
	@Override
	public int hashCode() {
		int result = getHour();
		result = 31 * result + getMinute();
		result = 31 * result + getDay();
		result = 31 * result + getMonth();
		result = 31 * result + getYear();
		return result;
	}

	public static Date getTomorrowMorning() {
		Date currentDate = Date.getCurrentDate();
		return new Date(9, 0, currentDate.getDay() + 1, currentDate.getMonth(), currentDate.getYear());
	}

	public static Date fromString(String s){
		String[] dmYear = s.split("/");
		String[] hourMinute = s.split("@");
		return new Date(Integer.parseInt(hourMinute[1].split(":")[0]),
				Integer.parseInt(hourMinute[1].split(":")[1]), Integer.parseInt(dmYear[2].split("@")[0])
				, Integer.parseInt(dmYear[1]), Integer.parseInt(dmYear[0]));
	}

	public static String dateListToString(List<Date> list) {
		List<List<String>> dateList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			List<String> oneDateList = new ArrayList<>();
			oneDateList.add(String.format("%d", i+1));
			oneDateList.add(list.get(i).toString());
			dateList.add(oneDateList);
		}
		return Utils.tableToString(dateList);
	}
}
