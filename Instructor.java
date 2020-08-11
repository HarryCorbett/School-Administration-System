public abstract class Instructor extends Person {

    private Course assignedCourse;

    /**
     * Constructor for Instructors
     *
     * @param name   of instructor
     * @param gender of instructor (M or F)
     * @param age    of instructor
     */
    public Instructor(String name, char gender, int age) {
        super(name, gender, age);
        this.assignedCourse = null;
    }

    /**
     * Constructor for Instructors when given ID
     * parameters as above
     */
    public Instructor(String name, char gender, int age, int ID) {
        super(name, gender, age, ID +1);
        this.assignedCourse = null;
    }

    /**
     * Set the Instructors assigned course
     *
     * @param course the instructor will teach
     */
    public void assignCourse(Course course) {
        this.assignedCourse = course;
    }

    /**
     * Remove the Course a Instructor has been assigned
     */
    public void unassignCourse() {
        this.assignedCourse = null;
    }

    /**
     * Get the Course that an instructor is assigned
     *
     * @return assignedCourse
     */
    public Course getAssignedCourse() {
        return assignedCourse;
    }

    /**
     * Abstract method for checking if a given instructor can teach a subject
     *
     * @param subject to check if the instructor can teach
     * @return True if the instructor can teach the subject, false if not
     */
    public abstract Boolean canTeach(Subject subject);

}
