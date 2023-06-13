package com.company.figures.figure_impls;

import com.company.core.exceptions.OccupiedSquareException;
import com.company.game.Game;
import com.company.core.Position;
import com.company.core.exceptions.IllegalSquareException;
import com.company.figures.Figure;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Figure {

    public boolean enPassantable = false; // whether can be taken with En Passant
    public boolean enPassanter = false; // whether can take En Passant

    public Pawn(Position position, boolean isWhite) {
        super(position, isWhite);

        if(isWhite) {
            figureChar = '♟';
        }
        else {
            figureChar = '♙';
        }
    }

    /**
     * returns whether the promotion occurred successfully
     * */
    public boolean promote(Class<? extends Figure> newPieceType, Position newPosition) { // position is needed because promotion might happen with a capture
        if (newPieceType.equals(Pawn.class) || !this.possibleMoves().contains(newPosition)) {
            return false;
        }
        Game.board.removeFigure(this);
        Figure newPiece;
        try {
            newPiece = newPieceType.getConstructor(Position.class, Boolean.TYPE).newInstance(newPosition, this.isWhite);
            try {
                Figure takes = Game.board.getFigureByPosition(newPosition);
                if(takes != null) {
                    Game.board.removeFigure(takes);
                }
                Game.board.addFigure(newPiece);
            } catch (OccupiedSquareException e) { return false; }
            return true;
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            // NoSuchException by the getConstructor method, the rest by the newInstance method
            return false;
        }
    }

    @Override
    public List<Position> possibleMoves() {
        List<Position> possibleMoves = new ArrayList<>();

        if(isWhite) {
            if(enPassanter) {
                Pawn passantable1 = null;
                Pawn passantable2 = null;
                try {
                    passantable1 = (Pawn) Game.board.getFigureByPosition(new Position(position.x + 1, position.y));
                } catch (Exception ignore) {} // either ClassCast or IllegalSquare
                try {
                    passantable2 = (Pawn) Game.board.getFigureByPosition(new Position(position.x - 1, position.y));
                } catch (Exception ignore) {} // either ClassCast or IllegalSquare


                if(passantable1 != null && passantable1.enPassantable) {
                    possibleMoves.add(new Position(passantable1.position.x, position.y+1));
                }
                if(passantable2 != null && passantable2.enPassantable) {
                    possibleMoves.add(new Position(passantable2.position.x, position.y+1));
                }
            }

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
            if(enPassanter) {
                Pawn passantable1 = null;
                Pawn passantable2 = null;
                try {
                    passantable1 = (Pawn) Game.board.getFigureByPosition(new Position(position.x + 1, position.y));
                } catch (Exception ignore) {} // either ClassCast or IllegalSquare
                try {
                    passantable2 = (Pawn) Game.board.getFigureByPosition(new Position(position.x - 1, position.y));
                } catch (Exception ignore) {} // either ClassCast or IllegalSquare


                if(passantable1 != null && passantable1.enPassantable) {
                    possibleMoves.add(new Position(passantable1.position.x, position.y-1));
                }
                if(passantable2 != null && passantable2.enPassantable) {
                    possibleMoves.add(new Position(passantable2.position.x, position.y-1));
                }
            }

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
