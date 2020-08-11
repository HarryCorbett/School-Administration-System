/**
 * OOTrainer class extends functionality of instructor
 * OOTrainer can teach subject specialism 1,2 and 3
 */
public class OOTrainer extends Teacher {

    /**
     * Constructor for the OOTrainer class (inherits Instructor constructor)
     * @param name of OOTrainer
     * @param gender of OOTrainer (M or F)
     * @param age of OOTrainer
     */
    public OOTrainer(String name, char gender, int age) {
        super(name, gender, age);
    }

    /**
     * Constructor for the OOTrainer class (inherits Instructor constructor) used when config file gives ID
     * @param ID of OOTrainer to add
     */
    public OOTrainer(String name, char gender, int age, int ID) {
        super(name, gender, age, ID);
    }

    /**
     * @param subject to check if the OOTrainer can teach
     * @return True if the subject specialism is 1,2 or 3
     */
    @Override
    public Boolean canTeach(Subject subject) {

        return subject.getSpecialism() == 1 || subject.getSpecialism() == 2 || subject.getSpecialism() == 3;    // return true if specialism is 1,2 or 3
    }

}
