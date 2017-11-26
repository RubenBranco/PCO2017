package fcul.pco.dentalclinic.main;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

import fcul.pco.dentalclinic.domain.*;

import java.util.List;

/**
 * This class deals with the interactions with the user.
 *
 * @author Thibault Langlois
 */
public class Menu {

    /**
     * The main menu of the application. It serves to distinguish which kind of
     * user is interacting with the application. It may be the clinic manager,
     * the front desk-person or the doctor. .
     *
     * @param in a Scanner instance that correspond to the input of the program.
     * @return
     * @throws IOException
     */
    static DoctorCatalog doctorCatalog;
    static PatientCatalog patientCatalog;

    public static void main(String[] args) throws IOException {
        initialize();
        interactiveMode();
        //App.executeAllUseCases();
    }
    private static void initialize() {
        patientCatalog = PatientCatalog.getInstance();
        doctorCatalog = DoctorCatalog.getInstance();
        try {
            doctorCatalog.load();
        }
        catch (IOException ex) {
            System.err.println("Error loading DoctorCatalog.");
        }
        try {
            patientCatalog.load();
        } catch (IOException ex){
            System.err.println("Error loading PatientCatalog");
        }
    }
    private static void interactiveMode() throws IOException {
        try (Scanner in = new Scanner(System.in)) {
            in.useLocale(Locale.US);
            Menu.mainMenu(in);
        }
    }


    static void mainMenu(Scanner in) throws IOException {
        boolean end = false;
        do {
            System.out.println("Escolhe uma opção: ");
            System.out.println("Marcações...................1");
            System.out.println("Tratamento do paciente......2");
            System.out.println("Gestão......................3");
            System.out.println("Terminar....................4");
            System.out.println("> ");
            switch (nextInt(in)) {
                case 1:
                    createAppointmentMenu(in);
                    break;
                case 2:
                    doctorMenu(in);
                    break;
                case 3:
                    managerMenu(in);
                    break;
                case 4:
                    end = true;
            }
        } while (!end);
    }

    private static void managerMenu(Scanner in) throws IOException {
        boolean end = false;
        do {
            System.out.println("Novo Médico................1");
            System.out.println("Listar Médicos.............2");
            System.out.println("Terminar...................3");
            System.out.println("> ");
            switch (nextInt(in)) {
                case 1:
                    makeDoctor(in);
                    break;
                case 2:
                    showDoctors();
                    break;
                case 3:
                    end = true;
                    break;
            }
        } while (!end);
    }

    private static void makeDoctor(Scanner in) throws IOException {
        System.out.println("Nome: ");
        String nome = nextLine(in);
        System.out.println("ID: ");
        int id = nextInt(in);
        if (App.getDoctorCatalog().getDoctorById(id) != null) {
            System.out.println("O Doctor " + id + " já se encontra registado !");
        } else {
            Doctor d = new Doctor(nome, id, new Agenda());
            App.getDoctorCatalog().addDoctor(d);
            App.getDoctorCatalog().save();
        }
    }

    private static void showDoctors() {
        System.out.println(App.getDoctorCatalog());
    }

    /**
     *
     * @param in a Scanner instance that correspond to the input of the program.
     * @return
     * @throws IOException
     */
    private static void doctorMenu(Scanner in) throws IOException {
        boolean end = false;
        do {
            System.out.println("Agenda do dia..............1");
            System.out.println("Agenda da semana...........2");
            System.out.println("Tratar paciente............3");
            System.out.println("Terminar...................4");
            System.out.println("> ");
            switch (nextInt(in)) {
                case 1:
                    dayAgenda(in);
                    break;
                case 2:
                    weekAgenda(in);
                    break;
                case 3:
                    PacientMenu(in);
                    break;
                case 4:
                    end = true;
                    break;
            }
        } while (!end);
    }

    private static void dayAgenda(Scanner in) throws IOException {
        // Not implemented yet
    }

    private static void weekAgenda(Scanner in) throws IOException {
        // Not implemented yet
    }

    /**
     *
     * @param in
     * @return
     * @throws IOException
     */
    private static void createAppointmentMenu(Scanner in) throws IOException {
        boolean end = false;
        do {
            System.out.println("Novo paciente..............1");
            System.out.println("Nova marcação..............2");
            System.out.println("Terminar...................3");
            System.out.println("> ");
            switch (nextInt(in)) {
                case 1:
                    makePatient(in);
                    break;
                case 2:
                    makeAppointment(in);
                    break;
                case 3:
                    end = true;
                    break;
            }
        } while (!end);
    }

