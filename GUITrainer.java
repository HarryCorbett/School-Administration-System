/**
 * GUITrainer class extends functionality of instructor
 * GUITrainer can teach subject specialism 1,2 and 4
 */
public class GUITrainer extends Teacher {

    /**
     * Constructor for the GUITrainer class (inherits Instructor constructor)
     * @param name of GUITrainer
     * @param gender of GUITrainer (M or F)
     * @param age of GUITrainer
     */
    public GUITrainer(String name, char gender, int age) {
        super(name, gender, age);
    }

    /**
     * Constructor for the GUITrainer class (inherits Instructor constructor) used when ID is given in config file
     * @param ID of GUITrainer to add
     */
    public GUITrainer(String name, char gender, int age, int ID) {
        super(name, gender, age, ID);
    }

    /**
     * @param subject to check if the GUITrainer can teach
     * @return True if the subject specialism is 1,2 or 3
     */
    @Override
    public Boolean canTeach(Subject subject) {

        return subject.getSpecialism() == 1 || subject.getSpecialism() == 2 || subject.getSpecialism() == 4;    // return true if specialism is 1,2 or 4
    }
}
