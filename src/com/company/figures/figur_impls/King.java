package com.company.figures.figur_impls;

import com.company.Game;
import com.company.core.Position;
import com.company.core.exceptions.IllegalSquare;
import com.company.figures.Figure;

import java.util.ArrayList;
import java.util.List;

public class King extends Figure {

    public King(Position position, boolean isWhite) {
        super(position, isWhite);

        if(isWhite) {
            figureChar = '♚';
        }
        else {
            figureChar = '♔';
        }
    }

    @Override
    public List<Position> possibleMoves() {
        ArrayList<Position> possibleMoves = new ArrayList<>();
        try { // 1
            possibleMoves.add(new Position(position.x-1, position.y));
        } catch (IllegalSquare ignore) {}
        try { // 2
            possibleMoves.add(new Position(position.x-1, position.y-1));
        } catch (IllegalSquare ignore) {}
        try { // 3
            possibleMoves.add(new Position(position.x-1, position.y+1));
        } catch (IllegalSquare ignore) {}
        try { // 4
            possibleMoves.add(new Position(position.x+1, position.y));
        } catch (IllegalSquare ignore) {}
        try { // 5
            possibleMoves.add(new Position(position.x+1, position.y-1));
        } catch (IllegalSquare ignore) {}
        try { // 6
            possibleMoves.add(new Position(position.x+1, position.y+1));
        } catch (IllegalSquare ignore) {}
        try { // 7
            possibleMoves.add(new Position(position.x, position.y+1));
        } catch (IllegalSquare ignore) {}
        try { // 8
            possibleMoves.add(new Position(position.x, position.y-1));
        } catch (IllegalSquare ignore) {}

        this.removeOccupiedCells(possibleMoves);
        this.removeUnderCheckCells(possibleMoves);

        return possibleMoves;
    }


    protected void removeUnderCheckCells(List<Position> possibleMoves) {
        for(Figure f : Game.board.figures) {
            if(f.isWhite != this.isWhite)
            possibleMoves.removeAll(f.controlSquares());
        }
    }

    @Override
    public List<Position> controlSquares() {
        return possibleMoves();
    }
}
