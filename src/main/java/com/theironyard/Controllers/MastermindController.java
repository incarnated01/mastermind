package com.theironyard.Controllers;

import com.theironyard.entities.Game;
import com.theironyard.services.MastermindRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@RestController
public class MastermindController {

    @Autowired
    MastermindRepository games;

    @PostConstruct
    public void init() {
        if(games.count() == 0) {
            Game game = new Game();
            game.setGuesses(new int[]{numberGenerator(), numberGenerator(), numberGenerator(), numberGenerator()});
            game.setChecks(new int[]{0,0,0,0});
            games.save(game);
        }
    }

    public static int numberGenerator() {
        return (int)(Math.random() * 8) + 1;
    }


    public static int[] check(int[] answerArray, int[] guessArray) {
        answerArray = Arrays.copyOf(answerArray, answerArray.length);

        int[] result = new int[answerArray.length];

        for (int i = 0; i < result.length; i++) {
            if (answerArray[i] == guessArray[i]) {
                result[i] = 2;
                answerArray[i] = 0;
                continue;
            }

            int answerIndex = Arrays.binarySearch(answerArray, guessArray[i]);

            if(answerIndex > -1) {
                result[i] = 1;
                answerArray[answerIndex] = 0;
            }
        }
        return result;
    }

    @CrossOrigin
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public Game home() {
        init();
        return games.findOne(1);
    }

    @CrossOrigin
    @RequestMapping(path = "/guess", method = RequestMethod.POST)
    public Iterable<Game> guessCheck(@RequestBody int [] guess) {
        Game NewAnswer = games.findOne(1);
        Game NewGuess = new Game();
        int [] response = check(NewAnswer.getGuesses(), guess);
        NewGuess.setGuesses(guess);
        NewGuess.setChecks(response);
        games.save(NewGuess);
        return games.findAll();
    }

    @CrossOrigin
    @RequestMapping(path = "/new-game", method = RequestMethod.GET)
    void newGame(HttpServletResponse response) throws IOException {
        games.deleteAll();
        response.sendRedirect("http://localhost:8080");
    }

}
