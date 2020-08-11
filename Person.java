/**
 * Class for storing information of a person
 * Super class for Student and Instructor
 */
public class Person {

    private String name;
    private char gender;
    private int age;
    private int ID;
    private static int count = 1;

    /**
     * Constructor for the person class
     *
     * @param name   name of the person
     * @param gender gander of the person, either 'M' or 'F'
     * @param age    age of the person in years
     */
    public Person(String name, char gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.ID = setID();
    }

    /**
     * Constructor for the person class when given the ID
     * @oaram ID of the person to add
     */
    public Person(String name, char gender, int age, int ID) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.ID = ID;
        count += 1;
    }

    /**
     * get persons name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * get persons gender
     *
     * @return gender ('M' or 'F')
     */
    public char getGender() {
        return gender;
    }

    /**
     * set persons age
     *
     * @param age of person
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * get persons age
     *
     * @return age
     */
    public int getAge() {
        return age;
    }


    // Saving data to file so it can be run again
    /**
     * For each new person added give them a unique id
     *
     * @return ID for the new peroson
     */
    private int setID() {

        int ID = count;
        count += 1;

        return ID;
    }

    public int getID(){
        return ID;
    }
}

