package fcul.pco.dentalclinic.domain;

import fcul.pco.dentalclinic.persistence.AgendaPersistence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private ArrayList<Appointment> agenda;

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
        AgendaPersistence.save(this, d);
    }
    public static Agenda load(Doctor d) throws FileNotFoundException {
        return AgendaPersistence.load(d);
    }
}
