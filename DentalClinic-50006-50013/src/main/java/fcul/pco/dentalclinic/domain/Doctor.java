package fcul.pco.dentalclinic.domain;

public class Doctor extends Person{
    private Agenda agenda;

    public Doctor(String name, int id, Agenda agenda) {
        super(id, name);
        this.agenda = agenda;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public static Doctor fromString(String s) {
        String[] doctor = s.split(",");
        return new Doctor(doctor[1], Integer.parseInt(doctor[0]), new Agenda());
    }
}
