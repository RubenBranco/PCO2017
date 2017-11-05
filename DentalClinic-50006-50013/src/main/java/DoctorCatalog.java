import domain.Doctor;

import java.util.TreeMap;

public class DoctorCatalog {

    private TreeMap<Integer, Doctor> doctorCatalog;
    private static DoctorCatalog instance;

    private DoctorCatalog() {
        doctorCatalog = new TreeMap<>();
    }

    public static DoctorCatalog getInstance() {
        if (instance == null) {
            instance = new DoctorCatalog();
        }
        return instance;
    }

    public void addDoctor(Doctor d) {
        doctorCatalog.put(d.getId(), d);
    }

    public Doctor getDoctorById(int id) {
        return doctorCatalog.get(id);
    }
}
