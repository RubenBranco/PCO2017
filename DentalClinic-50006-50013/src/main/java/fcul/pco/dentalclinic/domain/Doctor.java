package fcul.pco.dentalclinic.domain;

import java.io.FileNotFoundException;

public class Doctor extends Person{
    private Agenda agenda;

    public Doctor(String name, int id) throws FileNotFoundException {
        super(id, name);
        this.agenda = Agenda.load(this);
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public static Doctor fromString(String s) throws FileNotFoundException {
        String[] doctor = s.split(",");
        return new Doctor(doctor[1], Integer.parseInt(doctor[0]));
    }
}
