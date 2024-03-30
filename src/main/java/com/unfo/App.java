package com.unfo;

import com.unfo.Deck.Card;
import com.unfo.Deck.DeckFactory;
import com.unfo.Logic.Table;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Table game = new Table();
        for (int i = 1; i < 8; i++) {
            Card drawnCard = game.drawCard();
            System.out.println("Drawn card: " + drawnCard);
        }
    }

}
