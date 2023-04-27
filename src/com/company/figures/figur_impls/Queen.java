package com.company.figures.figur_impls;

import com.company.core.BoardLetters;
import com.company.core.Position;
import com.company.figures.Figure;

import java.util.ArrayList;
import java.util.List;

import static com.company.core.BoardLetters.D;

public class Queen extends Figure {


    public Queen(Position position, boolean isWhite) {
        super(position, isWhite);

        if(isWhite) {
            figureChar = '♛';
        }
        else {
            figureChar = '♕';
        }
    }

    @Override
    public List<Position> possibleMoves() {

        // straight moves
        Rook straightMoves = new Rook(this.position, this.isWhite);
        List<Position> possibleMoves = new ArrayList<>(straightMoves.possibleMoves());

        // diagonal moves
        Bishop diagonalMoves = new Bishop(this.position, this.isWhite);
        possibleMoves.addAll(diagonalMoves.possibleMoves());

        return possibleMoves;
    }
}
