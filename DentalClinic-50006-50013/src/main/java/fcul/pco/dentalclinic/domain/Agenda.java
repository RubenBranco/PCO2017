package fcul.pco.dentalclinic.domain;

import fcul.pco.dentalclinic.persistence.AgendaPersistence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * This class implements a doctors agenda
 *
 * @author João Regueira, Ruben Branco
 */
public class Agenda implements Iterable<Appointment>{
    private List<Appointment> agenda;


    public Agenda() {
        agenda = new ArrayList<>();
    }

    /**
     * Adds an appointment to an agenda list
     *
     * @param a is an appointment instance
     *
     */
    public void addAppointment(Appointment a) {
        agenda.add(a);
    }

    /**
     * A method to allow iteration of the agenda object
     *
     * @return an iterator for the agenda
     */
    public Iterator<Appointment> iterator() {
        return agenda.iterator();
    }

    /**
     * This method saves a doctor instance
     *
     * @param d is a Doctor object
     * @throws IOException
     */
    public void save(Doctor d) throws IOException {
        AgendaPersistence.save(d);
    }

    /**
     * This method loads a doctor into the agenda
     *
     * @param d is a Doctor object
     * @return returns an agenda object
     * @throws FileNotFoundException
     */
    public static Agenda load(Doctor d) throws FileNotFoundException {
        return AgendaPersistence.load(d);
    }

    /**
     * Gets the appointments for a given date
     *
     * @param d is a Date object
     * @return a list of appointments
     */
    public List<Appointment> getDayAppointments(Date d){
        List<Appointment> dayAppointments = new ArrayList<>();
        for (Appointment apt : agenda) {
            Date aptDate = apt.getDate();
            if (aptDate.getDay() == d.getDay() && aptDate.getMonth() == d.getMonth() && aptDate.getYear() == d.getYear()){
                dayAppointments.add(apt);
            }
        }
        Collections.sort(dayAppointments);
        return dayAppointments;
    }

    /**
     * This method returns a list of appointments after the date from
     *
     * @param from is a Date object
     * @return a List of Date
     */
    public List<Date> getNextAppointmentDates(Date from) {
        List<Date> aptList = new ArrayList<>();
        for (Appointment apt : agenda) {
            Date aptDate = apt.getDate();
            if (aptDate.minutesSinceStartDate() > from.minutesSinceStartDate()) {
                aptList.add(aptDate);
            }
        }
        return aptList;
    }
}
