package domain.monster;

import domain.pokeMon;
import domain.skill;

import java.util.ArrayList;

public class jennyTurtle extends pokeMon {

    public jennyTurtle() {
        this.setHp(20);
        this.setLv(5);
        this.setName("jennyTurtle");
        this.setType("water");
        this.setExp(0.0);
        this.setMaxExp(10);
        this.setMaxHp(20);
        ArrayList<skill> sk=new ArrayList<>();
        sk.add(new skill("water","water",4));
        sk.add(new skill("impact","water",3));
        sk.add(new skill("shotWater","water",6));
        sk.add(new skill("-","-",0));
        this.setSkills(sk);
    }
}
