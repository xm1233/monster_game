package domain;

public class skill {
    private String skillName;
    private String skillType;
    private int skillAtk;

    public skill(String skillName, String skillType, int skillAtk) {
        this.skillName = skillName;
        this.skillType = skillType;
        this.skillAtk = skillAtk;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillType() {
        return skillType;
    }

    public void setSkillType(String skillType) {
        this.skillType = skillType;
    }

    public int getSkillAtk() {
        return skillAtk;
    }

    public void setSkillAtk(int skillAtk) {
        this.skillAtk = skillAtk;
    }
}
