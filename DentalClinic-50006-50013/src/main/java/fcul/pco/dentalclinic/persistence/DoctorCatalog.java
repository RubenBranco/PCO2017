package fcul.pco.dentalclinic.persistence;
import fcul.pco.dentalclinic.main.ApplicationConfiguration;
import fcul.pco.dentalclinic.domain.Doctor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * This class implements save and load methods for a DoctorCatalog
 *
 * @author João Regueira, Ruben Branco
 */

public class DoctorCatalog {

    /**
     * Saves Doctors from a Map into a configuration save file
     *
     * @param doctors is a Map of Doctors
     * @throws IOException
     */
    public static void save(Map<Integer, Doctor> doctors) throws IOException {
        FileWriter saveFile = new FileWriter(ApplicationConfiguration.ROOT_DIRECTORY + ApplicationConfiguration.DOCTOR_CATALOG_FILENAME);
        for (Doctor d : doctors.values()) {
            saveFile.write(d.toString() + '\n');
        }
        saveFile.close();
    }

    /**
     * Loads a DoctorCatalog from configuration save file
     *
     * @return a Map of Doctors
     * @throws FileNotFoundException
     */
    public static Map<Integer, Doctor> load() {
        TreeMap<Integer, Doctor> doctors = new TreeMap<>();
        try (Scanner saveFile = new Scanner(new File(ApplicationConfiguration.ROOT_DIRECTORY + ApplicationConfiguration.DOCTOR_CATALOG_FILENAME))){
            while (saveFile.hasNextLine()) {
                String line = saveFile.nextLine().replaceAll("\n", "");
                Doctor doctor = Doctor.fromString(line);
                doctors.put(doctor.getId(), doctor);
            }
        } catch (FileNotFoundException ex) {
            return doctors;
        }
        return doctors;
    }

}
