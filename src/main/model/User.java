package model;

// Represent a user with their age, body weight, height, gender and activity level
public class User {
    private final double age;
    private final double bodyWeight;
    private final double height;
    private final String gender;
    private final String activeLevel;

    /*
     * EFFECTS:  constructs new client information
     */
    public User(double age1, double bodyweight1, double height1, String gender1, String activeLevel1) {
        this.age = age1;
        this.bodyWeight = bodyweight1;
        this.height = height1;
        this.gender = gender1;
        this.activeLevel = activeLevel1;
    }

    /*
     * REQUIRES: client is a female
     * EFFECTS:  calculate client's metabolism based on their age, body weight, height and gender
     */
    public int calculateMetabolismFemale() {
        return (int) (655.1 + (4.35 * bodyWeight) + (4.7 * height) - (4.7 * age));
    }

    /*
     * REQUIRES: client is a male
     * EFFECTS:  calculate client's metabolism based on their age, body weight, height and gender
     */
    public int calculateMetabolismMale() {
        return (int) (66.47 + (6.24 * bodyWeight) + (12.7 * height) - (6.755 * age));
    }

    public double getAge() {
        return age;
    }

    public double getBodyWeight() {
        return bodyWeight;
    }

    public double getHeight() {
        return height;
    }


    public String getGender() {
        return gender;
    }

    public String getActiveLevel() {
        return activeLevel;
    }

}


