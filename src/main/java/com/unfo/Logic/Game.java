package com.unfo.logic;

import java.util.ArrayList;
import java.util.List;

import com.unfo.deck.Card;
import com.unfo.gamerules.ClassicRules;

public class Game {
    private GameRules currentRules;
    private Table table;
    private List<Player> players;
    private int currentPlayerIndex;
    private boolean reverseOrder;

    public interface GameRules {
        void executeInitialCardAction(Game game);

        void executeCardAction(Card card, Game game);

        boolean executeRules(Game game);
    }

    public Game(GameRules gameRules) {
        this.currentRules = gameRules;
        this.table = new Table();
        this.players = new ArrayList<>();
        this.currentPlayerIndex = 0;
        this.reverseOrder = false;
    }

    public void reverseOrder() {
        reverseOrder = !reverseOrder;
    }

    public void skipPlayer() {
        nextPlayer();
        nextPlayer();
    }

    public void drawCards(int numCards) {
        for (int i = 0; i < numCards; i++) {
            getCurrentPlayer().receiveCard(table.drawCard());
        }
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public void nextPlayer() {
        if (reverseOrder) {
            currentPlayerIndex--;
            if (currentPlayerIndex < 0) {
                currentPlayerIndex = players.size() - 1;
            }
        } else {
            currentPlayerIndex++;
            if (currentPlayerIndex >= players.size()) {
                currentPlayerIndex = 0;
            }
        }

    }

    public void StartGame() {
        if (currentRules instanceof ClassicRules) {
            ((ClassicRules) currentRules).distributeCards(this);
            System.out.println("Game started");
        }
    }

    public Table getTable() {
        return this.table;
    }

    public boolean getReverseOrder() {
        return this.reverseOrder;
    }

    public GameRules getCurrentRules() {
        return this.currentRules;
    }

    public boolean isGameOver() {
        for (Player player : players) {
            if (player.getHandSize() == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean executeRules() {
        boolean cardPlayed = currentRules.executeRules(this);
        if (cardPlayed) {
            nextPlayer();
        }
        return cardPlayed;
    }

    public void executeInitialCardAction() {
        currentRules.executeInitialCardAction(this);
    }
}