    private static void PacientMenu(Scanner in) {
        // Not implemented yet
    }

    private static void makePatient(Scanner in) throws IOException {
        System.out.println("Nome do paciente: ");
        String name = nextLine(in);
        System.out.println("Número SNS: ");
        int id = nextInt(in);
        if (App.getPatientCatalog().getPatientById(id) != null) {
            System.out.println("O paciente " + id + " já se encontra registado !");
        } else {
            Patient d = new Patient(name, id);
            App.getPatientCatalog().addPatient(d);
            App.getPatientCatalog().save();
        }
        System.out.println(App.getPatientCatalog());
    }

    private static void makeAppointment(Scanner in) throws IOException {
        Patient p = null;
        Doctor d = null;
        System.out.println(App.getPatientCatalog());
        System.out.print("Indique o número do paciente: ");
        int id = nextInt(in);
        p = App.getPatientCatalog().getPatientById(id);
        if (p == null) {
            System.out.println("O paciente " + id + " não se encontra registado !");
        } else {
            System.out.print("Indique o número do médico: ");
            int idm = nextInt(in);
            d = App.getDoctorCatalog().getDoctorById(idm);
            if (d == null) {
               System.out.println("Não existe médico com o número " + idm); 
            } else {
               Agenda a = d.getAgenda();
               Date start = Date.getTomorrowMorning();
               List<Date> exclude = a.getNextAppointmentDates(start);
               System.out.println("exclude list : " + exclude);
               List<Date> dateList = 
                       start.makeSmartDateList(20,exclude);
               System.out.println(Date.dateListToString(dateList));
               System.out.print("Escolhe uma data: ");
               Date choice = dateList.get(nextInt(in)-1);
               System.out.println("Indique qual é o tratamento : ");
               String treatment = nextLine(in);
               Appointment apt = new Appointment(choice, treatment, 20, p);
               a.addAppointment(apt);
               System.out.println("Está marcado !");
               a.save(d);
            }
        }
    }

    /*
     * The following methods read several kinds of values from a Scanner. 
     * The Scanner may correspond to System.in of to an input file. This allows 
     * automatic testing of the application through "use cases" that are tested 
     * using the executeUseCase method in the App class.    
     * The reason for using these methods instead of Scanner's nextXXX() methods 
     * is they allow comments in the use case files. Comments are begin with # 
     * and end at the end of the line.
     * 
     */
    private static int nextInt(Scanner in) {
        String s = in.nextLine();
        while (s.startsWith("#")) {
            s = in.nextLine();
        }
        if (s.contains("#")) {
            try (Scanner sc = new Scanner(s)) {
                s = sc.next();
            }
        }
        return Integer.parseInt(s);
    }

    private static double nextDouble(Scanner in) {
        String s = in.nextLine();
        while (s.startsWith("#")) {
            s = in.nextLine();
        }
        if (s.contains("#")) {
            try (Scanner sc = new Scanner(s)) {
                s = sc.next();
            }
        }
        return Double.parseDouble(s);
    }

    private static String nextLine(Scanner in) {
        String s = in.nextLine();
        while (s.startsWith("#")) {
            s = in.nextLine();
        }
        if (s.contains("#")) {
            int p = s.indexOf("#");
            s = s.substring(0, p).trim();
        }
        return s;
    }

    private static int[] nextDate(Scanner in) {
        String s = in.nextLine();
        while (s.startsWith("#")) {
            s = in.nextLine();
        }
        if (s.contains("#")) {
            int p = s.indexOf("#");
            s = s.substring(0, p).trim();
        }
        String[] a = s.split("/");
        int[] d = new int[3];
        d[0] = Integer.parseInt(a[0]);
        d[1] = Integer.parseInt(a[1]);
        d[2] = Integer.parseInt(a[2]);
        return d;
    }

    private static int[] nextTime(Scanner in) {
        int d, m, y;
        String s = in.nextLine();
        while (s.startsWith("#")) {
            s = in.nextLine();
        }
        if (s.contains("#")) {
            int p = s.indexOf("#");
            s = s.substring(0, p).trim();
        }
        String[] a = s.split(":");
        int[] time = new int[2];
        time[0] = Integer.parseInt(a[0]);
        time[1] = Integer.parseInt(a[1]);
        return time;
    }

}
