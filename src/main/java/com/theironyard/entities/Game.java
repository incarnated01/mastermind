package com.theironyard.entities;

import javax.persistence.*;

@Entity
@Table(name = "MastermindGame")
public class Game {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
            int[] guesses;

    @Column(nullable = false)
            int[] checks;

    public Game() {
    }

    public Game(int id, int[] guesses, int[] checks) {
        this.id = id;
        this.guesses = guesses;
        this.checks = checks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int[] getGuesses() {
        return guesses;
    }

    public void setGuesses(int[] guesses) {
        this.guesses = guesses;
    }

    public int[] getChecks() {
        return checks;
    }

    public void setChecks(int[] checks) {
        this.checks = checks;
    }
}