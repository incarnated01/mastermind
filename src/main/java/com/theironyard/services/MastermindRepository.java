package com.theironyard.services;

import com.theironyard.entities.Game;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Joe on 1/20/17.
 */
public interface MastermindRepository extends CrudRepository<Game, Integer> {

}
