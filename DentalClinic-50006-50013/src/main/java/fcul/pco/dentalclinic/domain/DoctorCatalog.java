package fcul.pco.dentalclinic.domain;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class implements a catalog with doctors in order to access their information by having a list
 *
 * @author João Regueira, Ruben Branco
 */

public class DoctorCatalog {

    private Map<Integer, Doctor> doctorCatalog;
    private static DoctorCatalog instance;

    private DoctorCatalog() {
        doctorCatalog = new TreeMap<>();
    }

    /**
     * Gets the simpleton instance of a doctor catalog
     *
     * @return a DoctorCatalog object
     */
    public static DoctorCatalog getInstance() {
        if (instance == null) {
            instance = new DoctorCatalog();
        }
        return instance;
    }

    /**
     * Formats a catalog into a String to be displayed to the user
     *
     * @return a String
     */
    @Override
    public String toString() {
        List<List<String>> table = new ArrayList<>();
        for (Doctor d : doctorCatalog.values()) {
            ArrayList<String> row = new ArrayList<>();
            row.add(String.valueOf(d.getId()));
            row.add(d.getName());
            table.add(row);
        }
        if (table.size() > 0) return Utils.tableToString(table);
        else return "Não existe médicos registados";
    }

    /**
     * Adds a doctor to the DoctorCatalog
     *
     * @param d is a Doctor object
     */
    public void addDoctor(Doctor d) {
        doctorCatalog.put(d.getId(), d);
    }

    /**
     * Gets a Doctor from the catalog using his id
     *
     * @param id is an int
     * @return a Doctor object
     */
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
     */
    public void load() {
        doctorCatalog = fcul.pco.dentalclinic.persistence.DoctorCatalog.load();
    }


}
