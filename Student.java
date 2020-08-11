import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class for storing Students information and performing functions on it
 * Sub class of Person, constructor shares name, gender and age
 */
public class Student extends Person {

    private ArrayList<Integer> certificates;
    private Boolean enrolled = false;

    /**
     * Constructor for Student class
     *
     * @param name   name of the person
     * @param gender gander of the person, either 'M' or 'F'
     * @param age    age of the person in years
     */
    public Student(String name, char gender, int age) {
        super(name, gender, age);
        this.certificates = new ArrayList<Integer>();
        this.enrolled = false;
    }

    /**
     * Constructor for Student class when loading with ID
     * parameters are as above
     */
    public Student(String name, char gender, int age, String certificates, int ID) {
        super(name, gender, age, ID);

        this.certificates = new ArrayList<Integer>();

        if (!certificates.isBlank()) {
            if (certificates.contains("-")) {

                String[] certificateList = certificates.split("-");

                for (String certificateValue : certificateList) {
                    this.certificates.add(Integer.parseInt(certificateValue));
                }

            }else{

                this.certificates.add(Integer.parseInt(certificates));

            }
        }
    }

    /**
     * Adds the ID of a compelted subject to the students certificates array list
     *
     * @param subject subject the student has graduated from
     */
    public void graduate(Subject subject) {
        certificates.add(subject.getID());

    }

    /**
     * Return the list of certificates
     *
     * @return list of certificates
     */
    public ArrayList<Integer> getCertificates() {
        return certificates;
    }

    /**
     * Check if a stident has a given subject certificate
     *
     * @param subject to check if the student has completed
     * @return true if the student has completed the given subject
     */
    public Boolean hasCertificate(Subject subject) {
        return certificates.contains(subject.getID());
    }

    /**
     * Returns whether a student is currently enrolled on the course
     *
     * @return true if on the course
     */
    public boolean getEnrolled(Course course) {

        List<Student> studentsOnCourse = Arrays.asList(course.getStudents());

        return studentsOnCourse.contains(this);
    }

    /**
     * Returns whether a student is currently enrolled on a course
     *
     * @return true if on a course
     */
    public boolean getEnrolled(ArrayList<Course> courses) {

        boolean enrolled = false;

        for(Course course: courses){

            List<Student> studentsOnCourse = Arrays.asList(course.getStudents());

            if(studentsOnCourse.contains(this)){
                enrolled = true;
            }
        }
        return enrolled;
    }


}
