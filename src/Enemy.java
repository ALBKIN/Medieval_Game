import java.io.Serializable;

public class Enemy implements Serializable {

    /* Class Variables */
    private static final long serialVersionUID = 1L;

    /* Instance Variables */
    private final String name;
    private double health;
    private double attackPoints;

    private Weapon currentWeapon;

       /* Constructors */
    public Enemy(String name, double health, double attackPoints) {
        this.name = name;
        this.health = health;
        this.attackPoints = attackPoints;
    }

    /* Instance Methods */
    public void takeDamage(double damage) {
        this.health -= damage;
        if (this.health <= 0) {
            System.out.println("Congratulations! You defeated the " + name + "!");
        }
    }

    /* Getters & Setters */
    public String getName() {
        return name;
    }

    public double getHealth() {
        return health;
    }

    public double getAttackPoints() {
        return attackPoints;
    }
}