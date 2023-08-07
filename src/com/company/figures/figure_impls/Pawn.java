package com.company.figures.figure_impls;

import com.company.core.Position;
import com.company.core.exceptions.IllegalMoveException;
import com.company.core.exceptions.IllegalSquareException;
import com.company.core.exceptions.OccupiedSquareException;
import com.company.core.move_information_wrappers.Move;
import com.company.figures.Figure;
import com.company.game.Game;

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

    @Override
    public Move move(Position newPosition, Class<? extends Figure> promoteTo) {
        if (!this.possibleMoves().contains(newPosition)) {
            throw new IllegalMoveException(this + " cannot move to " + newPosition);
        }
        if(promoteTo.equals(Pawn.class)) {
            throw new IllegalMoveException(this + " tries to promote to a Pawn");
        }
        Game.board.removeFigure(this);
        Figure newPiece;
        try {
            newPiece = promoteTo.getConstructor(Position.class, Boolean.TYPE).newInstance(newPosition, this.isWhite);
            Figure capture;
            try {
                capture = Game.board.findFigureByPosition(newPosition);
                if(capture != null) {
                    Game.board.removeFigure(capture);
                }
                Game.board.addFigure(newPiece);
            } catch (OccupiedSquareException e) {
                throw new IllegalMoveException("");
            }
            Move move = new Move(position, newPosition, this, capture, null, newPiece);
            Game.board.onMove(move);
            return move;
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            // NoSuchException by the getConstructor method, the rest by the newInstance method
            e.printStackTrace();
            throw new IllegalMoveException("");
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
                    passantable1 = (Pawn) Game.board.findFigureByPosition(new Position(position.x + 1, position.y));
                } catch (Exception ignore) {} // either ClassCast or IllegalSquare
                try {
                    passantable2 = (Pawn) Game.board.findFigureByPosition(new Position(position.x - 1, position.y));
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
                if(Game.board.findFigureByPosition(new Position(position.x, position.y + 2)) == null)
                possibleMoves.add(new Position(position.x, position.y + 2));
            }
            try {
                Figure rightTake = Game.board.findFigureByPosition(new Position(position.x + 1, position.y + 1));
                if(rightTake != null) {
                    possibleMoves.add(rightTake.position);
                }
            } catch (IllegalSquareException ignore) {}
            try {
                Figure leftTake = Game.board.findFigureByPosition(new Position(position.x - 1, position.y + 1));
                if(leftTake != null) {
                    possibleMoves.add(leftTake.position);
                }
            } catch (IllegalSquareException ignore) {}
        } else {
            if(enPassanter) {
                Pawn passantable1 = null;
                Pawn passantable2 = null;
                try {
                    passantable1 = (Pawn) Game.board.findFigureByPosition(new Position(position.x + 1, position.y));
                } catch (Exception ignore) {} // either ClassCast or IllegalSquare
                try {
                    passantable2 = (Pawn) Game.board.findFigureByPosition(new Position(position.x - 1, position.y));
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
                if(Game.board.findFigureByPosition(new Position(position.x, position.y - 2)) == null)
                possibleMoves.add(new Position(position.x, position.y - 2));
            }
            try {
                Figure rightTake = Game.board.findFigureByPosition(new Position(position.x + 1, position.y - 1));
                if(rightTake != null) {
                    possibleMoves.add(rightTake.position);
                }
            } catch (IllegalSquareException ignore) {}
            try {
                Figure leftTake = Game.board.findFigureByPosition(new Position(position.x - 1, position.y - 1));
                if(leftTake != null) {
                    possibleMoves.add(leftTake.position);
                }
            } catch (IllegalSquareException ignore) {}
        }

        if(isPinned() != null) { // excluding moves
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

        List<Figure> checkers = Game.board.getKing(this.isWhite).checkers();
        if(!checkers.isEmpty()) { // if the King is under a check
            List<Position> covers = Game.board.getKing(this.isWhite).possibleCovers();
            if(checkers.size() == 1) {
                covers.add(checkers.get(0).position);
            }
            possibleMoves.retainAll(covers);
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