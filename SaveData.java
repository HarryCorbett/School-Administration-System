import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

class SaveData {

    private static FileOutputStream fileOutputStream;

    static void save(School school, String fileName) {

        try {
            fileOutputStream = new FileOutputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        PrintStream print = new PrintStream(fileOutputStream);

        // write school name
        print.println("school:" + school.getName());

        // write subjects
        for (Subject subject : school.getSubjects()) {

            print.println("subject:" + subject.getDescription() + "," + subject.getID() + "," + subject.getSpecialism() + "," + subject.getDuration());

        }

        // write student
        for (Student student : school.getStudents()) {

            StringBuilder certificates = new StringBuilder();
            String prefix = "";

            for (int certificate: student.getCertificates()){
                certificates.append(prefix);
                certificates.append(certificate);
                prefix = "-";
            }

            print.print("student:" + student.getName() + "," + student.getGender() + "," + student.getAge() + "," );
            print.println( certificates + "," + student.getID());
        }

        // write instructors
        for (Instructor instructor : school.getInstructors()) {

            print.print(instructor.getClass().getName() + ":" + instructor.getName() + "," + instructor.getGender() + "," + instructor.getAge());
            print.println("," + instructor.getID());

        }

        // write courses
        for (Course course: school.getCourses()){

            // create a string of student IDs on the course separated by "-"
            StringBuilder students = new StringBuilder();
            String prefix = "";

            if(course.getStudents().length > 0) {
                for (Student student : course.getStudents()) {

                    students.append(prefix);
                    prefix = "-";
                    students.append(student.getID());

                }
            }else{
                students.append(0);
            }

            // prevent a NullPointerException if the course does not have an instructor
            Instructor instructor = course.getInstructor();
            int instructorID;

            if(instructor == null){
                instructorID = 0;
            }else{
                instructorID = instructor.getID();
            }

            print.println("course:" + course.getSubject().getID() + "," + course.getDaysUntilStart() + "," + course.getDaysToRun() + "," + instructorID + "," + students);

        }

    }
}
