package com.unfo.gamerules;

import java.util.List;

import com.unfo.GameMaster;
import com.unfo.deck.Card;
import com.unfo.logic.Game;
import com.unfo.logic.Player;
import com.unfo.logic.Table;
import com.unfo.GameMaster;

public class ClassicRules implements Game.GameRules {
    private List<Player> players;
    @Override
    public boolean executeRules(Game game) {
        Table table = game.getTable();
        Player player = game.getCurrentPlayer();
        //Card topCard = table.getTopCard();
    
        List<Card> playableCards = player.getPlayableCards(game);
        Card chosenCard = GameMaster.chooseCard(playableCards); 
    
        if (chosenCard != null) {
            table.addCard(chosenCard);
            executeCardAction(chosenCard, game);
            return true;
        } else {
            return false;
        }
    }

    public void distributeCards(Game game) {
        List<Player> players = game.getPlayers();
        for (Player player : players) {
            for (int i = 0; i < 7; i++) {
                player.receiveCard(game.getTable().drawPile.pop());
            }
        }
    }

    public void executeInitialCardAction(Game game) {
        Table table = game.getTable();
        Card firstCard = table.drawPile.pop();
        while (firstCard.getColor().equals("Wild") || firstCard.getValue().equals("Wild Draw Four")
                || firstCard.getValue().equals("Draw Two") || firstCard.getValue().equals("Reverse")
                || firstCard.getValue().equals("Skip")) {
                    table.drawPile.add(firstCard);
            firstCard = table.drawPile.pop();
        }
        table.playedPile.push(firstCard);
    }

    public void executeCardAction(Card card, Game game) {
        Table table = game.getTable();
        if (card.getValue().equals("Reverse")) {
            table.addCard(card);
            game.reverseOrder();
            game.skipPlayer();
        } else if (card.getValue().equals("Skip")) {
            table.addCard(card);
            game.skipPlayer();
        } else if (card.getValue().equals("Draw Two")) {
            table.addCard(card);
            game.drawCards(2);
            game.skipPlayer();
        } else if (card.getValue().equals("Wild Draw Four")) {
            table.addCard(card);
            game.drawCards(4);
            game.skipPlayer();
        } else if (card.getValue().equals("Wild")) {
            // The player chooses a color for the Wild card
            String chosenColor = GameMaster.chooseColor();
            card.setColor(chosenColor);
            table.addCard(card);
        } else {
            table.addCard(card);
        }
    }

}