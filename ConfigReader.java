import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class ConfigReader {

    private BufferedReader reader;

    /**
     * Create a reader to read the given file
     *
     * @param configFile file to read
     */
    ConfigReader(String configFile) {

        try {
            this.reader = new BufferedReader(new FileReader(configFile));
        } catch (FileNotFoundException e) {
            System.out.println("Unable to find config file");
            this.reader = null;
        }
    }

    /**
     * read the next line of the file
     *
     * @return the next line as a string
     */
    private String getNextLine() {

        try {
            return reader.readLine();
        } catch (IOException e) {
            return "failed to read";
        }

    }

    /**
     * read the first line of the file return the name of the school to be created in Administrator
     *
     * @return name of the school
     */
    String getSchoolName() {

        String schoolName = null;

        try {

            String line = getNextLine();

            String[] splitData = line.split(":");

            if (splitData[0].equals("school")) {
                schoolName = splitData[1];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return schoolName;
    }

    /**
     * Read the rest of the file and create the required objects from the data
     */
    void generateSchool(School school) {

        while (fileIsReady()) {

            String line = getNextLine();

            String[] splitData = line.split(":");

            // Read the details of the subjects and create them
            if (splitData[0].equals("subject")) {

                String[] subjectDetails = splitData[1].split(",");

                String description = subjectDetails[0];
                int subjectID = Integer.parseInt(subjectDetails[1]);
                int specialisationID = Integer.parseInt(subjectDetails[2]);
                int duration = Integer.parseInt(subjectDetails[3]);

                Subject subject = new Subject(subjectID, specialisationID, duration);
                subject.setDescription(description);
                school.add(subject);

                // Read the data for courses and create them
            } else if (splitData[0].equals("course")) {

                String[] courseDetails = splitData[1].split(",");

                int subjectID = Integer.parseInt(courseDetails[0]);
                int daysUnitlStart = Integer.parseInt(courseDetails[1]);
                int daysToRun = Integer.parseInt(courseDetails[2]);
                int instructorID = Integer.parseInt(courseDetails[3]);
                String studentIDs = courseDetails[4];
                Subject subjectToAdd = null;
                Instructor instructorToAdd = null;

                // get the subject from its ID
                for (Subject subject : school.getSubjects()) {
                    if (subject.getID() == subjectID) {
                        subjectToAdd = subject;
                    }
                }

                // get the instructor from its ID
                for (Instructor instructor : school.getInstructors()) {
                    if (instructor.getID() == instructorID) {
                        instructorToAdd = instructor;
                    }
                }

                // create array list of students to add
                String[] splitStudentIDs = studentIDs.split("-");
                ArrayList<Student> students = new ArrayList<>();

                // Check that the ID is not 0 (no instructor)
                if (!splitStudentIDs[0].equals("0")) {

                    // get the students and add them to the array
                    for (String ID : splitStudentIDs) {
                        for (Student student : school.getStudents()) {
                            if (student.getID() == Integer.parseInt(ID)) {
                                students.add(student);
                            }
                        }
                    }
                }

                // create the course and add it to the school
                assert subjectToAdd != null;
                assert instructorToAdd != null;
                school.add(new Course(subjectToAdd, daysUnitlStart, daysToRun, instructorToAdd, students));


            } else  {

                try {
                    String[] personDetails = splitData[1].split(",");

                    boolean hasID = false;
                    int ID = 0;
                    String certificates = "";

                    String name = personDetails[0];
                    char gender = personDetails[1].charAt(0);
                    int age = Integer.parseInt(personDetails[2]);

                    if (personDetails.length == 5) {
                        certificates = personDetails[3];
                        ID = Integer.parseInt(personDetails[4]);
                        hasID = true;
                    }

                    switch (splitData[0]) {
                        case "student":

                            if (hasID) {
                                school.add(new Student(name, gender, age, certificates, ID));
                            } else {
                                school.add(new Student(name, gender, age));
                            }
                            break;
                        case "Teacher":

                            if (hasID) {
                                school.add(new Teacher(name, gender, age, ID));
                            } else {
                                school.add(new Teacher(name, gender, age));
                            }

                            break;
                        case "Demonstrator":

                            if (hasID) {
                                school.add(new Demonstrator(name, gender, age, ID));
                            } else {
                                school.add(new Demonstrator(name, gender, age));
                            }

                            break;
                        case "OOTrainer":

                            if (hasID) {
                                school.add(new OOTrainer(name, gender, age, ID));
                            } else {
                                school.add(new OOTrainer(name, gender, age));
                            }

                            break;
                        case "GUITrainer":

                            if (hasID) {
                                school.add(new GUITrainer(name, gender, age, ID));
                            } else {
                                school.add(new GUITrainer(name, gender, age));
                            }
                            break;
                    }

                    // If none of the checks for recognised information are successful, output this
                }catch(Exception e){
                    System.out.println("Error in line of configuration file");
                    System.out.println("unrecognised data, please ensure it describes a: school, subject, student, Teacher, Demonstrator, OOTrainer or GUITrainer");
                    System.out.println("this data will not be included in the simulation");
                    System.out.println();
                }
            }

        }

    }

    /**
     * Check that the file is ready to read
     *
     * @return true if ready
     */
    private Boolean fileIsReady() {
        try {
            return (reader.ready());
        } catch (IOException e) {
            return false;
        }
    }

}


