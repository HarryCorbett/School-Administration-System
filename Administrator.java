import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class Administrator {

    /**
     * Get the command line arguments and use them accordningly
     * begin the simulation
     *
     * @param args contains config file [0] and days to run [1]
     */
    public static void main(String[] args) {

        // Get the configuration file
        File configFile = null;

        if (0 < args.length) {
            configFile = new File(args[0]);
        } else {
            System.out.println("Configuration file not found");
        }

        // Get the number of days to run
        int daysToRun = 0;

        if (1 < args.length) {
            daysToRun = Integer.parseInt(args[1]);
        } else {
            System.out.println("Please add the number of days you would like to run the simulation for");
        }

        if (configFile != null) {
            new Administrator(daysToRun, configFile);
        } else {
            System.out.println("Error finding file");
        }

    }

    /**
     * Read the config file and create all objects
     *
     * @param daysToRun  amount of days to run the simulation for
     * @param configFile file to read data from
     */
    private Administrator(int daysToRun, File configFile) {

        ConfigReader configReader = new ConfigReader(configFile.toString());

        School school = new School(configReader.getSchoolName());
        configReader.generateSchool(school);

        run(daysToRun, school);

    }

    /**
     * Run the simulation given the parameters
     *
     * @param daysToRun days to run the simulation for
     * @param school    the school to run the simulation on
     */
    private void run(int daysToRun, School school) {

        System.out.println("Day 0, (config file data): ");
        System.out.println(school.toString());

        for (int i = 0; i < daysToRun; i++) {

            System.out.println();
            System.out.println("Day: " + (i + 1));
            Administrator.run(school);

            System.out.println(school.toString());
        }

        // Allow the user to save the current state of the school
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to save the current state of the school? (Y/N)");
        String save = scanner.nextLine();

        if (save.equals("Y")) {

            System.out.println("Please enter the file name you would like to save it to (including .txt):");
            System.out.println(" - Saving to a file that already exists will overwrite its contents - ");
            scanner = new Scanner(System.in);
            String fileName = scanner.nextLine();

            SaveData.save(school, fileName);

            System.out.println("The current sate of the simulation has been saved");
        }

    }

    /**
     * Run each day if the simulation
     *
     * @param school the school to run the simulation on
     */
    private static void run(School school) {

        Random randomNum = new Random();

        // Create students if any join, either 0,1 or 2 will join per day
        for (int i = 1; i <= randomNum.nextInt(3); i++) {

            //read the values from the ArrayList returned by createPersonDetails
            ArrayList<String> studentInfo = new ArrayList<String>(createPersonDetails());
            String name = studentInfo.get(0);
            char gender = studentInfo.get(1).charAt(0);
            int age = Integer.parseInt(studentInfo.get(2));

            school.add(new Student(name, gender, age));
        }

        // Create an instructor if one is to join the school
        ArrayList<String> instructorInfo = new ArrayList<String>(createPersonDetails());
        String name = instructorInfo.get(0);
        char gender = instructorInfo.get(1).charAt(0);
        int age = Integer.parseInt(instructorInfo.get(2));

        int n = randomNum.nextInt(100);
        if (n < 20) { // if n is between 0 and 19 (inclusive) (20% chance)

            school.add(new Teacher(name, gender, age));
        }

        n = randomNum.nextInt(100);
        if (n < 10) {  // if n is less than 10 (10%)

            school.add(new Demonstrator(name, gender, age));
        }

        n = randomNum.nextInt(100);
        if (n < 5) {  // if n is less than 5 (5%)

            school.add(new OOTrainer(name, gender, age));
        }

        n = randomNum.nextInt(100);
        if (n < 5) {  // if n is between 35 and 39 (5%)

            school.add(new GUITrainer(name, gender, age));

        }


        // Allow a school day to pass
        school.aDayAtSchool();

        // If an instructor does not have a course to teach there is a 20% chance they leave
        for (int i = school.getInstructors().size() - 1; i >= 0; i--) {

            Instructor instructor = school.getInstructors().get(i);

            if (instructor.getAssignedCourse() == null && randomNum.nextInt(5) == 0) {
                school.remove(instructor);
            }
        }

        // remove students who have passed all subjects
        for (int i = school.getStudents().size() - 1; i >= 0; i--) {

            boolean removeStudent = true;
            Student student = school.getStudents().get(i);

            for (Subject subject : school.getSubjects()) {

                if (!student.hasCertificate(subject)) {  // if the student doesn't have the certificate for any subject, keep them in the school
                    removeStudent = false;
                }

            }

            // If the student isn't enrolled, there is a 5% chance they will leave
            if (!student.getEnrolled(school.getCourses())) {

                removeStudent = true;

            }

            if (removeStudent) {

                if (randomNum.nextInt(20) < 1) {
                    school.remove(student);
                }

            }


        }

    }

    /**
     * Creates values for the name, gender and age of a person
     * name is selected from possible options in a list depending on gender
     * gender is selected randomly (50% chance of each)
     * Age is selected randomly between 18 and 30
     *
     * @return an array list containing the information for a new person
     */
    private static ArrayList<String> createPersonDetails() {

        String gender;
        String name;

        // A list of possible student names
        ArrayList<String> maleNames = new ArrayList<String>(Arrays.asList("Liam", "Noah", "William", "James", "Oliver", "Ben", "Lucas", "Mason", "Logan", "Jacob"));
        ArrayList<String> femaleNames = new ArrayList<String>(Arrays.asList("Emma", "Olivia", "Ava", "Isabella", "Sophia", "Charlotte", "Mia", "Amelia", "Harper", "Evelyn"));
        Random randomNum = new Random();

        // select a random name from the list
        int n = randomNum.nextInt(2);  // get a random number, either 0 (female) or 1 (male)
        if (n == 0) {
            gender = "F";
            name = femaleNames.get(randomNum.nextInt(femaleNames.size() - 1)); //select a random female name
        } else {
            gender = "M";
            name = maleNames.get(randomNum.nextInt(maleNames.size() - 1)); // select a random male name
        }

        String age = (Integer.toString(randomNum.nextInt(13) + 18)); // generate a random age between 18 and 30

        return new ArrayList<String>(Arrays.asList(name, gender, age));

    }


}
