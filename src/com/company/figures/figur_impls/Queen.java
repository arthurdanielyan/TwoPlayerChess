package com.company.figures.figur_impls;

import com.company.core.Position;
import com.company.figures.Figure;

import java.util.ArrayList;
import java.util.List;

import static com.company.core.BoardLetters.D;

public class Queen extends Figure {


    public Queen(boolean isWhite) {
        super(isWhite);

        char figureChar;
        int x = D.num;
        int y;

        if(isWhite) {
            figureChar = '♛';
            y = 1;
        }
        else {
            figureChar = '♕';
            y = 8;
        }

        init(figureChar, new Position(x, y));
    }

    @Override
    public List<Position> possibleMoves() {
        ArrayList<Position> possibleMoves = new ArrayList<>();

        for(int i = 1; i <= 8; i++) {
            if(i == this.position.x) continue;
            possibleMoves.add(new Position(i, position.y));
        }
        for(int i = 1; i <= 8; i++) {
            if(i == this.position.y) continue;
            possibleMoves.add(new Position(position.x, i));
        }

//        for(int y = 1; y <= 8; y++) {
//            for(int x = 1; x <= 8; x++) {
//                if(new Position(x, y).equals(this.position)) continue;
//                if(Math.abs(y - this.position.y) == Math.abs(x - this.position.x)) {
//                    possibleMoves.add(new Position(x, y));
//                }
//            }
//        }
        for(int x = this.position.x+1; x <= 8; x++) {
            int i = x - (this.position.x+1) + 1;
            System.out.println(x);
            possibleMoves.add(new Position(this.position.x + i, position.y + i));
            possibleMoves.add(new Position(this.position.x + i, position.y - i));
        }


        return possibleMoves;
    }
}
