package fcul.pco.dentalclinic.main;

import fcul.pco.dentalclinic.domain.DoctorCatalog;
import fcul.pco.dentalclinic.domain.PatientCatalog;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class App {
    private static void executeUseCase(String useCaseFileName) throws IOException {
        System.out.println("Test: " + useCaseFileName);
        Scanner in = new Scanner(new File(useCaseFileName));
        in.useLocale(Locale.US);
        in.nextLine();
        Menu.mainMenu(in);
        in.close();
    }

    public static void executeAllUseCases() {
        try {
            executeUseCase("data/addDoctorUsecase");
            // acrescentar aqui a execução de mais usecases.
            // executeUseCase("data/maisUsecaseAVossaEscolha");
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static DoctorCatalog getDoctorCatalog() {
        return DoctorCatalog.getInstance();
    }

    public static PatientCatalog getPatientCatalog() {
        return PatientCatalog.getInstance();
    }


}
