package com.company;

import com.company.core.exceptions.OccupiedSquareException;
import com.company.game.Game;

public class Main {

    public static void main(String[] args) throws OccupiedSquareException {
        Game.start();
    }
}
