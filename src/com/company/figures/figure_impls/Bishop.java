package com.company.figures.figure_impls;

import com.company.game.Game;
import com.company.core.Position;
import com.company.figures.figure_helpers.CombinedMovesCondition;
import com.company.figures.Figure;
import com.company.figures.figure_helpers.MoveRestrictions;

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


    private List<Position> combinedMoves(CombinedMovesCondition condition, MoveRestrictions mr) {
        List<Position> possibleMoves = new ArrayList<>();


        if(mr != MoveRestrictions.RTL_VER)
        if(8 - position.x <= 8 - position.y) { // if closer to the right than to the top
            for (int i = 1; i <= 8 - position.x; i++) {
                Position currentPos = new Position(position.x + i, position.y + i);
                Figure figure = Game.board.getFigureByPosition(currentPos);
                if(figure == null) {
                    possibleMoves.add(currentPos);
                } else if(figure.isWhite != isWhite) {
                    possibleMoves.add(currentPos);
                    if(!condition.isEnemyKing(figure))break;
                }
                else break;
            }
        } else {
            for (int i = 1; i <= 8 - position.y; i++) {
                Position currentPos = new Position(position.x + i, position.y + i);
                Figure figure = Game.board.getFigureByPosition(currentPos);
                if(figure == null) {
                    possibleMoves.add(currentPos);
                } else if(figure.isWhite != isWhite){
                    possibleMoves.add(currentPos);
                    if(!condition.isEnemyKing(figure))break;
                }
                else break;
            }
        }

        if(mr != MoveRestrictions.LTR_HOR)
        if(8 - position.x <= position.y-1) { // if closer to the right than to the bottom
            for (int i = 1; i <= 8 - position.x; i++) {
                Position currentPos = new Position(position.x + i, position.y - i);
                Figure figure = Game.board.getFigureByPosition(currentPos);
                if(figure == null) {
                    possibleMoves.add(currentPos);
                } else if(figure.isWhite != isWhite){
                    possibleMoves.add(currentPos);
                    if(!condition.isEnemyKing(figure))break;
                }
                else break;
            }
        } else {
            for (int i = 1; i < position.y; i++) {
                Position currentPos = new Position(position.x + i, position.y - i);
                Figure figure = Game.board.getFigureByPosition(currentPos);
                if(figure == null) {
                    possibleMoves.add(currentPos);
                } else if(figure.isWhite != isWhite){
                    possibleMoves.add(currentPos);
                    if(!condition.isEnemyKing(figure))break;
                }
                else break;
            }
        }

        if(mr != MoveRestrictions.LTR_HOR)
        if(position.x <= 8 - position.y) { // if closer to the left than to the top
            for (int i = 1; i < position.x; i++) {
                Position currentPos = new Position(position.x - i, position.y + i);
                Figure figure = Game.board.getFigureByPosition(currentPos);
                if(figure == null) {
                    possibleMoves.add(currentPos);
                } else if(figure.isWhite != isWhite){
                    possibleMoves.add(currentPos);
                    if(!condition.isEnemyKing(figure))break;
                }
                else break;
            }
        } else {
            for (int i = 1; i <= 8 - position.y; i++) {
                Position currentPos = new Position(position.x - i, position.y + i);
                Figure figure = Game.board.getFigureByPosition(currentPos);
                if(figure == null) {
                    possibleMoves.add(currentPos);
                } else if(figure.isWhite != isWhite){
                    possibleMoves.add(currentPos);
                    if(!condition.isEnemyKing(figure))break;
                }
                else break;
            }
        }

        if(mr != MoveRestrictions.RTL_VER)
        if(position.x <= position.y) { // if closer to the left than to the bottom
            for (int i = 1; i < position.x; i++) {
                Position currentPos = new Position(position.x - i, position.y - i);
                Figure figure = Game.board.getFigureByPosition(currentPos);
                if(figure == null) {
                    possibleMoves.add(currentPos);
                } else if(figure.isWhite != isWhite){
                    possibleMoves.add(currentPos);
                    if(!condition.isEnemyKing(figure))break;
                }
                else break;
            }
        } else {
            for (int i = 1; i < position.y; i++) {
                Position currentPos = new Position(position.x - i, position.y - i);
                Figure figure = Game.board.getFigureByPosition(currentPos);
                if(figure == null) {
                    possibleMoves.add(currentPos);
                } else if(figure.isWhite != isWhite){
                    possibleMoves.add(currentPos);
                    if(!condition.isEnemyKing(figure))break;
                }
                else break;
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
                    return combinedMoves((Figure f) -> false, MoveRestrictions.RTL_VER);
                } else {
                    return combinedMoves((Figure f) -> false, MoveRestrictions.LTR_HOR);
                }
            } else if (pinner.x > this.position.x) { // pinner is at the left
                if(pinner.y > this.position.y) {
                    return combinedMoves((Figure f) -> false, MoveRestrictions.LTR_HOR);
                } else {
                    return combinedMoves((Figure f) -> false, MoveRestrictions.RTL_VER);
                }
            }
            return Collections.emptyList();
        }
        if(!Game.board.getKing(this.isWhite).checkers().isEmpty()) { // if the King is under a check;
            List<Position> possibleMoves = Game.board.getKing(this.isWhite).possibleCovers();
            possibleMoves.retainAll(this.combinedMoves((Figure f) -> false, MoveRestrictions.FREE));
            return possibleMoves;
        }

        return combinedMoves((Figure f) -> false, MoveRestrictions.FREE);
    }

    @Override
    public List<Position> controlSquares() {
        // isWhite check is not necessarily needed
        return combinedMoves((Figure f) -> (f.isWhite != this.isWhite && f instanceof King), MoveRestrictions.FREE);
    }
}