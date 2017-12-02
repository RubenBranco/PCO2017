package fcul.pco.dentalclinic.domain;

/**
 * This class implements a Patient
 *
 * @author João Regueira, Ruben Branco
 */

public class Patient extends Person{
    /**
     * Constructor for Patient
     *
     * @param name is a String
     * @param sns is an int
     */
    public Patient(String name, int sns) {
        super(sns, name);
    }

    /**
     * Creates a Patient object from a String with the format id,name
     *
     * @param s is a String
     * @return a Patient object
     */
    public static Patient fromString(String s) {
        String[] patient = s.split(",");
        return new Patient(patient[1], Integer.parseInt(patient[0]));
    }
}
