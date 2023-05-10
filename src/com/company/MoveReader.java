package com.company;

import com.company.core.Position;
import com.company.figures.Figure;
import com.company.figures.figure_impls.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MoveReader {

    private final List<Character> figureLetters = Arrays.asList('K', 'Q', 'B', 'N', 'R');

    private final Board board;
    private boolean moveOfWhite = true;

    public MoveReader(Board board) {
        this.board = board;
    }

    public void readMove(String moveReq) {
        String moveOf;
        if(this.moveOfWhite) moveOf = "White";
        else moveOf = "Black";
        System.out.println(moveOf + " to move, enter the move");

        char figureToMoveLetter = moveReq.charAt(0);
        List<Figure> foundFigures = Collections.emptyList();
        if(figureLetters.contains(figureToMoveLetter)) {
            switch (figureToMoveLetter) {
                case 'K' -> foundFigures = board.getFigure(King.class, moveOfWhite);
                case 'Q' -> foundFigures = board.getFigure(Queen.class, moveOfWhite);
                case 'B' -> foundFigures = board.getFigure(Bishop.class, moveOfWhite);
                case 'N' -> foundFigures = board.getFigure(Knight.class, moveOfWhite);
                case 'R' -> foundFigures = board.getFigure(Rook.class, moveOfWhite);
            }
        } else if(isFile(figureToMoveLetter)){ // a to h
            foundFigures = board.getFigure(Pawn.class, moveOfWhite);
        } else System.out.println("Couldn't resolve move");

        boolean manyPossibleFigures = false;
        /*
            The logic of this for loop is wrong yet. It should check every figure's
            possible moves with every other figure's possible moves
        */
        for(int i = 0; i < foundFigures.size()-1; i++) {
            List<Position> currentFigureMoves = foundFigures.get(i).possibleMoves();
            List<Position> nextFigureMoves = foundFigures.get(i+1).possibleMoves();
            if(containsAny( currentFigureMoves, nextFigureMoves )) {
                manyPossibleFigures = true;
            }
        }

        System.out.println(foundFigures);

    }


    public static boolean isFile(char c) {
        return (c >= 97 && c <= 104);
    }

    public static <T> boolean containsAny(List<T> container, List<T> elements) {
        for(T e : elements) {
            if(container.contains(e)) return true;
        }
        return false;
    }
}
