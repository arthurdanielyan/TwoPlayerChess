package com.company.figures.figure_impls;

import com.company.core.Position;
import com.company.core.exceptions.IllegalSquareException;
import com.company.figures.Figure;

import java.util.ArrayList;
import java.util.Collections;
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

        if(isPinned() != null) {
            return Collections.emptyList();
        }

        List<Position> possibleMoves = new ArrayList<>();

        try {
            possibleMoves.add(new Position(position.x + 1, position.y - 2));
            System.out.println(new Position(position.x + 1, position.y - 2));
        } catch (IllegalSquareException ignored) {}
        try {
            possibleMoves.add(new Position(position.x - 1, position.y - 2));
        } catch (IllegalSquareException ignored) {}
        try {
            possibleMoves.add(new Position(position.x + 1, position.y + 2));
        } catch (IllegalSquareException ignored) {}
        try {
            possibleMoves.add(new Position(position.x - 1, position.y + 2));
        } catch (IllegalSquareException ignored) {}
        try {
            possibleMoves.add(new Position(position.x + 2, position.y + 1));
        } catch (IllegalSquareException ignored) {}
        try {
            possibleMoves.add(new Position(position.x + 2, position.y - 1));
        } catch (IllegalSquareException ignored) {}
        try {
            possibleMoves.add(new Position(position.x - 2, position.y + 1));
        } catch (IllegalSquareException ignored) {}
        try {
            possibleMoves.add(new Position(position.x - 2, position.y - 1));
        } catch (IllegalSquareException ignored) {}

        this.removeOccupiedCells(possibleMoves);

        return possibleMoves;
    }

    @Override
    public List<Position> controlSquares() {
        return this.possibleMoves();
    }
}
