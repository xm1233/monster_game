package domain.monster;

import domain.pokeMon;
import domain.skill;

import java.util.ArrayList;

public class fireDragon extends pokeMon {

    public fireDragon() {
        this.setHp(23);
        this.setLv(5);
        this.setName("fireDragon");
        this.setType("fire");
        this.setExp(0.0);
        this.setMaxExp(10);
        this.setMaxHp(23);
        ArrayList<skill> sk=new ArrayList<>();
        sk.add(new skill("fire","fire",4));
        sk.add(new skill("impact","fire",3));
        sk.add(new skill("shotFire","fire",6));
        sk.add(new skill("-","-",0));
        this.setSkills(sk);
    }
}
