package fcul.pco.dentalclinic.domain;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class implements a catalog of patients, where there's a list to access their info
 *
 * @author João Regueira, Ruben Branco
 */

public class PatientCatalog {

    private Map<Integer, Patient> patientCatalog;
    private static PatientCatalog instance;

    private PatientCatalog() {
        patientCatalog = new TreeMap<>();
    }

    /**
     * Gets the simpleton instance of the Catalog
     *
     * @return a PatientCatalog object
     */
    public static PatientCatalog getInstance() {
        if (instance == null) {
            instance = new PatientCatalog();
        }
        return instance;
    }

    /**
     * Adds a patient to the catalog
     *
     * @param p is a Patient object
     */
    public void addPatient(Patient p) {
        patientCatalog.put(p.getId(), p);
    }

    /**
     * Gets a patient by his SNS id
     *
     * @param id is an int
     * @return a Patient object from the catalog
     */
    public Patient getPatientById(int id) {
        return patientCatalog.get(id);
    }

    /**
     * Saves a catalog to a file.
     *
     * @throws IOException
     */
    public void save() throws IOException {
        fcul.pco.dentalclinic.persistence.PatientCatalog.save(patientCatalog);
    }


    /**
     * Formats a catalog into a String to be displayed to the user
     *
     * @return a String
     */
    @Override
    public String toString() {
        List<List<String>> table = new ArrayList<>();
        for (Patient p : patientCatalog.values()) {
            ArrayList<String> row = new ArrayList<>();
            row.add(String.valueOf(p.getId()));
            row.add(p.getName());
            table.add(row);
        }
        return Utils.tableToString(table);
    }

    /**
     * Loads a catalog from a file and returns a catalog instance.
     *
     * @throws IOException
     */
    public void load() throws IOException {
        patientCatalog = fcul.pco.dentalclinic.persistence.PatientCatalog.load();
    }


}
