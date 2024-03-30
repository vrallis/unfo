package com.unfo.Logic;

import com.unfo.Deck.Card;
import com.unfo.Deck.DeckFactory;
import java.util.Stack;

public class Table {
    private Stack<Card> drawPile;
    private Stack<Card> discardPile;

    public Table() {
        DeckFactory deckFactory = new DeckFactory();
        this.drawPile = new Stack<>();
        this.discardPile = new Stack<>();
        this.drawPile.addAll(deckFactory.getDeck());
    }

    public Card drawCard() {
        return this.drawPile.pop();
    }

    public void discardCard(Card card) {
        this.discardPile.push(card);
    }
}
