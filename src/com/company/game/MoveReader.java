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

    public MoveReader(Board board) {
        this.board = board;
    }

    @SuppressWarnings("deprecation")
    public void requestMove() {
        String moveOf;
        if(board.mated) {
            System.out.println();
            board.render();
            System.out.println();
            if(board.moveOfWhite) {
                System.out.println("Black won by checkmate!");
            } else {
                System.out.println("White won by checkmate!");
            }
            Thread.currentThread().stop();
            // Thread needs to be stops before after all the readMove() methods return some bullshit will happen
        }

        if(board.moveOfWhite) moveOf = "White";
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
            if(!board.getKing(board.moveOfWhite).castle(false).isLegal()) {
                System.out.println("Impossible move, try again");
            }
            requestMove();
        } else if (moveReq.equals("O-O")) {
            if(!board.getKing(board.moveOfWhite).castle(true).isLegal()) {
                System.out.println("Impossible move, try again");
            }
            requestMove();
        }

        String moveReq1 = moveReq;
        if(moveReq.charAt(moveReq.length()-1) == '#' || moveReq.charAt(moveReq.length()-1) == '+') {
            moveReq1 = moveReq.substring(0, moveReq.length()-1);
        }
        char figureToMoveLetter = moveReq1.charAt(0);
        List<? extends Figure> foundFigures = new ArrayList<>();

        boolean pawnPromotion = false;

        String destinationSquareString = "";
        try {
            destinationSquareString = moveReq1.substring(moveReq1.length() - 2);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Couldn't resolve move");
            requestMove();
        }
        Position destination = null;
        try {
            destination = Position.toPosition(destinationSquareString);
        } catch (IllegalArgumentException e) {
            if(moveReq1.charAt(moveReq1.length()-2) != '=') {
                System.out.println("Couldn't resolve move");
                requestMove();
            } else {
                destinationSquareString = moveReq1.substring(moveReq1.length() - 4, moveReq1.length() - 2);
                try {
                    destination = Position.toPosition(destinationSquareString);
                    if((board.moveOfWhite && destination.y == 8) || (!board.moveOfWhite && destination.y == 1))
                    pawnPromotion = true;
                } catch (IllegalArgumentException e1) {
                    System.out.println("Couldn't resolve move");
                    requestMove();
                }
            }
        }

        if(figureLetters.contains(figureToMoveLetter)) {
            switch (figureToMoveLetter) {
                case 'K' -> foundFigures = board.getFigures(King.class, board.moveOfWhite);
                case 'Q' -> foundFigures = board.getFigures(Queen.class, board.moveOfWhite);
                case 'B' -> foundFigures = board.getFigures(Bishop.class, board.moveOfWhite);
                case 'N' -> foundFigures = board.getFigures(Knight.class, board.moveOfWhite);
                case 'R' -> foundFigures = board.getFigures(Rook.class, board.moveOfWhite);
            }
        } else if(xOfFile(figureToMoveLetter) > 0){ // a to h
            foundFigures = board.getFigures(Pawn.class, board.moveOfWhite);
            foundFigures.removeIf((Predicate<Figure>) figure -> (figure.position.x != xOfFile(figureToMoveLetter)) || figure.isWhite != board.moveOfWhite);
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
                    switch (moveReq1.charAt(moveReq1.length()-1)) {
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

        if(foundFigures.size() == 0) {
            System.out.println("Impossible move, try again");
            requestMove();
        } else {
            StringBuilder moveReqCopy = new StringBuilder(moveReq1);
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
                if(board.mated && moveReq.charAt(moveReq.length()-1) != '#') {
                    board.takeback();
                    System.out.println("Did you mean " + moveReq + "#?");
                    requestMove();
                }
            }
        }
        requestMove();
    }


    private static int xOfFile(char c) {
        if (c >= 97 && c <= 104) {
            return c-96;
        }
        return -1;
    }

}
