package com.unfo;

import com.unfo.GameRules.ClassicRules;
import com.unfo.Logic.Game;
import com.unfo.Logic.Player;
import com.unfo.Deck.Card;

import java.util.List;
import java.util.Scanner;


public class GameMaster {

    private static Scanner scanner = new Scanner(System.in); // Global scanner

    public static void main(String[] args) {
        System.out.println("Welcome to Unfo. I am the GameMaster and I will be handling today's game.");

        System.out.print("Enter the number of players (2-8): ");
        int numPlayers = scanner.nextInt();
        // Consume newline left-over
        scanner.nextLine();

        playGame(numPlayers);

        scanner.close(); // Close scanner once at the end
    }

    public static void playGame(int numPlayers) {
        Game game = new Game(new ClassicRules());
        for (int i = 0; i < numPlayers; i++) {
            game.addPlayer(new Player("Player " + i, i));
        }
        

        game.StartGame();
        game.executeInitialCardAction();
        

        while (!game.isGameOver()) {
            Player currentPlayer = game.getCurrentPlayer();
            System.out.println("Current player: " + currentPlayer.getName());

            List<Card> hand = currentPlayer.getHand();
            System.out.println("Your hand: " + hand);

            List<Card> playableCards = currentPlayer.getPlayableCards(game);
            System.out.println("Playable cards: " + playableCards);

            System.out.println("Top card: " + game.getTable().getTopCard());

            if (!playableCards.isEmpty()) {
                Card chosenCard = chooseCard(playableCards);
                currentPlayer.chooseCard(playableCards, playableCards.indexOf(chosenCard));
                game.getTable().addCard(chosenCard);
                System.out.println(currentPlayer.getName() + " played " + chosenCard);
                if (chosenCard.getColor().equals("Wild") || chosenCard.getColor().equals("Wild Draw Four")) {
                    String chosenColor = chooseColor();
                    chosenCard.setColor(chosenColor);
                }
                if (currentPlayer.getHandSize() == 0) {
                    System.out.println(currentPlayer.getName() + " has won!");
                    //game.setGameOver(true);
                    break;
                }
            } else {
                System.out.println("You don't have any playable cards. Do you want to draw a card? (yes/no)");
                String response = scanner.nextLine();
                if (response.equalsIgnoreCase("yes")) {
                    Card drawnCard = game.getTable().drawCard();
                    currentPlayer.receiveCard(drawnCard);
                    System.out.println("You drew a card: " + drawnCard);
                    System.out.println(currentPlayer.getName() + " drew a card.");
                } else {
                    System.out.println(currentPlayer.getName() + " passed their turn.");
                }
            }

            game.nextPlayer(); // This method should advance to the next player in the order of play
        }
    }

    public static Card chooseCard(List<Card> playableCards) {
        System.out.println("Choose a card to play:");
        for (int i = 0; i < playableCards.size(); i++) {
            System.out.println(i + ": " + playableCards.get(i));
        }
        int index = scanner.nextInt();
        // Consume newline left-over
        scanner.nextLine();

        return playableCards.get(index);
    }

    public static String chooseColor() {
        System.out.println("Choose a color (Red, Yellow, Green, Blue):");
        String color = scanner.nextLine();
        return color;
    }
}

