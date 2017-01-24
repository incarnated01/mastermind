package com.theironyard.Controllers;

import com.theironyard.entities.Game;
import com.theironyard.services.MastermindRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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

        int[] results = new int[answerArray.length];

        for (int i = 0;i < results.length;i++) {
            if (answerArray[i] == guessArray[i]) {
                results[i] = 2;
                answerArray[i] = 0;
            }
        }

        for (int i = 0;i < results.length;i++) {
            int actualIndex = findIndexOfValue(answerArray, guessArray[i]);

            if (answerArray[i] > 0 && actualIndex > -1) {
                results[i] = 1;
                answerArray[actualIndex] = 0;
            }
        }

        return results;
    }


    private static int findIndexOfValue(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }

        return -1;
    }

    @CrossOrigin
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public Game home() {
        init();
        return games.findOne(1);
    }

    @CrossOrigin
    @RequestMapping(path = "/guess", method = RequestMethod.POST)
    public List<Game> guessCheck(@RequestBody int [] guess) {
        Game NewAnswer = games.findOne(1);
        Game NewGuess = new Game();
        int [] response = check(NewAnswer.getGuesses(), guess);
        NewGuess.setGuesses(guess);
        NewGuess.setChecks(response);
        games.save(NewGuess);
        return (List)games;
    }

    @CrossOrigin
    @RequestMapping(path = "/new-game", method = RequestMethod.GET)
    void newGame(HttpServletResponse response) throws IOException {
        games.deleteAll();
        response.sendRedirect("https://hidden-sands-13571.herokuapp.com/");
    }

}
