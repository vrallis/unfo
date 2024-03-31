package com.unfo.Logic;

import com.unfo.Deck.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    private List<Card> hand;
    private String name;
    private int id;

    public Player(String name, int id) {
        this.hand = new ArrayList<>();
        this.name = name;
        this.id = id;
    }

    public void receiveCard(Card card) {
        this.hand.add(card);
    }

    public Card playCard(int index) {
        return this.hand.remove(index);
    }

    public List<Card> getHand() {
        return this.hand;
    }

    public int getHandSize() {
        return this.hand.size();
    }

    public List<Card> getPlayableCards(Game game) {
        Table table = game.getTable();
        Card topCard = table.getTopCard();
        //GameRules currentRules = game.getCurrentRules();

        List<Card> playableCards = new ArrayList<>();
        for (Card card : this.hand) {
            if (canPlay(card, topCard)) {
                playableCards.add(card);
            }
        }

        return playableCards;
    }

    public boolean canPlay(Card card, Card topCard) {
        if (card.getColor().equals(topCard.getColor()) || card.getValue().equals(topCard.getValue())
                || card.getColor().equals("Wild") || topCard.getColor().equals("Wild Draw Four")) {
            return true;
        } else {
            return false;
        }
    }

    public Card chooseCard(List<Card> playableCards, int index) {
        if (index >= 0 && index < playableCards.size()) {
            return playableCards.get(index);
        } else {
            throw new IllegalArgumentException("Invalid card index");
        }
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return id;
    }

    
}
