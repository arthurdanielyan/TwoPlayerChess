package com.company.figures.figure_impls;

import com.company.Game;
import com.company.core.Position;
import com.company.core.exceptions.IllegalSquareException;
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
        List<Position> possibleMoves = combinedMoves();

        this.removeOccupiedCells(possibleMoves);
        this.removeUnderCheckCells(possibleMoves);

        return possibleMoves;
    }


    protected void removeUnderCheckCells(List<Position> possibleMoves) {
        /*
            The copy of Game.board.figures is needed because combinedMoves(),
            which is needed to reduce duplicate code for possibleMoves() and
            controlSquares(), calls isPinned() method, which determines if the
            piece is pinned by removing it from the figures list, but the piece
            that is pinned can also block the King from coming to a specific
            square. And the other reason of copying the list is that otherwise it
            would throw ConcurrentModificationException because Game.board.figures
            would be modified by the isPinned() method while here a for-each loop is
            iterating over it.
         */
        var figuresListCopy = new ArrayList<>(Game.board.figures);
        for(Figure f : figuresListCopy) {
            if(f.isWhite != this.isWhite) {
                possibleMoves.removeAll(f.controlSquares());
            }
        }
    }

    @Override
    public List<Position> controlSquares() {
        return combinedMoves();
    }

    private List<Position> combinedMoves() {
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
        return possibleMoves;
    }
}
