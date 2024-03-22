package com.unfo;

import com.unfo.deck.DeckFactory;
import com.unfo.deck.Card;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        DeckFactory deckFactory = new DeckFactory();
        for (Card card : deckFactory.getDeck()) {
            System.out.println(card);
        }
    }

}
