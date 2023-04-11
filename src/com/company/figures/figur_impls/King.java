package com.company.figures.figur_impls;

import com.company.Game;
import com.company.core.Position;
import com.company.figures.Figure;

import java.util.ArrayList;
import java.util.List;

import static com.company.core.BoardLetters.E;
import static com.company.core.Extensions.isLegalCell;

public class King extends Figure {

    public King(boolean isWhite) {
        super(isWhite);

        char figureChar;
        int x = E.num;
        int y;

        if(isWhite) {
            figureChar = '♚';
            y = 1;
        }
        else {
            figureChar = '♔';
            y = 8;
        }

        init(figureChar, new Position(x, y));
    }

    @Override
    public List<Position> possibleMoves() {
        ArrayList<Position> possibleMoves = new ArrayList<>();
        if(isLegalCell(position.x-1)) {
            possibleMoves.add(new Position(position.x-1, position.y));

            if(isLegalCell(position.y-1)) possibleMoves.add(new Position(position.x-1, position.y-1));
            if(isLegalCell(position.y+1)) possibleMoves.add(new Position(position.x-1, position.y+1));
        }
        if(isLegalCell(position.x+1)) {
            possibleMoves.add(new Position(position.x+1, position.y));

            if(isLegalCell(position.y-1)) possibleMoves.add(new Position(position.x+1, position.y-1));
            if(isLegalCell(position.y+1)) possibleMoves.add(new Position(position.x+1, position.y+1));
        }
        if(isLegalCell(position.y+1)) possibleMoves.add(new Position(position.x, position.y+1));
        if(isLegalCell(position.y-1)) possibleMoves.add(new Position(position.x, position.y-1));

        for (Figure f : Game.board) {
            possibleMoves.remove(f.position);
        }

        return possibleMoves;
    }
}
