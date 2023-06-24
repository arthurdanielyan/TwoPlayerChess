package com.company.game;

import com.company.core.Position;
import com.company.core.exceptions.IllegalSquareException;
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

//    @SuppressWarnings("deprecation")
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
            return;
//            Thread.currentThread().stop();
            // Thread needs to be stopped because after all the readMove() methods return some bullshit will happen
        }// requiring the '+' sign, Move info and takebacks for castles and pawn promotions,
        if(board.stalemate) {
            System.out.println();
            board.render();
            System.out.println();
            System.out.println("Draw by stalemate!");
            return;
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
            King castlingKing = board.getKing(board.moveOfWhite);
            if(!castlingKing.isCastleable(false)) {
                System.out.println("Impossible move, try again");
            } else {
                castlingKing.castle(false);
            }
            requestMove();
            return;
        } else if (moveReq.equals("O-O")) {
            King castlingKing = board.getKing(board.moveOfWhite);
            if(!castlingKing.isCastleable(true)) {
                System.out.println("Impossible move, try again");
            } else {
                castlingKing.castle(true);
            }
            requestMove();
            return;
        }

        String moveReq1 = moveReq;
        if(moveReq.charAt(moveReq.length()-1) == '#' || moveReq.charAt(moveReq.length()-1) == '+') {
            moveReq1 = moveReq.substring(0, moveReq.length()-1);
        }
        char figureToMoveLetter = moveReq1.charAt(0);
        List<? extends Figure> foundFigures = new ArrayList<>();

        boolean pawnPromotion = false;

        String destinationSquareString;
        try {
            destinationSquareString = moveReq1.substring(moveReq1.length() - 2);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Couldn't resolve move");
            requestMove();
            return;
        }
        Position destination;
        try {
            destination = Position.toPosition(destinationSquareString);
        } catch (IllegalArgumentException | IllegalSquareException e) {
            if(moveReq1.charAt(moveReq1.length()-2) != '=') {
                System.out.println("Couldn't resolve move");
                requestMove();
                return;
            } else {
                destinationSquareString = moveReq1.substring(moveReq1.length() - 4, moveReq1.length() - 2);
                try {
                    destination = Position.toPosition(destinationSquareString);
                    if((board.moveOfWhite && destination.y == 8) || (!board.moveOfWhite && destination.y == 1))
                    pawnPromotion = true;
                } catch (IllegalArgumentException e1) {
                    System.out.println("Couldn't resolve move");
                    requestMove();
                    return;
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
            return;
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
                            requestMove();
                            return;
                        }
                    }
                    mover.move(destination, promoteTo);
                    requestMove();
                    return;
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
                return;
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
                        return;
                    }
                }
                foundFigures.get(0).move(destination);
                if(board.mated && moveReq.charAt(moveReq.length()-1) != '#') {
                    board.takeback();
                    System.out.println("Did you mean " + moveReq + "#?");
                    requestMove();
                    return;
                }
                if(board.getKing(board.moveOfWhite).checkers().size() != 0 && !board.mated && moveReq.charAt(moveReq.length()-1) != '+') {
                    board.takeback();
                    System.out.println("Did you mean " + moveReq + "+?");
                }
                requestMove();
            }
        }
    }


    /**
     * This is the same as readMove(String), but this doesn't require the user to write
     * the move. THis for doing some testing
     * */
    public void makeMove(String moveReq) {
        if(moveReq.equals("O-O-O")) {
            King castlingKing = board.getKing(board.moveOfWhite);
            if(!castlingKing.isCastleable(false)) {
                System.out.println("Impossible move, try again");
            } else {
                castlingKing.castle(false);
            }
            return;
        } else if (moveReq.equals("O-O")) {
            King castlingKing = board.getKing(board.moveOfWhite);
            if(!castlingKing.isCastleable(true)) {
                System.out.println("Impossible move, try again");
            } else {
                castlingKing.castle(true);
            }
            return;
        }

        String moveReq1 = moveReq;
        if(moveReq.charAt(moveReq.length()-1) == '#' || moveReq.charAt(moveReq.length()-1) == '+') {
            moveReq1 = moveReq.substring(0, moveReq.length()-1);
        }
        char figureToMoveLetter = moveReq1.charAt(0);
        List<? extends Figure> foundFigures = new ArrayList<>();

        boolean pawnPromotion = false;

        String destinationSquareString;
        try {
            destinationSquareString = moveReq1.substring(moveReq1.length() - 2);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Couldn't resolve move");
            return;
        }
        Position destination;
        try {
            destination = Position.toPosition(destinationSquareString);
        } catch (IllegalArgumentException e) {
            if(moveReq1.charAt(moveReq1.length()-2) != '=') {
                System.out.println("Couldn't resolve move");
                return;
            } else {
                destinationSquareString = moveReq1.substring(moveReq1.length() - 4, moveReq1.length() - 2);
                try {
                    destination = Position.toPosition(destinationSquareString);
                    if((board.moveOfWhite && destination.y == 8) || (!board.moveOfWhite && destination.y == 1))
                        pawnPromotion = true;
                } catch (IllegalArgumentException e1) {
                    System.out.println("Couldn't resolve move");
                    return;
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
            return;
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
                            return;
                        }
                    }
                    mover.move(destination, promoteTo);
                    return;
                }
            }
        }

        if(foundFigures.size() == 0) {
            System.out.println("Impossible move, try again");
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

                return;
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

            } else if(foundFigures.size() == 0) { // happens after something like g7=Q
                System.out.println("Impossible move, try again");
            } else {
                Figure mover = foundFigures.get(0);
                if(mover instanceof Pawn) {
                    if((mover.isWhite && destination.y == 8) || (!mover.isWhite && destination.y == 1)) {
                        System.out.println("Specify the piece which the pawn should promote to");

                        return;
                    }
                }
                foundFigures.get(0).move(destination);
                if(board.mated && moveReq.charAt(moveReq.length()-1) != '#') {
                    board.takeback();
                    System.out.println("Did you mean " + moveReq + "#?");
                    return;
                }
                if(board.getKing(board.moveOfWhite).checkers().size() != 0 && !board.mated && moveReq.charAt(moveReq.length()-1) != '+') {
                    board.takeback();
                    System.out.println("Did you mean " + moveReq + "+?");
                }
            }
        }
    }

    private static int xOfFile(char c) {
        if (c >= 97 && c <= 104) {
            return c-96;
        }
        return -1;
    }

}
