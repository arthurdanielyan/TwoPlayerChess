package com.company.figures;

import com.company.core.Position;

import java.util.List;

public abstract class Figure {

    public Position position;

    protected boolean isWhite;
    protected char figureChar;

    protected void init(char figureChar, boolean isWhite, Position pos) {
        this.figureChar = figureChar;
        this.isWhite = isWhite;
        this.position = pos;
    }


    /** Used to have time to determine figure char before calling the method init above */
    public Figure(){}


    public abstract List<Position> possibleMoves();
}
