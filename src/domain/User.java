package domain;

import domain.monster.fireDragon;

import java.util.ArrayList;

public class User {
    private int i;
    private int j;
    private ArrayList<pokeMon> pokeMons=new ArrayList<>();

    public User(int i, int j) {
        this.i = i;
        this.j = j;
        this.pokeMons.add(new fireDragon());
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public ArrayList<pokeMon> getPokeMons() {
        return pokeMons;
    }

    public void setPokeMons(ArrayList<pokeMon> pokeMons) {
        this.pokeMons = pokeMons;
    }
}
