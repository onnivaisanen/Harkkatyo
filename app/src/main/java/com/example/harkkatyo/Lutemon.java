package com.example.harkkatyo;

public class Lutemon {
    protected static int idCounter = 1;
    protected int id;
    protected String name;
    protected String color;
    protected int attack;
    protected int defense;
    protected int life;
    protected int maxLife;
    protected int experience;
    protected String location;
    private boolean isSelected;

    public Lutemon(String name, int maxLife) {
        this.id = idCounter++;
        this.name = name;
        this.maxLife = maxLife;
        this.life = maxLife;
        this.experience = 0;
        this.location = "Kotona";
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getAttack() {
        return attack + experience;
    }

    public int getDefense() {
        return defense;
    }

    public int getLife() {
        return life;
    }

    public int getId() {
        return id;
    }

    public int getMaxLife() {
        return maxLife;
    }

    public int getExperience() {
        return experience;
    }

    public void train() {
        experience++;
    }

    public void resetLife() {
        this.life = this.maxLife;
    }

    public boolean defend(int attackPower) {
        int damage = Math.max(0, attackPower - defense);
        life -= damage;
        if (life < 0) {
            life = 0;
        }
        return life > 0;
    }

    public int getImageResource() {
        switch (color) {
            case "Valkoinen":
                return R.drawable.lutemon_white;
            case "Musta":
                return R.drawable.lutemon_black;
            case "Vihreä":
                return R.drawable.lutemon_green;
            case "Oranssi":
                return R.drawable.lutemon_orange;
            case "Pinkki":
                return R.drawable.lutemon_pink;
            default:
                return R.drawable.default_lutemon; // jos haluat varakuvan
        }
    }
}
