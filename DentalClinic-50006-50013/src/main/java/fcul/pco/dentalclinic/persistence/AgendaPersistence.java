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
import java.util.List;
import java.util.Scanner;

public class AgendaPersistence {
    public static void save(Doctor d) throws IOException {
        Agenda a = d.getAgenda();
        FileWriter saveFile = new FileWriter(ApplicationConfiguration.ROOT_DIRECTORY + d.getId());
        for (Appointment apt : a) {
            saveFile.write(apt.toString() + "\n");
        }
        saveFile.close();
    }

    public static Agenda load(Doctor d) throws FileNotFoundException{
        Agenda a = new Agenda();
        try (Scanner saveFile = new Scanner(new File(ApplicationConfiguration.ROOT_DIRECTORY + d.getId()))){
            while (saveFile.hasNextLine()){
                String line = saveFile.nextLine().replace("\n","");
                a.addAppointment(Appointment.fromString(line));
            }
        }
        catch (FileNotFoundException ex) {
            return a;
        }
        return a;
    }
}
