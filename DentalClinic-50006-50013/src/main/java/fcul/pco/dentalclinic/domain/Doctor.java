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
        int doctorId = Integer.parseInt(s.split(",")[0]);
        String doctorName = s.split(",")[1];
        return new Doctor(doctorName, doctorId, new Agenda());
    }
}
