/**
 * Class for storing information about subjects
 * Each Course object contains one Subject object
 */
public class Subject {

    private int id;
    private int specialism;
    private int duration;
    private String description;

    /**
     * Constructor for Subjects
     * @param id - unique id of the subject
     * @param specialism - specialism id of the subject
     * @param duration - number of days required to to complete the course on the subject
     */
    public Subject(int id, int specialism, int duration) {
        this.id = id;
        this.specialism = specialism;
        this.duration = duration;
    }

    /**
     * get subject id
     * @return id
     */
    public int getID() {
        return id;
    }

    /**
     * get specialism id
     * @return specialism
     */
    public int getSpecialism() {
        return specialism;
    }

    /**
     * get duration of the subject
     * @return duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Mutator method for a subjects description
     * @param description a brief description of the subject
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * get the description of the subject
     * @return description
     */
    public String getDescription() {
        return description;
    }
}
