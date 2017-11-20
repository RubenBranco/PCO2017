package fcul.pco.dentalclinic.persistence;

import fcul.pco.dentalclinic.domain.Agenda;
import fcul.pco.dentalclinic.domain.Appointment;
import fcul.pco.dentalclinic.domain.Doctor;
import fcul.pco.dentalclinic.main.ApplicationConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AgendaPersistence {
    public static void save(Agenda a, Doctor d) throws IOException {
        FileWriter saveFile = new FileWriter(ApplicationConfiguration.ROOT_DIRECTORY + d.getId());
        ArrayList<Appointment> appointments = a.getAppointments();
        for (Appointment apt : appointments) {
            saveFile.write(apt.toString() + "\n");
        }
        saveFile.close();
    }

    public static Agenda load(Doctor d) throws FileNotFoundException {
        Scanner saveFile = new Scanner(new File(ApplicationConfiguration.ROOT_DIRECTORY + d.getId()));
        Agenda a = new Agenda();
        while (saveFile.hasNextLine()){
            String line = saveFile.nextLine().replace("\n","");
            a.addAppointment(Appointment.fromString(line));
        }
        saveFile.close();
        return a;
    }
}
