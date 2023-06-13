package com.company.game;

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
        if(moveReq.equals("O-O-O")) {
            if(!board.getKing(moveOfWhite).castle(false).isLegal()) {
                System.out.println("Impossible move, try again");
                requestMove();
            } else {
                moveOfWhite = !moveOfWhite;
            }
        } else if (moveReq.equals("O-O")) {
            if(!board.getKing(moveOfWhite).castle(true).isLegal()) {
                System.out.println("Impossible move, try again");
                requestMove();
            } else {
                moveOfWhite = !moveOfWhite;
            }
        }

        char figureToMoveLetter = moveReq.charAt(0); // doesn't consider castles and pawn promotions
        List<? extends Figure> foundFigures = new ArrayList<>();

        boolean pawnPromotion = false;

        String destinationSquareString = moveReq.substring(moveReq.length() - 2);
        Position destination = null;
        try {
            destination = Position.toPosition(destinationSquareString);
        } catch (IllegalArgumentException e) {
            if(moveReq.charAt(moveReq.length()-2) != '=') {
                System.out.println("Couldn't resolve move");
                requestMove();
            } else {
                destinationSquareString = moveReq.substring(moveReq.length() - 4, moveReq.length() - 2);
                try {
                    destination = Position.toPosition(destinationSquareString);
                    if((moveOfWhite && destination.y == 8) || (!moveOfWhite && destination.y == 1))
                    pawnPromotion = true;
                } catch (IllegalArgumentException e1) {
                    System.out.println("Couldn't resolve move");
                    requestMove();
                }
            }
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


        if(pawnPromotion) {
            Figure mover = foundFigures.get(0);
            if(mover instanceof Pawn) {
                if((mover.isWhite && destination.y == 8) || (!mover.isWhite && destination.y == 1)) {
                    Class<? extends Figure> promoteTo;
                    switch (moveReq.charAt(moveReq.length()-1)) {
                        case 'Q' -> promoteTo = Queen.class;
                        case 'B' -> promoteTo = Bishop.class;
                        case 'N' -> promoteTo = Knight.class;
                        case 'R' -> promoteTo = Rook.class;
                        default -> {
                            System.out.println("Couldn't resolve move");
                            promoteTo = Pawn.class;
                            requestMove();
                        }
                    }
                    if(((Pawn) mover).promote(promoteTo, destination)) {
                        System.out.println("Couldn't resolve move");
                    }
                    requestMove();
                }
            }
        }


        /*if(foundFigures.size() == 1) {
            if(board.getFigureByPosition(destination) != null && moveReq.charAt(moveReq.length()-3) != 'x'){
                System.out.println("Couldn't resolve move, did you mean " + moveReq.substring(0, moveReq.length()-2) + "x" + destinationSquareString);
                requestMove();
            }
            foundFigures.get(0).move(Position.toPosition(destinationSquareString));
        }
        else */if(foundFigures.size() == 0) {
            System.out.println("Impossible move, try again");
            requestMove();
        } else {
            StringBuilder moveReqCopy = new StringBuilder(moveReq);
            moveReqCopy.deleteCharAt(0);
            moveReqCopy.deleteCharAt(moveReqCopy.length()-1);
            try {
                moveReqCopy.deleteCharAt(moveReqCopy.length() - 1);
            } catch (StringIndexOutOfBoundsException ignore) {}
            moveReqCopy = new StringBuilder(moveReqCopy.toString().replace("x", ""));
            // now the destination the figure character (or a pawn's file) and the takes (if there was) mark are removed from the move query
            if(moveReqCopy.length() == 0 && foundFigures.size() > 1) {
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
                } else if(identifier.length() == 2) { // could be 0
                    int file = xOfFile(identifier.charAt(0));
                    int rank = Character.getNumericValue(identifier.charAt(1));
                    return !figure.position.equals(new Position(file, rank));
                }
                return false;
            }));

            if (foundFigures.size() > 1) {
                System.out.println("Couldn't identify the piece as multiple pieces can go to the specified square");
                requestMove();
            } else if(foundFigures.size() == 0) { // happens after something like g7=Q
                System.out.println("Impossible move, try again");
                requestMove();
            } else {
                Figure mover = foundFigures.get(0);
                if(mover instanceof Pawn) {
                    if((mover.isWhite && destination.y == 8) || (!mover.isWhite && destination.y == 1)) {
                        System.out.println("Specify the piece which the pawn should promote to");
                        requestMove();
                    }
                }
                foundFigures.get(0).move(destination);
            }
        }
        moveOfWhite = !moveOfWhite;
        requestMove();
    }

    /**
     * Undone things
     * 1. add castles
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
