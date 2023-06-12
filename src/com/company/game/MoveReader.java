package com.company.game;

import com.company.core.Extensions;
import com.company.core.Position;
import com.company.figures.Figure;
import com.company.figures.figure_impls.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

import static com.company.core.Extensions.isLegalSquare;

public class MoveReader {

    private final List<Character> figureLetters = Arrays.asList('K', 'Q', 'B', 'N', 'R');

    private final Board board;
    private boolean moveOfWhite = true;

    public MoveReader(Board board) {
        this.board = board;
    }

    public void requestMove() {
        String moveOf;
        if(this.moveOfWhite) moveOf = "White";
        else moveOf = "Black";
        System.out.println();
        System.out.println(moveOf + " to move, enter the move");
        board.render();

        Scanner input = new Scanner(System.in);
        readMove(input.next());
        input.close();
    }

    public void readMove(String moveReq) {
        char figureToMoveLetter = moveReq.charAt(0); // doesn't consider pawn promotions and castles
        List<? extends Figure> foundFigures = new ArrayList<>();

        final String destinationSquareString = moveReq.substring(moveReq.length() - 2);
        Position destination = null;
        try {
            destination = Position.toPosition(destinationSquareString);
        } catch (IllegalArgumentException e) {
            System.out.println("Couldn't resolve move");
            requestMove();
        }

        if(figureLetters.contains(figureToMoveLetter)) {
            switch (figureToMoveLetter) {
                case 'K' -> foundFigures = board.getFigures(King.class, moveOfWhite);
                case 'Q' -> foundFigures = board.getFigures(Queen.class, moveOfWhite);
                case 'B' -> foundFigures = board.getFigures(Bishop.class, moveOfWhite);
                case 'N' -> foundFigures = board.getFigures(Knight.class, moveOfWhite);
                case 'R' -> foundFigures = board.getFigures(Rook.class, moveOfWhite);
            }
        } else if(xOfFile(figureToMoveLetter) > 0){ // a to h
            foundFigures = board.getFigures(Pawn.class, moveOfWhite);
            foundFigures.removeIf((Predicate<Figure>) figure -> (figure.position.x != xOfFile(figureToMoveLetter)) || figure.isWhite != moveOfWhite);
        } else {
            System.out.println("Couldn't resolve move");
            requestMove();
        }
        final Position finalDestination = destination;
        foundFigures.removeIf((Predicate<Figure>) figure -> (!figure.possibleMoves().contains(finalDestination)));



        if(foundFigures.size() == 1) {
            if(board.getFigureByPosition(destination) != null && moveReq.charAt(moveReq.length()-3) != 'x'){
                System.out.println("Couldn't resolve move, did you mean " + moveReq.substring(0, moveReq.length()-2) + "x" + destinationSquareString);
                requestMove();
            }
            foundFigures.get(0).move(Position.toPosition(destinationSquareString));
        } else if(foundFigures.size() == 0) {
            System.out.println("Impossible move, try again");
            requestMove();
        } else {
            StringBuilder moveReqCopy = new StringBuilder(moveReq);
            moveReqCopy.deleteCharAt(0);
            moveReqCopy.deleteCharAt(moveReqCopy.length()-1);
            moveReqCopy.deleteCharAt(moveReqCopy.length()-1);
            moveReqCopy = new StringBuilder(moveReqCopy.toString().replace("x", ""));
            // now the destination the figure character (or a pawn's file) and the takes (if there was) mark are removed from the move query
            if(moveReqCopy.length() == 0) {
                System.out.println("Couldn't identify the piece as multiple pieces can go to the specified square");
                requestMove();
            }
            final String identifier = moveReqCopy.toString();
            foundFigures.removeIf((figure -> {
                if(identifier.length() == 1) {
                    if(xOfFile(identifier.charAt(0)) > 0) {
                        return figure.position.x != xOfFile(identifier.charAt(0));
                    } else if(isLegalSquare(Integer.parseInt(identifier))) {
                        return figure.position.y != Integer.parseInt(identifier);
                    }
                }
                return false;
            }));
            if (foundFigures.size() != 1) {
                System.out.println("Couldn't identify the piece as multiple pieces can go to the specified square");
                requestMove();
            } else {
                foundFigures.get(0).move(destination);
            }
        }
        moveOfWhite = !moveOfWhite;
        requestMove();
    }

    /**
     * Undone things
     * 1. add the case where both the file and the rank is specified
     * 2. add castles
     * 3. add pawn promotion
     *  
     * */


    private static int xOfFile(char c) {
        if (c >= 97 && c <= 104) {
            return c-96;
        }
        return -1;
    }

    private static <T> boolean containsAny(List<T> container, List<T> elements) {
        for(T e : elements) {
            if(container.contains(e)) return true;
        }
        return false;
    }
}
