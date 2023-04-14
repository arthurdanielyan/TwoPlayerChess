package com.company.figures.figur_impls;

import com.company.core.BoardLetters;
import com.company.core.Position;
import com.company.figures.Figure;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Figure {

    public Bishop(boolean isWhite) {
        super(isWhite);

        char figureChar;
        int y;

        if(isWhite) {
            figureChar = '♝';
            y = 1;
        }
        else {
            figureChar = '♗';
            y = 8;
        }

        init(figureChar, new Position(BoardLetters.F, y));
    }

    @Override
    public List<Position> possibleMoves() {
        List<Position> possibleMoves = new ArrayList<>();

        if(8 - position.x <= 8 - position.y) { // if closer to the right than to the top
            for (int i = 1; i <= 8 - position.x; i++) {
                possibleMoves.add(new Position(position.x + i, position.y + i));
            }
        } else {
            for (int i = 1; i <= 8 - position.y; i++) {
                possibleMoves.add(new Position(position.x + i, position.y + i));
            }
        }

        if(8 - position.x <= position.y-1) { // if closer to the right than to the bottom
            for (int i = 1; i <= 8 - position.x; i++) {
                possibleMoves.add(new Position(position.x + i, position.y - i));
            }
        } else {
            for (int i = 1; i < position.y; i++) {
                possibleMoves.add(new Position(position.x + i, position.y - i));
            }
        }

        if(position.x <= 8 - position.y) { // if closer to the left than to the top
            for (int i = 1; i < position.x; i++) {
                possibleMoves.add(new Position(position.x - i, position.y + i));
            }
        } else {
            for (int i = 1; i <= 8 - position.y; i++) {
                possibleMoves.add(new Position(position.x - i, position.y + i));
            }
        }

        if(position.x <= position.y) { // if closer to the left than to the bottom
            for (int i = 1; i < position.x; i++) {
                possibleMoves.add(new Position(position.x - i, position.y - i));
            }
        } else {
            for (int i = 1; i < position.y; i++) {
                possibleMoves.add(new Position(position.x - i, position.y - i));
            }
        }


        this.removeOccupiedCells(possibleMoves);
        return possibleMoves;
    }
}
