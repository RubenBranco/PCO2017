package domain;

public class Doctor {

    private String name;
    private int id;
    private Agenda agenda;

    public Doctor(String name, int id, Agenda agenda) {
        this.name = name;
        this.id = id;
        this.agenda = agenda;
    }

    public String getName() {
        return name;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return String.format("%d,%s", id, name);
    }

    public static Doctor fromString(String s) {
        int doctorId = Integer.parseInt(s.split(",")[0]);
        String doctorName = s.split(",")[1];
        return new Doctor(doctorName, doctorId, new Agenda());
    }
}
