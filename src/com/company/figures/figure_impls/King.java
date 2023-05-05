package com.company.figures.figure_impls;

import com.company.Game;
import com.company.core.Position;
import com.company.core.exceptions.IllegalSquareException;
import com.company.figures.CombinedMovesCondition;
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

//    @Override
//    protected List<Position> combinedMoves(CombinedMovesCondition condition) {
//        return null;
//    }

    @Override
    public List<Position> possibleMoves() {
        ArrayList<Position> possibleMoves = new ArrayList<>();
        try { // 1
            possibleMoves.add(new Position(position.x-1, position.y));
        } catch (IllegalSquareException ignore) {}
        try { // 2
            possibleMoves.add(new Position(position.x-1, position.y-1));
        } catch (IllegalSquareException ignore) {}
        try { // 3
            possibleMoves.add(new Position(position.x-1, position.y+1));
        } catch (IllegalSquareException ignore) {}
        try { // 4
            possibleMoves.add(new Position(position.x+1, position.y));
        } catch (IllegalSquareException ignore) {}
        try { // 5
            possibleMoves.add(new Position(position.x+1, position.y-1));
        } catch (IllegalSquareException ignore) {}
        try { // 6
            possibleMoves.add(new Position(position.x+1, position.y+1));
        } catch (IllegalSquareException ignore) {}
        try { // 7
            possibleMoves.add(new Position(position.x, position.y+1));
        } catch (IllegalSquareException ignore) {}
        try { // 8
            possibleMoves.add(new Position(position.x, position.y-1));
        } catch (IllegalSquareException ignore) {}

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
