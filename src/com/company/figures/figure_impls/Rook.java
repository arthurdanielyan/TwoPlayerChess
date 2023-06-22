package com.company.figures.figure_impls;

import com.company.core.BoardLetters;
import com.company.core.Position;
import com.company.core.move_information_wrappers.MoveInfo;
import com.company.figures.Figure;
import com.company.figures.figure_helpers.MoveRestrictions;
import com.company.game.Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rook extends Figure {

    boolean castleable = true;

    public Rook(Position position, boolean isWhite) {
        super(position, isWhite);

        if(isWhite) {
            figureChar = '♜';
        }
        else {
            figureChar = '♖';
        }
    }

    public MoveInfo castle(boolean shortSide) {
        if(!castleable) {
            return new MoveInfo(null, false, false);
        }
        castleable = false;
        if(shortSide) {
            if(isWhite) {
                position = new Position(BoardLetters.F, 1);
            } else {
                position = new Position(BoardLetters.F, 8);
            }
        } else {
            if(isWhite) {
                position = new Position(BoardLetters.D, 1);
            } else {
                position = new Position(BoardLetters.D, 8);
            }
        }
        return new MoveInfo(position, false, true);
    }

    @Override
    public MoveInfo move(Position newPosition) {
        castleable = false;
        return super.move(newPosition);
    }

    /**
     * @param control - if true this returns the squares which this figure "sees"(it can see
     *                through the enemy king), squares where it can go otherwise
     * */
    private List<Position> combinedMoves(boolean control, MoveRestrictions mr) {
        List<Position> possibleMoves = new ArrayList<>();


        // right direction
        if(mr != MoveRestrictions.LTR_HOR) {
            for (int x = position.x + 1; x <= 8; x++) {
                Figure figure = Game.board.getFigureByPosition(new Position(x, position.y));
                possibleMoves.add(new Position(x, position.y));
                if (figure != null) {
                    if(!control && figure.isWhite != isWhite) break;
                    if(figure.isWhite == isWhite && !(figure instanceof King)){
                        break;
                    }
                }
            }
            // left direction
            for (int x = position.x - 1; x >= 1; x--) {
                Figure figure = Game.board.getFigureByPosition(new Position(x, position.y));
                possibleMoves.add(new Position(x, position.y));
                if (figure != null) {
                    if(!control && figure.isWhite != isWhite) break;
                    if(figure.isWhite == isWhite && !(figure instanceof King)){
                        break;
                    }
                }
            }
        }
        if(mr != MoveRestrictions.RTL_VER) {
            // up direction
            for (int y = position.y + 1; y <= 8; y++) {
                Figure figure = Game.board.getFigureByPosition(new Position(position.x, y));
                possibleMoves.add(new Position(position.x, y));
                if (figure != null) {
                    if (!control && figure.isWhite != isWhite) break;
                    if (figure.isWhite == isWhite && !(figure instanceof King)) {
                        break;
                    }
                }
            }
            // down direction
            for (int y = position.y - 1; y >= 1; y--) {
                Figure figure = Game.board.getFigureByPosition(new Position(position.x, y));
                possibleMoves.add(new Position(position.x, y));
                if (figure != null) {
                    if(!control && figure.isWhite != isWhite) break;
                    if(figure.isWhite == isWhite && !(figure instanceof King)){
                        break;
                    }
                }
            }
        }

        return possibleMoves;
    }

    @Override
    public List<Position> possibleMoves() {
        if(isPinned() != null) {

            if(isPinned().position.x == this.position.x) {
                return combinedMoves(false, MoveRestrictions.LTR_HOR);
            } else if (isPinned().position.y == this.position.y) {
                return combinedMoves(false, MoveRestrictions.RTL_VER);
            }

            return Collections.emptyList();
        }
        List<Figure> checkers = Game.board.getKing(this.isWhite).checkers();
        if(!checkers.isEmpty()) { // if the King is under a check
            List<Position> possibleMoves = Game.board.getKing(this.isWhite).possibleCovers();
            if(checkers.size() == 1) {
                possibleMoves.add(checkers.get(0).position);
            }
            possibleMoves.retainAll(this.combinedMoves(false, MoveRestrictions.FREE));
            return possibleMoves;
        }

        return combinedMoves(false, MoveRestrictions.FREE);
    }

    @Override
    public List<Position> controlSquares() {
        // isWhite check is not necessarily needed
        return combinedMoves(true, MoveRestrictions.FREE);
    }
}
