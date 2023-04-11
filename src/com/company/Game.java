package com.company;

import com.company.core.BoardLetters;
import com.company.core.Position;
import com.company.figures.Figure;
import com.company.figures.figur_impls.King;
import com.company.figures.figur_impls.Queen;

import java.util.ArrayList;
import java.util.List;

public class Game {

//    public static int[][] board = {
//            {0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0}
//    };

    public static List<Figure> board = new ArrayList<>();
    static {
        Queen queen = new Queen(true);
        queen.position = new Position(BoardLetters.B.num, 3);

        board.add(queen);
    }
}
