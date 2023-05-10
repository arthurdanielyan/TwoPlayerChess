package com.company.figures.figure_impls;

import com.company.Game;
import com.company.core.Position;
import com.company.core.exceptions.IllegalSquareException;
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
            try {
                Figure rightTake = Game.board.getFigureByPosition(new Position(position.x + 1, position.y + 1));
                if(rightTake != null) {
                    possibleMoves.add(rightTake.position);
                }
            } catch (IllegalSquareException ignore) {}
            try {
                Figure leftTake = Game.board.getFigureByPosition(new Position(position.x - 1, position.y + 1));
                if(leftTake != null) {
                    possibleMoves.add(leftTake.position);
                }
            } catch (IllegalSquareException ignore) {}
        } else {
            if(position.y == 1) return possibleMoves;
            possibleMoves.add(new Position(position.x, position.y - 1));
            if(position.y == 7) {
                possibleMoves.add(new Position(position.x, position.y - 2));
            }
            try {
                Figure rightTake = Game.board.getFigureByPosition(new Position(position.x + 1, position.y - 1));
                if(rightTake != null) {
                    possibleMoves.add(rightTake.position);
                }
            } catch (IllegalSquareException ignore) {}
            try {
                Figure leftTake = Game.board.getFigureByPosition(new Position(position.x - 1, position.y - 1));
                if(leftTake != null) {
                    possibleMoves.add(leftTake.position);
                }
            } catch (IllegalSquareException ignore) {}
        }


        if(isPinned() != null) {
            Figure pinner = isPinned();
            if(isWhite) {
                if (pinner.position.y > this.position.y && (pinner instanceof Rook || pinner instanceof Queen)) {
                    possibleMoves.removeAll(controlSquares()); // if the pinner is above this then this can't take
                } else {
                    if(pinner.position.x > this.position.x) { // if the pinner is at the right then this can't take to left and also can't move
                        possibleMoves.remove(new Position(position.x, position.y + 1));
                        try {
                            possibleMoves.remove(new Position(position.x - 1, position.y + 1));
                        } catch (IllegalSquareException ignore) {}
                    } else {
                        possibleMoves.remove(new Position(position.x, position.y + 1));
                        try {
                            possibleMoves.remove(new Position(position.x + 1, position.y + 1));
                        } catch (IllegalSquareException ignore) {}
                    }
                }
            } else {
                if (pinner.position.y < this.position.y && (pinner instanceof Rook || pinner instanceof Queen)) {
                    possibleMoves.removeAll(controlSquares()); // if the pinner is below this then this can't take
                } else {
                    if(pinner.position.x > this.position.x) { // if the pinner is at the right then this can't take to left and can't move either
                        possibleMoves.remove(new Position(position.x, position.y - 1));
                        try {
                            possibleMoves.remove(new Position(position.x - 1, position.y - 1));
                        } catch (IllegalSquareException ignore) {}
                    } else {
                        possibleMoves.remove(new Position(position.x, position.y - 1));
                        try {
                            possibleMoves.remove(new Position(position.x + 1, position.y - 1));
                        } catch (IllegalSquareException ignore) {}
                    }
                }
            }
        }

        removeOccupiedCells(possibleMoves);
        return possibleMoves;
    }

    @Override
    public List<Position> controlSquares() {
        List<Position> controlCells = new ArrayList<>();
        if(isWhite) {
            try {
                controlCells.add(new Position(position.x + 1, position.y + 1));
            } catch (IllegalSquareException ignore) {
            }
            try {
                controlCells.add(new Position(position.x - 1, position.y + 1));
            } catch (IllegalSquareException ignore) {
            }
        } else {
            try {
                controlCells.add(new Position(position.x + 1, position.y - 1));
            } catch (IllegalSquareException ignore) {
            }
            try {
                controlCells.add(new Position(position.x - 1, position.y - 1));
            } catch (IllegalSquareException ignore) {}
        }

        return controlCells;
    }
}
