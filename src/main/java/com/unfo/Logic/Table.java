package com.unfo.logic;

import java.util.ArrayDeque;
import java.util.Deque;

import com.unfo.deck.Card;
import com.unfo.deck.DeckFactory;

public class Table {
    public Deque<Card> drawPile;
    public Deque<Card> playedPile;

    public Table() {
        DeckFactory deckFactory = new DeckFactory();
        this.drawPile = new ArrayDeque<>();
        this.playedPile = new ArrayDeque<>();
        this.drawPile.addAll(deckFactory.getDeck());
    }

    public Card drawCard() {
        if (this.drawPile.isEmpty()) {
            Card topCard = this.playedPile.pop();
            this.drawPile.addAll(this.playedPile);
            this.playedPile.clear();
            this.playedPile.push(topCard);
        }
        return this.drawPile.pop();
    }

    public void discardCard(Card card) {
        this.playedPile.push(card);
    }

    public Card getTopCard() {
        return this.playedPile.peek();
    }

    public void addCard(Card playerCard) {
        this.playedPile.push(playerCard);
    }
}
