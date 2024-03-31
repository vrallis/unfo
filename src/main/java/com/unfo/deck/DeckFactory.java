package com.unfo.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckFactory {
    private List<Card> deck;

    public DeckFactory() {
        this.deck = new ArrayList<>();
        String[] colors = {"Red", "Yellow", "Green", "Blue"};
        String[] values = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] specialCards = {"Skip", "Reverse", "Draw Two"};

        for (String color : colors) {
            for (String value : values) {
                this.deck.add(new Card(color, value));
                if (!value.equals("0")) {
                    this.deck.add(new Card(color, value));
                }
            }

            // Add two of each special card for each color
            for (String special : specialCards) {
                this.deck.add(new Card(color, special));
                this.deck.add(new Card(color, special));
            }
        }

        // Add Wild and Wild Draw Four cards
        for (int i = 0; i < 4; i++) {
            this.deck.add(new Card("Wild", "Wild"));
            this.deck.add(new Card("Wild", "Wild Draw Four"));
        }

        // Shuffle the deck
        Collections.shuffle(this.deck);
    }

    public List<Card> getDeck() {
        return this.deck;
    }

    public static void main(String[] args) {
        DeckFactory factory = new DeckFactory();
        List<Card> deck = factory.getDeck();
        for (Card card : deck) {
            System.out.println(card);
        }
    }
}
