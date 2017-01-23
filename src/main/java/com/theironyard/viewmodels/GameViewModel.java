package com.theironyard.viewmodels;

import com.theironyard.entities.Game;

import java.util.List;

/**
 * Created by Joe on 1/23/17.
 */
public class GameViewModel {
    private static int STATIC_ID = 0;

    private List<Game> games;
    private int id;

    public GameViewModel() {
        id = STATIC_ID++;
    }

    public GameViewModel(List<Game> games) {
        this();
        this.games = games;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
