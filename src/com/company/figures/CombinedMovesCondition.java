package com.company.figures;


/** Used to eliminate double code of possibleMoves() and controlSquares()*/
public interface CombinedMovesCondition {

    /** the condition that ignores opposite color King*/
    boolean isEnemyKing(Figure figure);
}
