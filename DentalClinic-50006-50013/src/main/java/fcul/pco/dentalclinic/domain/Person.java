package fcul.pco.dentalclinic.domain;

/**
 * This class implements a Person
 *
 * @author João Regueira, Ruben Branco
 */

public class Person {
    private int id;
    private String name;

    /**
     * Constructor for a Person
     *
     * @param id is an int
     * @param name is a String
     */
    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets the ID from the Person
     *
     * @return an int
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the name from the Person
     *
     * @return a String
     */
    public String getName() {
        return name;
    }

    /**
     * Turns the Person object into a String with the format id,name
     *
     * @return a String
     */
    @Override
    public String toString() {
        return String.format("%d,%s", id, name);
    }
}
