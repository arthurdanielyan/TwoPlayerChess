package com.company.figures.figur_impls;

import com.company.core.BoardLetters;
import com.company.core.Position;
import com.company.figures.Figure;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Figure {


    public Knight(boolean isWhite) {
        super(isWhite);

        char figureChar;
        int y;

        if(isWhite) {
            figureChar = '♞';
            y = 1;
        }
        else {
            figureChar = '♘';
            y = 8;
        }

        init(figureChar, new Position(BoardLetters.G, y));
    }

    @Override
    public List<Position> possibleMoves() {
        List<Position> possibleMoves = new ArrayList<>();


        try {
            possibleMoves.add(new Position(position.x + 1, position.y - 2));
            System.out.println(new Position(position.x + 1, position.y - 2));
        } catch (IllegalArgumentException ignored) {}
        try {
            possibleMoves.add(new Position(position.x - 1, position.y - 2));
        } catch (IllegalArgumentException ignored) {}
        try {
            possibleMoves.add(new Position(position.x + 1, position.y + 2));
        } catch (IllegalArgumentException ignored) {}
        try {
            possibleMoves.add(new Position(position.x - 1, position.y + 2));
        } catch (IllegalArgumentException ignored) {}
        try {
            possibleMoves.add(new Position(position.x + 2, position.y + 1));
        } catch (IllegalArgumentException ignored) {}
        try {
            possibleMoves.add(new Position(position.x + 2, position.y - 1));
        } catch (IllegalArgumentException ignored) {}
        try {
            possibleMoves.add(new Position(position.x - 2, position.y + 1));
        } catch (IllegalArgumentException ignored) {}
        try {
            possibleMoves.add(new Position(position.x - 2, position.y - 1));
        } catch (IllegalArgumentException ignored) {}

        removeOccupiedCells(possibleMoves);
        return possibleMoves;
    }
}
