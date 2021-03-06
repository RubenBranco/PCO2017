package fcul.pco.dentalclinic.domain;

import fcul.pco.dentalclinic.main.App;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements an appointment
 *
 * @author João Regueira, Ruben Branco
 */
public class Appointment implements Comparable<Appointment> {
	private Date date;
	private String description;
	private int duration;
	private Patient patient;

	/**
	 * Constructor of the class Appointment
	 *
	 *
	 * @param date is date Object
	 * @param description is a String
	 * @param duration is an Int
	 * @param patient is a Patient object
	 */

	public Appointment(Date date, String description, int duration, Patient patient) {
		this.date = date;
		this.description = description;
		this.duration = duration;
		this.patient = patient;
	}

	/**
	 * This method sets a task
	 *
	 * @param task is a String
	 */
	public void setTask(String task) {
		description = task;
	}

	/**
	 * This method returns a Date object
	 *
	 * @return the instanced Date object
	 */

	public Date getDate(){
		return date;
	}

	/**
	 * This method returns an incremented time for a Date object
	 *
	 * @return incremented Date object
	 */
	
	public Date getEndDate() {
		return Date.incrementDate(date, duration);
	}

	/**
	 * This method returns the instance as a string.
	 *
	 * @return the instance as a string
	 */
	@Override
	public String toString(){
		return date + "$" + duration + "$" + description + "$" + patient;
	}

	/**
	 * Builds an appointment object from a string
	 *
	 * @param s is a string with a format: date$duration$description$patient
	 * @return an appointment object
	 */
	public static Appointment fromString(String s) {
	    String[] apt = s.split("\\$");
	    return new Appointment(Date.fromString(apt[0]), apt[2], Integer.parseInt(apt[1]), Patient.fromString(apt[3]));
    }

	/**
	 * Creates a list of strings of appointment variables as a row.
	 *
	 * @return a list of strings
	 */
	public List<String> toRow() {
		List<String> row = new ArrayList<>();
		row.add(date.toString());
		row.add(patient.getName());
		row.add(description);
		return row;
	}

	/**
	 * Compares (subtraction) the instances date with another appointments date
	 *
	 * @param o is an appointment object
	 * @return an int
	 */
	@Override
	public int compareTo(Appointment o) {
		Date thisDate = this.getDate();
		Date otherDate = o.getDate();
		return thisDate.compareTo(otherDate);
	}
}
