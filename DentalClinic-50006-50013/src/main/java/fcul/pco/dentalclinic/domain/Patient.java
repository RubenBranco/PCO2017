package fcul.pco.dentalclinic.domain;

public class Patient extends Person{
    public Patient(String name, int sns) {
        super(sns, name);
    }

    public static Patient fromString(String s) {
        String[] patient = s.split(",");
        return new Patient(patient[1], Integer.parseInt(patient[0]));
    }
}
