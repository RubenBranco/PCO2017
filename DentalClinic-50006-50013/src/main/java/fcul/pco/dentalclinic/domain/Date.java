package fcul.pco.dentalclinic.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements an appointment
 *
 * @author João Regueira, Ruben Branco
 */

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

	public static void main(String[] args) {
		Date test = new Date(9,0,2,12,2017);
		System.out.println(test.dayOfWeek());
	}

	/**
	 * Public constructor for date
	 *
	 * @param hour is an int
	 * @param minute is an int
	 * @param day is an int
	 * @param month is an int
	 * @param year is an int
	 */
	public Date(int hour, int minute, int day, int month, int year) {
		this.hour = hour;
		this.minute = minute;
		this.day = day;
		this.month = month;
		this.year = year;
	}

	/**
	 * Private constructor for yyyy/mm/dd
	 *
	 * @param year is an int
	 * @param month is an int
	 * @param day is an int
	 */
	private Date(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}

	/**
	 * Private constructor for mm/dd
	 *
	 * @param month is an int
	 * @param day is an int
	 */
	private Date(int month, int day) {
		this.month = month;
		this.day = day;
	}

	/**
	 * Checks if the instance is the same date as the other
	 *
	 * @param other is a date object
	 * @return Boolean(true or false)
	 */
	private boolean sameDay(Date other) {
		return other.day == day && other.month == month;
	}

	/**
	 * Checks if the instance is before other.
	 *
	 * @param other is a date object
	 * @return Boolean(true or false)
	 */
	public boolean isBefore(Date other) {
		return this.year <= other.year && this.month <= other.month && this.day < other.day;
	}

	/**
	 * Checks if the instance is an holiday.
	 *
	 * @return Boolean(true or false)
	 */
	public boolean isHoliday() {
		for (int i = 0; i < HOLIDAYS.length; i++) {
			if (this.sameDay(HOLIDAYS[i])) return true;
		}
		return false;
	}

	/**
	 * Checks if year is a leap year
	 *
	 * @param year is an int
	 * @return Boolean(true or false)
	 */
	private static boolean isLeapYear(int year) {
		return year % 400 == 0 || (year % 4 == 0) && (year % 100 != 0);
	}

	/**
	 * Checks how many days there is in the month
	 *
	 * @param month is an int
	 * @param year is an int
	 * @return an int being the number of days
	 */
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

	/**
	 * Checks how many days there is in the given year
	 *
	 * @param year is an int
	 * @return an int, being the number of days
	 */
	private static int daysInYear(int year) {
		if (Date.isLeapYear(year)) return 366;
		return 365;
	}

	/**
	 * Calculates how many minutes have passed since a start date
	 *
	 * @return an int being the number of minutes since startdate
	 */
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

	/**
	 * How many days have passed since start date
	 *
	 * @return an int
	 */
	private int daysSinceStartDate() {
		return (int) Math.round(this.intValue() / 1440.0);
	}

	/**
	 * Calculates the day of the week, from 0 to 6.
	 *
	 * @return an int
	 */
	public int dayOfWeek() {
		System.out.println(this.daysSinceStartDate());
		System.out.println(this.intValue() / 1440.0);
		return (5 + this.daysSinceStartDate()) % 7;
	}

	/**
	 *
	 *
	 * @return
	 */
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

	public int minutesSinceStartDate(){
		return this.intValue();
	}

	@Override
	public String toString() {
		return String.format("%s/%s/%s@%d:%d", year, month, day, hour, minute);
	}

	public static Date incrementDate(Date d, int minutes) {
		int minute = d.getMinute();
		int hour = d.getHour();
		int day = d.getDay();
		int month = d.getMonth();
		int year = d.getYear();
		while (minutes > 0) {
			if (minute + minutes > 59) {
				minutes -= (60 - minute);
				minute = 0;
				hour++;
				if (hour > 23) {
					hour = 0;
					if (day + 1 > Date.daysInMonth(month, year)) {
						day = 1;
						month++;
						if (month > 12) {
							month = 1;
							year++;
						}
					}
					else {
						day++;
					}
				}
			} else {
				minute += minutes;
				minutes = 0;
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
					&& currentDate.dayOfWeek() != 6 && currentDate.getHour() > 8 && currentDate.getHour() < 18 &&
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

	public static String dayOfWeekDesignation(int i) {
		List<String> dayOfWeek = new ArrayList<>();
		dayOfWeek.add("Segunda-Feira");
		dayOfWeek.add("Terça-Feira");
		dayOfWeek.add("Quarta-Feira");
		dayOfWeek.add("Quinta-Feira");
		dayOfWeek.add("Sexta-Feira");
		dayOfWeek.add("Sábado");
		dayOfWeek.add("Domingo");
		return dayOfWeek.get(i);
	}
}
