package com.company.figures.figur_impls;

import com.company.core.Position;
import com.company.figures.Figure;

import java.util.List;

public class Queen extends Figure {


    public Queen(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public List<Position> possibleMoves() {
        return null;
    }
}
