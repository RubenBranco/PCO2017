package domain;

import java.util.ArrayList;

public class Agenda {
    private ArrayList<Appointment> agenda;

    public Agenda() {
        agenda = new ArrayList<>();
    }

    public void addAppointment(Appointment a) {
        agenda.add(a);
    }
}
