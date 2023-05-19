package com.company;

import com.company.core.BoardLetters;
import com.company.core.Position;
import com.company.figures.Figure;
import com.company.figures.figure_impls.*;

import java.util.*;
import java.util.function.Predicate;

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
        System.out.println(moveOf + " to move, enter the move");

        Scanner input = new Scanner(System.in);
        readMove(input.next());
        input.close();
    }

    public void readMove(String moveReq) {
        char figureToMoveLetter = moveReq.charAt(0);
        List<? extends Figure> foundFigures = new ArrayList<>();
        if(figureLetters.contains(figureToMoveLetter)) {
            switch (figureToMoveLetter) {
                case 'K' -> foundFigures = board.getFigures(King.class, moveOfWhite);
                case 'Q' -> foundFigures = board.getFigures(Queen.class, moveOfWhite);
                case 'B' -> foundFigures = board.getFigures(Bishop.class, moveOfWhite);
                case 'N' -> foundFigures = board.getFigures(Knight.class, moveOfWhite);
                case 'R' -> foundFigures = board.getFigures(Rook.class, moveOfWhite);
            }
        } else if(isFile(figureToMoveLetter)){ // a to h
            foundFigures = board.getFigures(Pawn.class, moveOfWhite);
        } else System.out.println("Couldn't resolve move");

        final boolean[] pawnPromotion = {false};


        final String destinationSquareString = moveReq.substring(moveReq.length() - 2);
        foundFigures.removeIf((Predicate<Figure>) figure -> {
            if(figure instanceof Pawn && figure.position.y == 7) {
                pawnPromotion[0] = true;
                int x = BoardLetters.fileNumber(destinationSquareString.charAt(0));
                int y = Character.getNumericValue(moveReq.charAt(1));

                return !figure.possibleMoves().contains(new Position(x, y));
            }
            int x = BoardLetters.fileNumber(destinationSquareString.charAt(0));
            int y = Character.getNumericValue(destinationSquareString.charAt(1));

            return !figure.possibleMoves().contains(new Position(x, y));
        });
        // foundFigures at this point contains requested figures that can be moved to the requested position

        if(foundFigures.size() > 1) {
            if(!(foundFigures.get(0) instanceof Pawn)) {
                int destinationX = Character.getNumericValue(destinationSquareString.charAt(0));
                int destinationY = Character.getNumericValue(destinationSquareString.charAt(1));
                Position destination = new Position(destinationX, destinationY);
                if (foundFigures.size() == 2) { // one departure point is needed
                    if (foundFigures.get(0).position.x == foundFigures.get(1).position.x) {
                        int departureRank = Character.getNumericValue(moveReq.charAt(1));
                        foundFigures.removeIf((figure -> figure.position.y != departureRank));
                        foundFigures.get(0).move(destination);
                    } else {
                        int departureFile = Character.getNumericValue(moveReq.charAt(1));
                        foundFigures.removeIf((figure -> figure.position.x != departureFile));
                        foundFigures.get(0).move(destination);
                    }
                } else { // full departure position is needed

                }
            }
        }

        System.out.println(foundFigures);
        board.render();

    }


    private static boolean isFile(char c) {
        return (c >= 97 && c <= 104);
    }

    private static <T> boolean containsAny(List<T> container, List<T> elements) {
        for(T e : elements) {
            if(container.contains(e)) return true;
        }
        return false;
    }
}
