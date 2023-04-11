package com.company.figures;

import com.company.core.Position;

import java.util.List;

public abstract class Figure {

    public Position position;

    protected char figureChar;
    protected final boolean isWhite;

    protected void init(char figureChar, Position pos) {
        this.figureChar = figureChar;
        this.position = pos;
    }


    /** Used to have time to determine figure char before calling the method init above */
    public Figure(boolean isWhite) {
        this.isWhite = isWhite;
    }


    public abstract List<Position> possibleMoves();
}
