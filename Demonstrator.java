/**
 * Demonstrator class extends functionality of instructor
 * Demonstrators can only teach subject specialism 2
 */
public class Demonstrator extends Instructor {

    /**
     * Constructor for the Demonstrator class (inherits Instructor constructor)
     * @param name of Demonstrator
     * @param gender of Demonstrator (M or F)
     * @param age of Demonstrator
     */
    public Demonstrator(String name, char gender, int age) {
        super(name, gender, age);
    }

    /**
     * Constructor for the Demonstrator class (inherits Instructor constructor) Used when configuration file has IDs
     * parameters are the same as the constructor above
     * @param ID of Demonstrator given by config file
     */
    public Demonstrator(String name, char gender, int age, int ID) {
        super(name, gender, age, ID);
    }

    /**
     * @param subject to check if the instructor can teach
     * @return True if the subject specialism is 2
     */
    @Override
    public Boolean canTeach(Subject subject) {

        return subject.getSpecialism() == 2;    // return true if specialism is 2
    }


}
