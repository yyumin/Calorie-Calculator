package model;

// Represents food having a name, calories, total carbs, total fat and total protein it contains.
public class FoodIntake {
    private final String name;  //food name
    private final int cal;      //food calories
    private final int carbs;    //food carbs
    private final int fat;      //food fat
    private final int protein;  //food proteins

    /*
     * REQUIRES: cal,carbs,fat and protein >= 0;
     * EFFECTS:  constructs an instance of FoodIntake.
     *           food name is set to name; cal,carbs,fat and protein is non-negative integers.
     */
    public FoodIntake(String name, int cal, int carbs, int fat, int protein) {
        this.name = name;
        this.cal = cal;
        this.carbs = carbs;
        this.fat = fat;
        this.protein = protein;
    }


    public int getCal() {
        return this.cal;
    }

    public String getName() {
        return this.name;
    }

    public int getCarbs() {
        return this.carbs;
    }

    public int getFat() {
        return this.fat;
    }

    public int getProtein() {
        return this.protein;
    }


}

