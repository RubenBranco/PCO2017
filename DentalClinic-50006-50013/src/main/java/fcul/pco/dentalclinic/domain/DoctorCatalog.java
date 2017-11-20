package fcul.pco.dentalclinic.domain;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

    /**
     * Saves a catalog to a file.
     *
     * @throws IOException
     */
    public void save() throws IOException {
        fcul.pco.dentalclinic.persistence.DoctorCatalog.save(doctorCatalog);
    }

    /**
     * Loads a catalog from a file and returns a catalog instance.
     *
     * @throws IOException
     */
    public void load() throws IOException {
        doctorCatalog = fcul.pco.dentalclinic.persistence.DoctorCatalog.load();
    }

    @Override
    public String toString() {
        List<List<String>> table = new ArrayList<>();
        for (Doctor d : doctorCatalog.values()) {
            ArrayList<String> row = new ArrayList<>();
            row.add(String.valueOf(d.getId()));
            row.add(d.getName());
            table.add(row);
        }
        return Utils.tableToString(table);
    }
}
