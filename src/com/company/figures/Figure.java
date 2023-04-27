package com.company.figures;

import com.company.Game;
import com.company.core.Position;

import java.util.List;
import java.util.Objects;

public abstract class Figure {

    public Position position;

    public char figureChar;
    public final boolean isWhite;

    protected Figure(Position position, boolean isWhite) {
        this.position = position;
        this.isWhite = isWhite;
    }


    public abstract List<Position> possibleMoves();

    /** isn't needed for Rook, Bishop and Queen */
    protected void removeOccupiedCells(List<Position> possibleMoves) {
        possibleMoves.removeIf(position -> {
            Figure figureAtPos = Game.board.getFigureByPosition(position);
            return figureAtPos != null && figureAtPos.position.equals(position) && figureAtPos.isWhite == Figure.this.isWhite;
        });
    }

    /** King should check controlled squares with this not with possibleMoves()
     *  because some squares may be covered by the king itself
     *
     *  Example: Ke3, Re2
     *
     *  The only difference between implementations of this and possibleMoves()
     *  is that this ignores the opposite color king as a piece
     * */
    public abstract List<Position> controlSquares();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Figure figure = (Figure) o;
        return figureChar == figure.figureChar && isWhite == figure.isWhite && position.equals(figure.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, figureChar, isWhite);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(isWhite) {
            sb.append("White ");
        } else {
            sb.append("Black ");
        }

        sb
          .append(this.getClass().getSimpleName())
          .append(" at square ")
          .append(position);

        return sb.toString();
    }
}
