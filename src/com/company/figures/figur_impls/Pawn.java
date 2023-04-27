package com.company.figures.figur_impls;

import com.company.core.Position;
import com.company.figures.Figure;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Figure {

    public Pawn(Position position, boolean isWhite) {
        super(position, isWhite);

        if(isWhite) {
            figureChar = '♟';
        }
        else {
            figureChar = '♙';
        }
    }

    @Override
    public List<Position> possibleMoves() {
        List<Position> possibleMoves = new ArrayList<>();

        if(isWhite) {
            if(position.y == 8) return possibleMoves;
            possibleMoves.add(new Position(position.x, position.y + 1));
            if(position.y == 2) {
                possibleMoves.add(new Position(position.x, position.y + 2));
            }
        } else {
            if(position.y == 1) return possibleMoves;
            possibleMoves.add(new Position(position.x, position.y - 1));
            if(position.y == 7) {
                possibleMoves.add(new Position(position.x, position.y - 2));
            }
        }

        removeOccupiedCells(possibleMoves);
        return possibleMoves;
    }

    @Override
    public List<Position> controlSquares() {
        List<Position> controlCells = new ArrayList<>();
        try{
            controlCells.add(new Position(position.x+1, position.y+1));
        } catch (IllegalArgumentException ignore) {}
        try{
            controlCells.add(new Position(position.x-1, position.y+1));
        } catch (IllegalArgumentException ignore) {}

        return controlCells;
    }
}
