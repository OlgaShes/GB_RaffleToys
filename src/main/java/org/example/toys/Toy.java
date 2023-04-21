package org.example.toys;

public class Toy {
    private String toyName;
    private int quantity;
    private int chance;


    protected Toy(String toyName, int quantity, int chance) {
        this.toyName = toyName;
        this.quantity = quantity;
        this.chance = chance;
    }

    protected Toy() {
        this("", 0,0);
    }

    protected void setToyName(String toyName) {
        this.toyName = toyName;
    }

    protected void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    protected void setChance(int chance) {
        this.chance = chance;
    }

    protected String getToyName() {
        return toyName;
    }

    protected int getQuantity() {
        return quantity;
    }

    protected int getChance() {
        return chance;
    }

    @Override
    public String toString() {
        return  toyName + ", " +
                quantity + " шт, вероятность "+
                chance + "%";
    }
}
