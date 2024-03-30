package com.unfo;

import com.unfo.Deck.Card;
import com.unfo.Deck.DeckFactory;
import com.unfo.Logic.Game;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Game game = new Game();
        for (int i = 1; i < 8; i++) {
            Card drawnCard = game.drawCard();
            System.out.println("Drawn card: " + drawnCard);
        }
    }

}
