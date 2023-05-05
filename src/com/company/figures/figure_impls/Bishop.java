package com.company.figures.figure_impls;

import com.company.Game;
import com.company.core.Position;
import com.company.core.exceptions.OccupiedSquareException;
import com.company.figures.CombinedMovesCondition;
import com.company.figures.Figure;

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


    private List<Position> combinedMoves(CombinedMovesCondition condition) {
        List<Position> possibleMoves = new ArrayList<>();

//        for(Figure f : Game.board.figures) { // checking for a discover check
//            if(f.isWhite != this.isWhite) {
//                Game.board.figures.remove(this);
//                System.out.println(f);
//                for(Position p : f.possibleMoves()) {
//                    if(p == Game.board.getKing(this.isWhite).position) {
//                        try {
//                            Game.board.addFigure(this);
//                        } catch (OccupiedSquareException ignore) {}
//                        return Collections.emptyList();
//                    }
//                }
//            }
//        }

        King myKing = Game.board.getKing(this.isWhite);
        Figure queenCheck = new Queen(this.position, !isWhite);
        Figure knightCheck = new Knight(this.position, !isWhite);
        


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
        return combinedMoves((Figure f) -> false);
    }

    @Override
    public List<Position> controlSquares() {
        // isWhite check is not necessarily needed
        return combinedMoves((Figure f) -> (f.isWhite != this.isWhite && f instanceof King));
    }
}