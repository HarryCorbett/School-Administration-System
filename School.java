import java.util.ArrayList;

/**
 * School class, stores information about Students, Instructors, Subjects and Courses
 */
public class School {

    private String name;
    private ArrayList<Student> students;
    private ArrayList<Instructor> instructors;
    private ArrayList<Course> courses;
    private ArrayList<Subject> subjects;

    /**
     * Constructor for the School class
     *
     * @param name of the School
     */
    public School(String name) {
        this.name = name;
        this.students = new ArrayList<Student>();
        this.instructors = new ArrayList<Instructor>();
        this.courses = new ArrayList<Course>();
        this.subjects = new ArrayList<Subject>();
    }

    /**
     * @return the name of the school
     */
    public String getName() {
        return name;
    }

    // Methods for Students

    /**
     * @return Array list of students in he school
     */
    public ArrayList<Student> getStudents() {
        return students;
    }

    /**
     * Add a student to the school
     *
     * @param student to add to the school
     */
    public void add(Student student) {

        students.add(student);
    }

    /**
     * Remove the given student from the school
     *
     * @param student to remove from the school
     */
    public void remove(Student student) {

        students.remove(student);
    }

    // methods for Instructors

    /**
     * @return Array list of Instructors in the school
     */
    public ArrayList<Instructor> getInstructors() {
        return instructors;
    }

    /**
     * Add a Instructor to the school
     *
     * @param instructor to add to the school
     */
    public void add(Instructor instructor) {

        instructors.add(instructor);
    }

    /**
     * Remove the given instructor from the school
     *
     * @param instructor to remove from the school
     */
    public void remove(Instructor instructor) {

        instructors.remove(instructor);
    }

    // Methods for Courses

    /**
     * @return Array list of courses the school teaches
     */
    public ArrayList<Course> getCourses() {
        return courses;
    }

    /**
     * Add a course to the school
     *
     * @param course to add to the school
     */
    void add(Course course) {

        courses.add(course);
    }

    /**
     * Remove the given course from the school
     *
     * @param course to remove from the school
     */
    private void remove(Course course) {

        courses.remove(course);
    }

    // Methods for Subjects

    /**
     * @return Array list of subjects the school teaches
     */
    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    /**
     * Add a Subject to the school
     *
     * @param subject to add to the school
     */
    public void add(Subject subject) {

        subjects.add(subject);
    }

    /**
     * Remove the given Subject from the school
     *
     * @param subject to remove from the school
     */
    public void remove(Subject subject) {

        subjects.remove(subject);
    }


    /**
     * Have a day go by for the school
     */
    public void aDayAtSchool() {

        // For each subject, if there is not currently a course students can enrol to for it, create one
        for (Subject subject : subjects) {

            boolean courseForSubject = false;

            for (Course course : courses) {
                if (course.getSubject() == subject && course.getStatus() < 0) {
                    courseForSubject = true;
                }
            }

            if (!courseForSubject) {
                this.add(new Course(subject, 2));
            }
        }

        // Assign instructors to each course
        for (Course course : courses) {

            if (!course.hasInstructor()) {

                for (Instructor instructor : instructors) {

                    if (instructor.getAssignedCourse() == null && instructor.canTeach(course.getSubject())) {  // If the instructor does not have a course and can teach the subject
                        instructor.assignCourse(course);
                        course.setInstructor(instructor);
                        break;  // return to looping through courses as that instructor is now assigned
                    }
                }
            }

        }

        // Assign students to each course
        for (Student student : students) {

            for (Course course : courses) {

                if (!student.getEnrolled(courses)) {  // if the student is free

                    if (course.getSize() < 3 && !student.hasCertificate(course.getSubject())) {   // if the course is not full and the student has not already graduated from the subject

                        course.enrolStudent(student);

                    }

                }

            }

        }

        // Allow each course to progress a day
        for (Course course : courses) {

            course.aDayPasses();

        }

        // Loop backwards though the courses array and remove any that are cancelled or finished
        // Looping backwards ensures that the locations of unchecked courses do not change while looping through the array list and therefore do not get missed
        for (int i = courses.size() - 1; i >= 0; i--) {

            if (courses.get(i).isCancelled() || courses.get(i).getStatus() == 0) {
                courses.remove(courses.get(i));

            }
        }

    }

    /**
     * Overrides toString in Object class
     *
     * @return information about each of the classes linked to school
     */
    public String toString() {

        // Print school details
        System.out.println("School [name = " + this.name + "]");

        // Loop through each course in the school
        for (Course course : this.getCourses()) {
            System.out.print("    ");

            // if there is no instructor, store that there isn't one, if there is, get their name (this is output with the other data)
            String instructorName;
            if (course.getInstructor() == null) {
                instructorName = "No instructor";
            } else {
                instructorName = course.getInstructor().getName();
            }

            System.out.println("Course [Subject ID = " + course.getSubject().getID() +
                    ", Status = " + course.getStatus() +
                    ", Instructor = " + instructorName +
                    ", Enrolled students = " + getStudentNames(course.getStudents()) +
                    ", Is cancelled = " + course.isCancelled() + "]");
        }

        System.out.println();

        // print each subjects details
        for (Subject subject : this.getSubjects()) {

            System.out.print("    ");
            System.out.println("Subject [Subject ID = " + subject.getID() +
                    ", Specialism = " + subject.getSpecialism() +
                    ", Duration = " + subject.getDuration() +
                    ", Description = " + subject.getDescription() + "]");
        }

        System.out.println();

        // print each students details
        for (Student student : this.getStudents()) {

            System.out.print("    ");
            System.out.println("Student [Name = " + student.getName() +
                    ", Age = " + student.getAge() +
                    ", Gender = " + student.getGender() +
                    ", Certificates = " + student.getCertificates() +
                    ", Enrolled = " + student.getEnrolled(courses) +
                    ", ID = " + student.getID() + "]");
        }

        System.out.println();

        // print each Instructors details
        for (Instructor instructor : this.getInstructors()) {
            System.out.print("    ");
            boolean assignedcourse = instructor.getAssignedCourse() != null;

            System.out.println(instructor.getClass().getName() +
                    " [Name = " + instructor.getName() +
                    ", Age = " + instructor.getAge() +
                    ", Gender = " + instructor.getGender() +
                    ", Assigned a course = " + assignedcourse +
                    ", ID = " + instructor.getID() +"]");
        }

        return "All information returned";
    }

    /**
     * takes the list of students on a course and makes a list of their names of data type string
     *
     * @param studentarray contains all students in the course
     * @return a list of type string of the students on the course
     */
    private String getStudentNames(Student[] studentarray) {

        StringBuilder studentNames = new StringBuilder();
        String prefix = "";

        for (Student student : studentarray) {
            studentNames.append(prefix);
            prefix = ", ";
            studentNames.append(student.getName());
        }

        return studentNames.toString();
    }

}
