package com.company.figures.figur_impls;

import com.company.core.BoardLetters;
import com.company.core.Position;
import com.company.core.exceptions.IllegalSquare;
import com.company.figures.Figure;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Figure {


    public Knight(Position position, boolean isWhite) {
        super(position, isWhite);

        if(isWhite) {
            figureChar = '♞';
        }
        else {
            figureChar = '♘';
        }
    }

    @Override
    public List<Position> possibleMoves() {
        List<Position> possibleMoves = new ArrayList<>();


        try {
            possibleMoves.add(new Position(position.x + 1, position.y - 2));
            System.out.println(new Position(position.x + 1, position.y - 2));
        } catch (IllegalSquare ignored) {}
        try {
            possibleMoves.add(new Position(position.x - 1, position.y - 2));
        } catch (IllegalSquare ignored) {}
        try {
            possibleMoves.add(new Position(position.x + 1, position.y + 2));
        } catch (IllegalSquare ignored) {}
        try {
            possibleMoves.add(new Position(position.x - 1, position.y + 2));
        } catch (IllegalSquare ignored) {}
        try {
            possibleMoves.add(new Position(position.x + 2, position.y + 1));
        } catch (IllegalSquare ignored) {}
        try {
            possibleMoves.add(new Position(position.x + 2, position.y - 1));
        } catch (IllegalSquare ignored) {}
        try {
            possibleMoves.add(new Position(position.x - 2, position.y + 1));
        } catch (IllegalSquare ignored) {}
        try {
            possibleMoves.add(new Position(position.x - 2, position.y - 1));
        } catch (IllegalSquare ignored) {}

        this.removeOccupiedCells(possibleMoves);

        return possibleMoves;
    }
}
