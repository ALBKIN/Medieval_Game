import java.util.Scanner;

public class Combat {
    public static void performCombat(Player player, Enemy enemy, Scanner console) {
        System.out.println("A wild " + enemy.getName() + " has appeared!");

        while (player.getHealth() > 0 && enemy.getHealth() > 0) {
            System.out.println("\nPlayer HP: " + player.getHealth());
            System.out.println((enemy.getName() + " HP: " + enemy.getHealth()));
            System.out.println("\nWhat will you do?");
            System.out.println("1. Attack");
            System.out.println("2. Run");
            int choice = console.nextInt();

            if (choice == 1) {
                double playerDamage = player.getAttackPoints();
                double enemyDamage = enemy.getAttackPoints();

                System.out.println("You attack the " + enemy.getName() + " for " + playerDamage + " damage!");
                enemy.takeDamage(playerDamage);

                if (enemy.getHealth() <= 0) {
                    // TODO: Enemy defeated, do something or reward the player
                    break;
                }

                System.out.println("The " + enemy.getName() + " attacks you for " + enemyDamage + " damage!");
                player.takeDamage(enemyDamage);

                if (player.getHealth() <= 0) {
                    System.out.println("You have been defeated by the " + enemy.getName() + ". Game Over.");
                    System.exit(0);
                }
            } else if (choice == 2) {
                System.out.println("You run away from the " + enemy.getName() + ".");
                break;
            } else {
                System.out.println("Invalid choice. Please choose again.");
            }
        }
    }
}
