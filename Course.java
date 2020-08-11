import java.util.Arrays;
import java.util.ArrayList;

/**
 * Class for storing information about coruses that can be compelted
 */
public class Course {

    private Subject subject;
    private int daysUntilStart;
    private int daysToRun;
    private ArrayList<Student> studentsEnroled;
    private Instructor instructor;
    private Boolean cancelled;

    /**
     * Constructor for Course
     *
     * @param subject        the subject associated with the course
     * @param daysUntilStart days remaining before the start of the Course
     */
    public Course(Subject subject, int daysUntilStart) {
        this.subject = subject;
        this.daysUntilStart = daysUntilStart;
        this.daysToRun = subject.getDuration();
        this.studentsEnroled = new ArrayList<Student>();
        this.cancelled = false;
    }

    /**
     * Constructor for Course when reading from a file
     * parameters have the same values as above constructor
     */
    public Course(Subject subject, int daysUntilStart, int daysToRun, Instructor instructor, ArrayList<Student> studentsToAdd){

        this.subject = subject;
        this.daysUntilStart = daysUntilStart;
        this.daysToRun = daysToRun;
        this.studentsEnroled = new ArrayList<Student>();
        this.cancelled = false;

        // assign instructor
        this.instructor = instructor;
        if(instructor != null) {
            instructor.assignCourse(this);
        }

        for(Student student:studentsToAdd){
            this.enrolStudent(student);
        }

    }

    /**
     * Gets the subject related to the course
     *
     * @return subject
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * @return Instructor for the course
     */
    public Instructor getInstructor() {
        return instructor;
    }

    /**
     * @return the amount of days until either starting the course (negative values) or finishing the course (positive values)
     * or 0 if the course has finished
     */
    public int getStatus() {
        if (daysUntilStart > 0) {
            return daysUntilStart * -1;
        } else if (daysToRun > 0) {
            return daysToRun;
        } else {
            return 0;
        }
    }


    // The following two get methods are used for saving the current state of courses
    /**
     * @return number of days until the course starts
     */
    public int getDaysUntilStart() {
        return daysUntilStart;
    }

    /**
     * @return number of days until the course finsihes
     */
    public int getDaysToRun() {
        return daysToRun;
    }

    /**
     * Advance one day in the course, adjust daysUntilStart and daysToRun accordingly
     * Graduate students when the course finishes (when daysToRun = 0) and un-assign the instructor
     * If the course is about to start without either students or an instructor, cancel it
     */
    public void aDayPasses() {

            // adjust daysUntilStart and daysToRun accordingly
            if (getStatus() < 0) {
                daysUntilStart -= 1;
            } else if (getStatus() > 0) {
                daysToRun -= 1;
            }

            // Give students the certificate for completing the course
            if (getStatus() == 0) {
                for (Student student : studentsEnroled) {
                    //If the student has not already passed the course graduate them (Student should not have done the course before due to course assignment, left as a precaution)
                    if (!(student.getCertificates().contains(this.subject.getID()))) {
                        student.graduate(this.subject);
                    }

                    instructor.unassignCourse();    // un-assign the instructor now the course has finished
                }
            }

            // If the course is about to start without an instructor or without students, cancel it, un-assign the instructor and/or students
            if (daysUntilStart == 0 && (!hasInstructor() || getSize() == 0)) {

                cancelled = true;

                if (hasInstructor()) {  // If there is an instructor, un-assign them
                    instructor.unassignCourse();
                }
                if (getSize() > -1) {  // If there are student, remove them and make them each no longer enrolled
                    for(Student student: studentsEnroled){
                    }
                    studentsEnroled.clear();
                }
            }
        }

    /**
     * @param student Student to add to the course
     * @return true if student added, false if course has already started or
     */
    public Boolean enrolStudent(Student student) {

        if (this.daysUntilStart > 0 && this.getStudents().length < 3) {  // If there is space in the course and the course has not started
            studentsEnroled.add(student);  // Add the student to the next open space in the array
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return number of students enrolled (int)
     */
    public int getSize() {
        return studentsEnroled.size();
    }

    /**
     * @return ArrayList of students enrolled
     */
    public Student[] getStudents() {
        return studentsEnroled.toArray(new Student[0]);
    }

    /**
     * Check if an instructor is capable of teaching the given course and if they can assign it to them
     *
     * @param instructor to attempt to assign to the course
     * @return True if the instructor has been assigned
     */
    public Boolean setInstructor(Instructor instructor) {
        if (instructor.canTeach(subject)) {
            instructor.assignCourse(this);
            this.instructor = instructor;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get whether the given course has an instructor
     *
     * @return Boolean
     */
    public Boolean hasInstructor() {
        return instructor != null;
    }

    /**
     * Return whether or not a course has been cancelled
     *
     * @return True if the course has been canceled
     */
    public Boolean isCancelled() {
        return cancelled;
    }
}

