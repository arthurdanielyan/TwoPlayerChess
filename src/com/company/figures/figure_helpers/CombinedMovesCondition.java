package com.company.figures.figure_helpers;


import com.company.figures.Figure;

/** Used to eliminate double code of possibleMoves() and controlSquares()*/
public interface CombinedMovesCondition {

    /** the condition that ignores opposite color King*/
    boolean isEnemyKing(Figure figure);
}
