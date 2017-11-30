package fcul.pco.dentalclinic.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements an appointment
 *
 * @author João Regueira, Ruben Branco
 */

public class Date implements Comparable<Date>{
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
		return this.intValue() - other.intValue() < 0;
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
		int[] thirtyOne = {1, 3, 5, 7, 8, 10, 12};
		if (month == 2) {
			if (Date.isLeapYear(year)) {
				return 29;
			} else {
				return 28;
			} 
		} else {
			for (int m : thirtyOne) {
				if (m == month) {
					return 31;
				}
			}
			return 30;
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
		Date cacheDate = new Date(0, 0, this.getDay(), this.getMonth(), this.getYear());
		return cacheDate.intValue() / 1440;
	}

	/**
	 * Calculates the day of the week, from 0 to 6.
	 *
	 * @return an int
	 */
	public int dayOfWeek() {
		return (5 + this.daysSinceStartDate()) % 7;
	}

	/**
	 * Gets the minutes in the Date instance
	 *
	 * @return an int
	 */
	public int getMinute() {
		return minute;
	}

	/**
	 * Gets the hour in the Date instance
	 *
	 * @return an int
	 */
	public int getHour() {
		return hour;
	}

	/**
	 * Gets the day in the Date instance
	 *
	 * @return an int
	 */
	public int getDay() {
		return day;
	}

	/**
	 * Gets the month in the Date instance
	 *
	 * @return an int
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * Gets the year in the Date instance
	 *
	 * @return an int
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Turns the Date instance into a string
	 *
	 * @return a string
	 */
	@Override
	public String toString() {
		return String.format("%s/%s/%s@%d:%d", year, month, day, hour, minute);
	}

	/**
	 * Increments a date object by n minutes
	 *
	 * @param d is date object
	 * @param minutes is an int
	 * @return a Date object
	 */
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

	/**
	 * Returns a Date object of the current date
	 *
	 * @return a Date object
	 */
	public static Date getCurrentDate() {
		LocalDateTime now = LocalDateTime.now();
		return new Date(now.getHour(), now.getMinute(), now.getDayOfMonth(), now.getMonthValue(), now.getYear());
	}

	/**
	 * Returns a list of 10 Date objects with an interval of every minutes, if the generated Date is not in exclude
	 * or isn't a holiday, saturday and sunday
	 *
	 * @param every is an int
	 * @param exclude is a List of Date objects
	 * @return A List of Date objects
	 */
	public List<Date> makeSmartDateList(int every, List<Date> exclude) {
		List<Date> smartDateList = new ArrayList<>();
		Date currentDate = this;
		while (smartDateList.size() < 10) {
			if (!exclude.contains(currentDate) && !currentDate.isHoliday() && currentDate.dayOfWeek() != 5
					&& currentDate.dayOfWeek() != 6 && currentDate.getHour() > 8 && currentDate.getHour() < 18 &&
					currentDate.getHour() != 12 && currentDate.getHour() != 13) {
				smartDateList.add(currentDate);
			}
			currentDate = Date.incrementDate(currentDate, every);
		}
		return smartDateList;
	}

	/**
	 *
	 *
	 * @param o
	 * @return
	 */
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
		int month = currentDate.getMonth();
		int year = currentDate.getYear();
		int day = currentDate.getDay() + 1;
		if (day > Date.daysInMonth(month, year)) {
			day = 1;
			month++;
			if (month > 12) {
				month = 1;
				year++;
			}
		}
		return new Date(9, 0, day, month, year);
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

	/**
	 * Compares this object with the specified object for order.  Returns a
	 * negative integer, zero, or a positive integer as this object is less
	 * than, equal to, or greater than the specified object.
	 * <p>
	 * <p>The implementor must ensure
	 * {@code sgn(x.compareTo(y)) == -sgn(y.compareTo(x))}
	 * for all {@code x} and {@code y}.  (This
	 * implies that {@code x.compareTo(y)} must throw an exception iff
	 * {@code y.compareTo(x)} throws an exception.)
	 * <p>
	 * <p>The implementor must also ensure that the relation is transitive:
	 * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
	 * {@code x.compareTo(z) > 0}.
	 * <p>
	 * <p>Finally, the implementor must ensure that {@code x.compareTo(y)==0}
	 * implies that {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for
	 * all {@code z}.
	 * <p>
	 * <p>It is strongly recommended, but <i>not</i> strictly required that
	 * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
	 * class that implements the {@code Comparable} interface and violates
	 * this condition should clearly indicate this fact.  The recommended
	 * language is "Note: this class has a natural ordering that is
	 * inconsistent with equals."
	 * <p>
	 * <p>In the foregoing description, the notation
	 * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
	 * <i>signum</i> function, which is defined to return one of {@code -1},
	 * {@code 0}, or {@code 1} according to whether the value of
	 * <i>expression</i> is negative, zero, or positive, respectively.
	 *
	 * @param o the object to be compared.
	 * @return a negative integer, zero, or a positive integer as this object
	 * is less than, equal to, or greater than the specified object.
	 * @throws NullPointerException if the specified object is null
	 * @throws ClassCastException   if the specified object's type prevents it
	 *                              from being compared to this object.
	 */
	@Override
	public int compareTo(Date o) {
		return this.intValue() - o.intValue();
	}
}
