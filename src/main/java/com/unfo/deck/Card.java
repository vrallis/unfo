package com.unfo.Deck;

public class Card {
    String color;
    String value;

    public Card(String color, String value) {
        this.color = color;
        this.value = value;
    }

    public String getColor() {
        return this.color;
    }

    public String getValue() {
        return this.value;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Card{" +
                "color='" + color + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}