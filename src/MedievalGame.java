import java.io.*;
import java.util.Scanner;
import java.util.Objects;

public class MedievalGame {

    /* Instance Variables */
    private Player player;

    /* Main Method */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        Scanner console = new Scanner(System.in);
        MedievalGame game = new MedievalGame();

        game.player = game.start(console);

        game.addDelay(500);
        System.out.println("\nLet's take a quick look at you to make sure you're ready to head out the door.");
        System.out.println(game.player);

        game.addDelay(1000);
        System.out.println("\nWell, you're off to a good start, let's get your game saved so we don't lose it.");
        game.save();

        game.addDelay(2000);
        System.out.println("We just saved your game...");
        System.out.println("Now we are going to try to load your character to make sure the save worked...");

        game.addDelay(1000);
        System.out.println("Deleting character...");
        String charName = game.player.getName();
        game.player = null;

        game.addDelay(1500);
        game.player = game.load(charName, console);
        System.out.println("Loading character...");

        game.addDelay(2000);
        System.out.println("Now let's print out your character again to make sure everything loaded:");

        game.addDelay(500);
        System.out.println(game.player);
    } // End of main

    /* Instance Methods */
    private Player start(Scanner console) {
        Player player = null;
        Art.homeScreen();
        System.out.println("Welcome in the Medieval Game, o brave Adventurer!");
        System.out.println("If you'd like to start a New Game type \"y\", otherwise " +
                "\n if you'd like to Load Game, type \"n\"");
        String answer = console.next().toLowerCase();
        while (!answer.equals("y") && !answer.equals("n")) {
            System.out.println("Incorrect choice. Please enter one of two choicesy. \"Y\" for New Game, \"N\" to Load Game.");
            answer = console.next().toLowerCase();
        }

        if (answer.equals("y")) {
            System.out.println("Starting New Game...\nEnter your NEW player name: ");
            String newPlayerName = console.next();
            if (playerNameExists(newPlayerName)) {
                System.out.println("Sorry, the player name \"" + newPlayerName + "\" is already taken. Please" +
                        "choose a different name.");
                return start(console);
            } else {
                player = new Player(newPlayerName);
            }

        } else if (answer.equals("n")) {
            System.out.println("Loading Game...\nEnter your EXISTING player name: ");
            player = load(console.next(), console);
        }
        return player;
    } // End of start

    private void save() {
        String fileName = player.getName() + ".svr";
        try {
            FileOutputStream userSaveFile = new FileOutputStream(fileName);
            ObjectOutputStream playerSaver = new ObjectOutputStream(userSaveFile);
            playerSaver.writeObject(this.player);
        } catch (IOException e) {
            System.out.println("There was an error saving your game, your file might not be available" +
                    "the next time you go to load a game." + e);
        }
    } // End of save

    private Player load(String playerName, Scanner console) {
        Player loadedPlayer;
        try {
            FileInputStream userLoadFile = new FileInputStream(playerName + ".svr");
            ObjectInputStream playerLoader = new ObjectInputStream(userLoadFile);
            loadedPlayer = (Player) playerLoader.readObject();
        } catch (IOException | ClassNotFoundException e) {
            /* TODO: Consider different handling of failed load character. Possibly scan for similar names and ask user
             *   to confirm or give chance to retry first */
            addDelay(1500);
            System.out.println("There was a problem loading your character. We've created new player with the name you entered");
            System.out.println("If you're sure the spelling is correct, your character may no longer exist");
            System.out.println("Please reload the game if you'd like to try again and correct possible spelling errors.");
            System.out.println("In the meantime - we created a new character with the name: " + playerName);
            addDelay(2000);
            loadedPlayer = new Player(playerName);
        }
        return loadedPlayer;
    } // End of load

    /*
    Adds a delay to the console so it seems like the computer is "thinking"
    or "responding" like a human, not instantly like a computer.
    */
    private void addDelay(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /*
    Implementation of a method to check if the playerName already exists in the game records.
    Later we can use different data structures or file storage to maintain player records
    For now, I assume the player name already exists if there's a .svr file with the same name*/
    private boolean playerNameExists(String playerName) {
        File playerFile = new File(playerName + ".svr");
        return playerFile.exists();
    }

    // TODO: Future functionality expansions:
    // TODO: 1. Enemies + combat system
    // TODO: 2. Shops / Taverns + interactions, buying, selling items
    // TODO: 3. 2D map drawn for players to help them navigate around
    // TODO: X. Encrypt the serialization so users can't change data in the saved files
}