/**
 * Teacher class extends functionality of instructor
 * Teachers can teach subject specialism 1 and 2
 */
public class Teacher extends Instructor{

    /**
     * Constructor for the teacher class (inherits Instructor constructor)
     * @param name of teacher
     * @param gender of teacher (M or F)
     * @param age of teacher
     */
    public Teacher(String name, char gender, int age) {
        super(name, gender, age);
    }

    /**
     * Constructor for the teacher class (inherits Instructor constructor) for creating teachers given an ID
     * parameters as above constructor
     * @param ID of teacher given by config file
     */
    public Teacher(String name, char gender, int age, int ID) {
        super(name, gender, age, ID);
    }

    /**
     * @param subject to check if the instructor can teach
     * @return True if the subject specialism is 1 or 2
     */
    @Override
    public Boolean canTeach(Subject subject) {

        return subject.getSpecialism() == 1 || subject.getSpecialism() == 2;    // return true if specialism is 1 or 2
    }
}
