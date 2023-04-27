package com.company.figures.figur_impls;

import com.company.Game;
import com.company.core.Position;
import com.company.figures.Figure;

import java.util.ArrayList;
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

    @Override
    public List<Position> possibleMoves() {
        List<Position> possibleMoves = new ArrayList<>();

        if(8 - position.x <= 8 - position.y) { // if closer to the right than to the top
            for (int i = 1; i <= 8 - position.x; i++) {
                Position currentPos = new Position(position.x + i, position.y + i);
                Figure figure = Game.board.getFigureByPosition(currentPos);
                if(figure == null) {
                    possibleMoves.add(currentPos);
                } else if(figure.isWhite != isWhite){
                    possibleMoves.add(currentPos);
                    break;
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
                    break;
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
                    break;
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
                    break;
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
                    break;
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
                    break;
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
                    break;
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
                    break;
                }
                else break;
            }
        }

        return possibleMoves;
    }
}
