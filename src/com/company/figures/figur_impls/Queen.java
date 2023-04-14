package com.company.figures.figur_impls;

import com.company.core.Position;
import com.company.figures.Figure;

import java.util.ArrayList;
import java.util.List;

import static com.company.core.BoardLetters.D;

public class Queen extends Figure {


    public Queen(boolean isWhite) {
        super(isWhite);

        char figureChar;
        int y;

        if(isWhite) {
            figureChar = '♛';
            y = 1;
        }
        else {
            figureChar = '♕';
            y = 8;
        }

        init(figureChar, new Position(D, y));
    }

    @Override
    public List<Position> possibleMoves() {

        // straight moves
        Rook straightMoves = new Rook(this.isWhite);
        straightMoves.position = this.position;
        List<Position> possibleMoves = new ArrayList<>(straightMoves.possibleMoves());

        // diagonal moves
        Bishop diagonalMoves = new Bishop(this.isWhite);
        diagonalMoves.position = this.position;
        possibleMoves.addAll(diagonalMoves.possibleMoves());


        removeOccupiedCells(possibleMoves);
        return possibleMoves;
    }
}
