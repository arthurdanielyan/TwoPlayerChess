package com.company.figures.figure_impls;

import com.company.core.Position;
import com.company.figures.Figure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    @Override
    public List<Position> controlSquares() {
        // straight moves
        Rook straightMoves = new Rook(this.position, this.isWhite);
        List<Position> possibleMoves = new ArrayList<>(straightMoves.controlSquares());

        // diagonal moves
        Bishop diagonalMoves = new Bishop(this.position, this.isWhite);
        possibleMoves.addAll(diagonalMoves.controlSquares());

        return possibleMoves;
    }
}
