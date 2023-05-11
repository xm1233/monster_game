package domain;

import java.util.ArrayList;

public class pokeMon {
    private int hp;
    private String name;
    private int lv;
    private String type;
    private double exp;
    private double maxExp;
    public  int maxHp;
    private ArrayList<skill> skills=new ArrayList<>();


    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public pokeMon() {
    }

    public double getMaxExp() {
        return maxExp;
    }

    public void setMaxExp(double maxExp) {
        this.maxExp = maxExp;
    }

    public double getExp() {
        return exp;
    }

    public void setExp(double exp) {
        this.exp = exp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    public ArrayList<skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<skill> skills) {
        this.skills = skills;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
