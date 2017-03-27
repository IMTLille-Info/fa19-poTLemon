package potlemon.core.model;

import java.util.ArrayList;
import java.util.List;

public class Pokemon {

    private int id = 0;
    private String name;
    private int hp = 100;//hp at this moment
    private int hpMax = 100;//maximum hp when the pokemon is full life
    private int level = 89;
    private int defense = 12;
    private Type type = Type.AIR; //enum
    private List<Attack> attacks;
    private int xpBeforeLevel = 0;
    private int xp = 0;
    private boolean ejected=false;

    /**
     * Gives the pokemon an ID.
     * @param id
     * @param name
     */
    public Pokemon(int id, String name, int pv, int pvMax) {
        this(name, pv);
        this.id = id;
        this.hpMax = pvMax;
    }

    /**
     * Gives the pokemon an ID.
     * @param id
     * @param name
     * @param hpMax
     * @param defense
     * @param level
     * @param type
     * @param attacks
     */
    public Pokemon(int id, String name, int hpMax, int defense, int level, Type type, List<Attack> attacks) {
        this(name,hpMax,defense,level,type,attacks);

        this.id = id;
    }

    public Pokemon(String name, int pv) {
        this.name = name;
        this.hpMax = pv;
        this.hp = pv;
        this.level = 1;
        this.xpBeforeLevel = calculateXpBeforeLevel();
        this.xp = 0;
        this.defense = 0;
        this.attacks = new ArrayList<Attack>();
    }


    public Pokemon(String name, int hpMax, int defense, int level, Type type, List<Attack> attacks) {
        this.name = name;
        this.hpMax = hpMax;
        this.defense = defense;
        this.level = level;
        this.xpBeforeLevel = calculateXpBeforeLevel();
        this.xp = 0;
        this.type = type;
        this.hp = hpMax;
        this.attacks = attacks;
    }


    public int calculateXpBeforeLevel() {
        return (int) Math.pow(level, 3);
    }


    /**
     * Remove the amount of pv in parameter
     *
     * @param lost
     */
    public void lostPV(int lost) {
        hp -= lost;
        if (hp < 0) {
            hp = 0;
        }
    }

    /**
     * Remove the amount of pv in parameter
     *
     */
    public void addPV(int gain) {
        hp += gain;
        if (hp > hpMax) {
            hp = hpMax;
        }
    }

    public void heal() {
        this.hp = this.hpMax;
    }

    public int getHp() {
        return hp;
    }
    
    public boolean checkDead(){
    	return this.hp <= 0;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHpMax() {
        return hpMax;
    }

    public void setHpMax(int hpMax) {
        this.hpMax = hpMax;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        xpBeforeLevel = calculateXpBeforeLevel();
        xp = 0;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public float getLifePercentage(){
        return this.getHp()*1.0f/this.getHpMax()*1.0f;
    }

    public List<Attack> getAttacks() {
        return attacks;
    }

    public void setAttacks(Attack attack) {
        this.attacks.add(attack);
    }


    /**
     * Add amount to XP and check for level up
     *
     * @param amount
     */
    public void gainXp(int amount) {
        if (amount >= xpBeforeLevel - xp) {
            amount -= xpBeforeLevel - xp;
            level += 1;
            xpBeforeLevel = calculateXpBeforeLevel();
            xp = 0;
            gainXp(amount);
        } else {
            xp += amount;
        }
    }


    public int getXpBeforeLevel() {
        return xpBeforeLevel;
    }


    public int getXp() {
        return xp;
    }

    public int getId() {
        return id;
    }

    public boolean isEjected() {
        return ejected;
    }

    public void setEjected(boolean ejected) {
        this.ejected = ejected;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hp=" + hp +
                ", hpMax=" + hpMax +
                ", level=" + level +
                ", defense=" + defense +
                ", type=" + type +
                ", xpBeforeLevel=" + xpBeforeLevel +
                ", xp=" + xp +
                '}';
    }
}
