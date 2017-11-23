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
 *
 * @author Thibault Langlois
 */
public class PatientCatalog {

    public static void save(Map<Integer, Patient> patients) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ApplicationConfiguration.ROOT_DIRECTORY
                + ApplicationConfiguration.PATIENT_CATALOG_FILENAME))) {
            for (Patient d : patients.values()) {
                bw.write(d.toString());
                bw.newLine();
            }
        }
    }

    public static Map<Integer, Patient> load() {
        Map<Integer, Patient> patients = new TreeMap<>();
        try (Scanner br = new Scanner(new FileReader(ApplicationConfiguration.ROOT_DIRECTORY
                + "/"
                + ApplicationConfiguration.PATIENT_CATALOG_FILENAME))) {
            while (br.hasNextLine()) {
                Patient p = Patient.fromString(br.nextLine());
                patients.put(p.getId(), p);
            }
        } catch (FileNotFoundException ex) {
            // if the file is not found return the empty catalog.
        }
        return patients;
    }
}
