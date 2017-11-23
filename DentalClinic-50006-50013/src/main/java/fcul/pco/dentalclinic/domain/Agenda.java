package fcul.pco.dentalclinic.domain;

import fcul.pco.dentalclinic.persistence.AgendaPersistence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private List<Appointment> agenda;

    public Agenda() {
        agenda = new ArrayList<>();
    }

    public void addAppointment(Appointment a) {
        agenda.add(a);
    }

    public List<Appointment> getAppointments(){
        return agenda;
    }

    public void save(Doctor d) throws IOException {
        AgendaPersistence.save(d);
    }
    public static Agenda load(Doctor d) throws FileNotFoundException {
        return AgendaPersistence.load(d);
    }

    public List<Date> getNextAppointmentDates(Date from) {
        List<Date> aptList = new ArrayList<>();
        for (Appointment apt : agenda) {
            Date aptDate = apt.getDate();
            if (aptDate.getYear() >= from.getYear() && aptDate.getMonth() >= from.getMonth() && aptDate.getDay() >=
                    from.getDay() && aptDate.getHour() >= from.getHour() && aptDate.getMinute() > from.getMinute()){
                aptList.add(aptDate);
            }
        }
        return aptList;
    }
}
