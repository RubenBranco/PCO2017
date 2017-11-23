package fcul.pco.dentalclinic.domain;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class PatientCatalog {

    private Map<Integer, Patient> patientCatalog;
    private static PatientCatalog instance;

    private PatientCatalog() {
        patientCatalog = new TreeMap<>();
    }

    public static PatientCatalog getInstance() {
        if (instance == null) {
            instance = new PatientCatalog();
        }
        return instance;
    }

    public void addPatient(Patient p) {
        patientCatalog.put(p.getId(), p);
    }

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
     * Loads a catalog from a file and returns a catalog instance.
     *
     * @throws IOException
     */
    public void load() throws IOException {
        patientCatalog = fcul.pco.dentalclinic.persistence.PatientCatalog.load();
    }


}