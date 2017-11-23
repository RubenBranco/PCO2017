package fcul.pco.dentalclinic.domain;

public class Appointment {
	private Date date;
	private String description;
	private int duration;
	private Patient patient;
	
	public Appointment(Date date, String description, int duration, Patient patient) {
		this.date = date;
		this.description = description;
		this.duration = duration;
		this.patient = patient;
	}
	
	public void setTask(String task) {
		description = task;
	}

	public Date getDate(){
		return date;
	}
	
	public Date getEndDate() {
		return Date.incrementDate(date, duration);
	}
	@Override
	public String toString(){
		return date + "$" + duration + "$" + description + "$" + patient;
	}

	public static Appointment fromString(String s) {
	    String[] apt = s.split("$");
	    return new Appointment(Date.fromString(apt[0]), apt[2], Integer.parseInt(apt[1]), Patient.fromString(apt[3]));
    }
}
