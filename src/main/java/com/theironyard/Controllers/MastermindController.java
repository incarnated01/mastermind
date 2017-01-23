package com.theironyard.Controllers;

import com.theironyard.entities.Game;
import com.theironyard.services.MastermindRepository;
import com.theironyard.viewmodels.GameViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MastermindController {

    @Autowired
    MastermindRepository games;

    @CrossOrigin
    @RequestMapping(path="/", method = RequestMethod.POST)
    public GameViewModel homePage(@RequestBody Game newGame) {
        games.save(newGame);
        return new GameViewModel((List)games.findAll());
    }
}
//we generate 4 random numbers
//front-end picks colors 1-8 so we have an array on the back-end of just numbers

//algorithim

//copy answer to answerCopy variable
//for each element in guess:

// put the below in a method
//  for each element in guess:
//      if(current in answer == curent in guess)
//            exact++;
//break;
//else if is a match
// set answerCopy at the index to
//  color++;