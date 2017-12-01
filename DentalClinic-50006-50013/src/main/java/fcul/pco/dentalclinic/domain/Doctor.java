package fcul.pco.dentalclinic.domain;

import java.io.FileNotFoundException;

public class Doctor extends Person{
    private Agenda agenda;

    /**
     * Constructor for Doctor
     *
     * @param name is a String
     * @param id is an int
     * @throws FileNotFoundException
     */
    public Doctor(String name, int id) throws FileNotFoundException {
        super(id, name);
        this.agenda = Agenda.load(this);
    }

    public Agenda getAgenda() {
        return agenda;
    }

    /**
     * Creates a Doctor object from a String with the format id,name
     *
     * @param s is a String
     * @return a Doctor object
     * @throws FileNotFoundException
     */
    public static Doctor fromString(String s) throws FileNotFoundException {
        String[] doctor = s.split(",");
        return new Doctor(doctor[1], Integer.parseInt(doctor[0]));
    }
}
