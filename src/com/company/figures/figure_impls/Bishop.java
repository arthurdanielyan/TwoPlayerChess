package com.company.figures.figure_impls;

import com.company.core.Position;
import com.company.figures.Figure;
import com.company.figures.figure_helpers.MoveRestrictions;
import com.company.game.Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bishop extends Figure {

    public Bishop(Position position, boolean isWhite) {
        super(position, isWhite);

        if(isWhite) {
            figureChar = '♝';
        }
        else {
            figureChar = '♗';
        }
    }

    /**
     * @param control - if true this returns the squares which this figure "sees"(it can see
     *                through the enemy king), squares where it can go otherwise
     * */
    private List<Position> combinedMoves(boolean control, MoveRestrictions mr) {
        List<Position> possibleMoves = new ArrayList<>();


        if(mr != MoveRestrictions.RTL_VER)
        if(8 - position.x <= 8 - position.y) { // if closer to the right than to the top
            for (int i = 1; i <= 8 - position.x; i++) {
                Position currentPos = new Position(position.x + i, position.y + i);
                Figure figure = Game.board.findFigureByPosition(currentPos);
                possibleMoves.add(currentPos);
                if (figure != null) {
                    // if control && opposite king don't break => if either of these conditions are wrong then break
                    if(!control || figure.isWhite == isWhite || !(figure instanceof King)) break;
                }
            }
        } else {
            for (int i = 1; i <= 8 - position.y; i++) {
                Position currentPos = new Position(position.x + i, position.y + i);
                Figure figure = Game.board.findFigureByPosition(currentPos);
                possibleMoves.add(currentPos);
                if (figure != null) {
                    // if control && opposite king don't break => if either of these conditions are wrong then break
                    if(!control || figure.isWhite == isWhite || !(figure instanceof King)) break;
                }
            }
        }

        if(mr != MoveRestrictions.LTR_HOR)
        if(8 - position.x <= position.y-1) { // if closer to the right than to the bottom
            for (int i = 1; i <= 8 - position.x; i++) {
                Position currentPos = new Position(position.x + i, position.y - i);
                Figure figure = Game.board.findFigureByPosition(currentPos);
                possibleMoves.add(currentPos);
                if (figure != null) {
                    // if control && opposite king don't break => if either of these conditions are wrong then break
                    if(!control || figure.isWhite == isWhite || !(figure instanceof King)) break;
                }
            }
        } else {
            for (int i = 1; i < position.y; i++) {
                Position currentPos = new Position(position.x + i, position.y - i);
                Figure figure = Game.board.findFigureByPosition(currentPos);
                possibleMoves.add(currentPos);
                if (figure != null) {
                    // if control && opposite king don't break => if either of these conditions are wrong then break
                    if(!control || figure.isWhite == isWhite || !(figure instanceof King)) break;
                }
            }
        }

        if(mr != MoveRestrictions.LTR_HOR)
        if(position.x <= 8 - position.y) { // if closer to the left than to the top
            for (int i = 1; i < position.x; i++) {
                Position currentPos = new Position(position.x - i, position.y + i);
                Figure figure = Game.board.findFigureByPosition(currentPos);
                possibleMoves.add(currentPos);
                if (figure != null) {
                    // if control && opposite king don't break => if either of these conditions are wrong then break
                    if(!control || figure.isWhite == isWhite || !(figure instanceof King)) break;
                }
            }
        } else {
            for (int i = 1; i <= 8 - position.y; i++) {
                Position currentPos = new Position(position.x - i, position.y + i);
                Figure figure = Game.board.findFigureByPosition(currentPos);
                possibleMoves.add(currentPos);
                if (figure != null) {
                    // if control && opposite king don't break => if either of these conditions are wrong then break
                    if(!control || figure.isWhite == isWhite || !(figure instanceof King)) break;
                }
            }
        }

        if(mr != MoveRestrictions.RTL_VER)
        if(position.x <= position.y) { // if closer to the left than to the bottom
            for (int i = 1; i < position.x; i++) {
                Position currentPos = new Position(position.x - i, position.y - i);
                Figure figure = Game.board.findFigureByPosition(currentPos);
                possibleMoves.add(currentPos);
                if (figure != null) {
                    // if control && opposite king don't break => if either of these conditions are wrong then break
                    if(!control || figure.isWhite == isWhite || !(figure instanceof King)) break;
                }
            }
        } else {
            for (int i = 1; i < position.y; i++) {
                Position currentPos = new Position(position.x - i, position.y - i);
                Figure figure = Game.board.findFigureByPosition(currentPos);
                possibleMoves.add(currentPos);
                if (figure != null) {
                    // if control && opposite king don't break => if either of these conditions are wrong then break
                    if(!control || figure.isWhite == isWhite || !(figure instanceof King)) break;
                }
            }
        }

        return possibleMoves;
    }

    @Override
    public List<Position> possibleMoves() {
        if(isPinned() != null) {
            Position pinner = isPinned().position;
            if(pinner.x < this.position.x) { // pinner is at the right
                if(pinner.y > this.position.y) {
                    return combinedMoves(false, MoveRestrictions.RTL_VER);
                } else {
                    return combinedMoves(false, MoveRestrictions.LTR_HOR);
                }
            } else if (pinner.x > this.position.x) { // pinner is at the left
                if(pinner.y > this.position.y) {
                    return combinedMoves(false, MoveRestrictions.LTR_HOR);
                } else {
                    return combinedMoves(false, MoveRestrictions.RTL_VER);
                }
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
        return combinedMoves(true, MoveRestrictions.FREE);
    }
}