package fcul.pco.dentalclinic.domain;

public class Appointment {
	private Date date;
	private String description;
	private int duration;
	
	public Appointment(Date date, String description, int duration) {
		this.date = date;
		this.description = description;
		this.duration = duration;
	}
	
	public void setTask(String task) {
		description = task;
	}
	
	public Date getEndDate() {
		int minute = (date.getMinute() + duration) % 60;
		int hoursElapsed = (date.getMinute() + duration) / 60;
		int hour = (date.getHour() + hoursElapsed) % 24;
		int daysElapsed = (date.getHour() + hoursElapsed) / 24;
		int day = date.getDay();
		int month = date.getMonth();
		int monthCache = date.getMonth();
		int year = date.getYear();
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
	@Override
	public String toString(){
		return date + "$" + duration + "$" + description;
	}

	public static Appointment fromString(String s) {
	    String[] apt = s.split("$");
    }
}
