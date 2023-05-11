package domain.monster;

import domain.pokeMon;
import domain.skill;

import java.util.ArrayList;

public class mythicalFrogWeed extends pokeMon {

    public mythicalFrogWeed() {
        this.setHp(19);
        this.setLv(5);
        this.setName("mythicalFrogWeed");
        this.setType("grass");
        this.setExp(0.0);
        this.setMaxExp(10);
        this.setMaxHp(19);
        ArrayList<skill> sk=new ArrayList<>();
        sk.add(new skill("grass","grass",4));
        sk.add(new skill("impact","grass",3));
        sk.add(new skill("shotGrass","grass",6));
        sk.add(new skill("-","-",0));
        this.setSkills(sk);
    }
}
