package fcul.pco.dentalclinic.persistence;

import fcul.pco.dentalclinic.domain.Patient;
import fcul.pco.dentalclinic.main.ApplicationConfiguration;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.io.FileNotFoundException;

/**
 * This class implements save and load methods for a PatientCatalog
 *
 * @author Thibault Langlois
 */
public class PatientCatalog {

    /**
     * Saves Patients from a Map into a configuration save file
     *
     * @param patients is a Map of Patients
     * @throws IOException
     */
    public static void save(Map<Integer, Patient> patients) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ApplicationConfiguration.ROOT_DIRECTORY
                + ApplicationConfiguration.PATIENT_CATALOG_FILENAME))) {
            for (Patient d : patients.values()) {
                bw.write(d.toString());
                bw.newLine();
            }
        }
    }

    /**
     * Loads a PatientCatalog from configuration save file
     *
     * @return a Map of Patients
     */
    public static Map<Integer, Patient> load() {
        Map<Integer, Patient> patients = new TreeMap<>();
        try (Scanner br = new Scanner(new FileReader(ApplicationConfiguration.ROOT_DIRECTORY
                + ApplicationConfiguration.PATIENT_CATALOG_FILENAME))) {
            while (br.hasNextLine()) {
                Patient p = Patient.fromString(br.nextLine().replace("\n", ""));
                patients.put(p.getId(), p);
            }
        } catch (FileNotFoundException ex) {
            return patients;
        }
        return patients;
    }
}
