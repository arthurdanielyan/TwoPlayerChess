package com.company.figures;

import com.company.Game;
import com.company.core.Position;
import com.company.figures.figur_impls.King;

import java.util.List;

public abstract class Figure {

    public Position position;

    public char figureChar;
    public final boolean isWhite;

    protected void init(char figureChar, Position pos) {
        this.figureChar = figureChar;
        this.position = pos;
    }


    /** Used to have time to determine figure char before calling the method init above */
    protected Figure(boolean isWhite) {
        this.isWhite = isWhite;
    }



    /** Should be called at the end of possibleMoves() */
    protected void removeOccupiedCells(List<Position> possibleMoves) {
        for (Figure f : Game.figures) {
            if(f.isWhite == this.isWhite || f instanceof King) {
                possibleMoves.remove(f.position);
            }
        }
    }

    public abstract List<Position> possibleMoves();
}
