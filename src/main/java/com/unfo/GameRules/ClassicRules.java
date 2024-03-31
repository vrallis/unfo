package com.unfo.GameRules;

import com.unfo.Logic.Game;
import com.unfo.Logic.Player;
import com.unfo.Logic.Table;
import java.util.List;

import com.unfo.GameMaster;
import com.unfo.Deck.Card;

public class ClassicRules implements Game.GameRules {
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

    @Override
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

    @Override
    public void executeCardAction(Card card, Game game) {
        Table table = game.getTable();
        if (card.getValue().equals("Reverse")) {
            game.reverseOrder();
        } else if (card.getValue().equals("Skip")) {
            game.skipPlayer();
        } else if (card.getValue().equals("Draw Two")) {
            game.drawCards(2);
            game.skipPlayer();
        } else if (card.getValue().equals("Wild Draw Four")) {
            game.drawCards(4);
            game.skipPlayer();
        } else if (card.getValue().equals("Wild")) {
            // The player chooses a color for the Wild card
            String chosenColor = GameMaster.chooseColor();
            card.setColor(chosenColor);
            table.addCard(card);
        } else {
            // If the card is a Number card, no special action is needed
        }
    }

}