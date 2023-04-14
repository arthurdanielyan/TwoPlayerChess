package com.company.figures.figur_impls;

import com.company.core.BoardLetters;
import com.company.core.Position;
import com.company.figures.Figure;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Figure {


    public Rook(boolean isWhite) {
        super(isWhite);

        char figureChar;
        int y;

        if(isWhite) {
            figureChar = '♜';
            y = 1;
        }
        else {
            figureChar = '♖';
            y = 8;
        }

        init(figureChar, new Position(BoardLetters.H, y));
    }

    @Override
    public List<Position> possibleMoves() {
        List<Position> possibleMoves = new ArrayList<>();

        for(int i = 1; i <= 8; i++) {
            if(i == this.position.x) continue;
            possibleMoves.add(new Position(i, position.y));
        }
        for(int i = 1; i <= 8; i++) {
            if(i == this.position.y) continue;
            possibleMoves.add(new Position(position.x, i));
        }


        removeOccupiedCells(possibleMoves);
        return possibleMoves;
    }
}
