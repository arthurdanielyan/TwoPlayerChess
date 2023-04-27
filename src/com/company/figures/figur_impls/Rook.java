package com.company.figures.figur_impls;

import com.company.Game;
import com.company.core.Position;
import com.company.figures.Figure;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Figure {


    public Rook(Position position, boolean isWhite) {
        super(position, isWhite);

        if(isWhite) {
            figureChar = '♜';
        }
        else {
            figureChar = '♖';
        }
    }

    @Override
    public List<Position> possibleMoves() {
        List<Position> possibleMoves = new ArrayList<>();

        // right direction
        for(int x = position.x+1; x <= 8; x++) {
            Figure figure = Game.board.getFigureByPosition(new Position(x, position.y));
            if(figure == null) {
                possibleMoves.add(new Position(x, position.y));
            } else if(figure.isWhite != isWhite){
                possibleMoves.add(new Position(x, position.y));
                break;
            }
            else break;
        }
        // left direction
        for(int x = position.x-1; x >= 1; x--) {
            Figure figure = Game.board.getFigureByPosition(new Position(x, position.y));
            if(figure == null) {
                possibleMoves.add(new Position(x, position.y));
            } else if(figure.isWhite != isWhite){
                possibleMoves.add(new Position(x, position.y));
                break;
            }
            else break;
        }
        // up direction
        for(int y = position.y+1; y <= 8; y++){
            Figure figure = Game.board.getFigureByPosition(new Position(position.x, y));
            if(figure == null) {
                possibleMoves.add(new Position(position.x, y));
            } else if(figure.isWhite != isWhite){
                possibleMoves.add(new Position(position.x, y));
                break;
            }
            else break;
        }
        // down direction
        for(int y = position.y-1; y >= 1; y--){
            Figure figure = Game.board.getFigureByPosition(new Position(position.x, y));
            if(figure == null) {
                possibleMoves.add(new Position(position.x, y));
            } else if(figure.isWhite != isWhite){
                possibleMoves.add(new Position(position.x, y));
                break;
            }
            else break;
        }

        return possibleMoves;
    }
}
