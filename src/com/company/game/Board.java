package com.company.game;


import com.company.core.BoardLetters;
import com.company.core.Position;
import com.company.core.exceptions.OccupiedSquareException;
import com.company.core.move_information_wrappers.Move;
import com.company.figures.Figure;
import com.company.figures.figure_impls.*;

import java.util.ArrayList;
import java.util.List;

import static com.company.core.BoardLetters.*;

public class Board {

    public final List<Figure> figures = new ArrayList<>();
    private final List<Move> moves = new ArrayList<>();
    private Pawn enPassantablePawn = null;
    public boolean mated = false;
    public boolean moveOfWhite = true;

    public void addFigure(Figure figure) throws OccupiedSquareException {
        if (findFigureByPosition(figure.position) == null) {
            figures.add(figure);
        } else {
            throw new OccupiedSquareException(figure.position);
        }
    }

    public void onMove(Move move) {
        if(enPassantablePawn != null) enPassantablePawn.enPassantable = false;
        if (move.movedFigure() instanceof Pawn) {
            if(move.movedFigure().isWhite) {
                if(move.movedFigure().position.y == 4 && move.from().y == 2) {
                    enPassantablePawn = (Pawn) move.movedFigure();
                    ((Pawn) move.movedFigure()).enPassantable = true;
                } else ((Pawn) move.movedFigure()).enPassanter = move.movedFigure().position.y == 5;
            } else {
                if(move.movedFigure().position.y == 5  && move.from().y == 7) {
                    enPassantablePawn = (Pawn) move.movedFigure();
                    ((Pawn) move.movedFigure()).enPassantable = true;
                } else ((Pawn) move.movedFigure()).enPassanter = move.movedFigure().position.y == 4;
            }
        }
        King lostKing = getKing(!moveOfWhite);
        if(lostKing.checkers().size() != 0 && lostKing.possibleMoves().size() == 0) { // under a check and has nowhere to go
            if(lostKing.checkers().size() > 1) {
                mated = true;
            } else {
                boolean canBeCoveredOrTaken = false;
                var possibleCovers = lostKing.possibleCovers();
                var figuresCopy = new ArrayList<>(figures);
                Position checkerPosition = lostKing.checkers().get(0).position;
                // throws concurrent modification without copying
                for(Figure f : figuresCopy) {
                    List<Position> fMoves = f.possibleMoves();
                    if (f.isWhite == !moveOfWhite && (containsAny(fMoves, possibleCovers) || fMoves.contains(checkerPosition))) {
                        canBeCoveredOrTaken = true;
                        break;
                    }
                }
                if(!canBeCoveredOrTaken) mated = true;
            }
        }

        moves.add(move);
        moveOfWhite = !moveOfWhite;
    }

    public void takeback() {
        mated = false;
        Move lastMove = moves.get(moves.size()-1);
        if(lastMove.castle() != null) {
            if(lastMove.castle().shortSide()) {
                if(lastMove.movedFigure().isWhite) {
                    getKing(true).position = new Position(E, 1);
                    this.findFigureByPosition(new Position(F, 1)).position = new Position(H, 1);
                } else {
                    getKing(false).position = new Position(E, 8);
                    this.findFigureByPosition(new Position(F, 8)).position = new Position(H, 8);
                }
            } else {
                if(lastMove.movedFigure().isWhite) {
                    getKing(true).position = new Position(E, 1);
                    this.findFigureByPosition(new Position(D, 1)).position = new Position(A, 1);
                } else {
                    getKing(false).position = new Position(E, 8);
                    this.findFigureByPosition(new Position(D, 8)).position = new Position(A, 8);
                }
            }
            return;
        }
        if(lastMove.promotedTo() != null) {
            this.removeFigure(lastMove.promotedTo());
            if(lastMove.capturedFigure() != null) {
                try {
                    this.addFigure(lastMove.capturedFigure());
                } catch (OccupiedSquareException ignore) {}
            }
            try {
                this.addFigure(lastMove.movedFigure());
            } catch (OccupiedSquareException ignore) {}
            return;
        }
        // if not a castle and not a pawn promotion
        lastMove.movedFigure().position = lastMove.from();
        Figure captured = lastMove.capturedFigure();
        if(captured != null) {
            try {
                this.addFigure(captured);
            } catch (OccupiedSquareException ignore) {}
        }
        moveOfWhite = !moveOfWhite;
    }

    public void removeFigure(Figure figure) {
        figures.remove(figure);
    }

    public King getKing(boolean white) {
        for(Figure f : figures) {
            if(f instanceof King && f.isWhite == white) {
                return (King) f;
            }
        }
        return null;
    }


    @SuppressWarnings("unchecked")
    public <T extends Figure> List<T> getFigures(Class<T> figureType, boolean isWhite) {
        List<T> reqFigures = new ArrayList<>();
        for(Figure f : this.figures) {
            if(f.isWhite == isWhite && f.getClass().equals(figureType)) {
                reqFigures.add((T) f);
            }
        }

        return reqFigures;
    }

    /** null if is empty */
    public Figure findFigureByPosition(Position position) {
        for(Figure f : figures) {
            if (f.position.equals(position)) {
                return f;
            }
        }
        return null;
    }


    public void resetBoard() { // generated by ChatGPT
        mated = false;
        moveOfWhite = true;
        figures.clear();
        // White pieces
        Figure whiteKing = new King(new Position(E, 1), true);
        Figure whiteQueen = new Queen(new Position(BoardLetters.D, 1), true);
        Figure whiteRook1 = new Rook(new Position(BoardLetters.A, 1), true);
        Figure whiteRook2 = new Rook(new Position(H, 1), true);
        Figure whiteBishop1 = new Bishop(new Position(BoardLetters.C, 1), true);
        Figure whiteBishop2 = new Bishop(new Position(BoardLetters.F, 1), true);
        Figure whiteKnight1 = new Knight(new Position(BoardLetters.B, 1), true);
        Figure whiteKnight2 = new Knight(new Position(BoardLetters.G, 1), true);
        Figure whitePawn1 = new Pawn(new Position(BoardLetters.A, 2), true);
        Figure whitePawn2 = new Pawn(new Position(BoardLetters.B, 2), true);
        Figure whitePawn3 = new Pawn(new Position(BoardLetters.C, 2), true);
        Figure whitePawn4 = new Pawn(new Position(BoardLetters.D, 2), true);
        Figure whitePawn5 = new Pawn(new Position(E, 2), true);
        Figure whitePawn6 = new Pawn(new Position(BoardLetters.F, 2), true);
        Figure whitePawn7 = new Pawn(new Position(BoardLetters.G, 2), true);
        Figure whitePawn8 = new Pawn(new Position(H, 2), true);

        // Black pieces
        Figure blackKing = new King(new Position(E, 8), false);
        Figure blackQueen = new Queen(new Position(BoardLetters.D, 8), false);
        Figure blackRook1 = new Rook(new Position(BoardLetters.A, 8), false);
        Figure blackRook2 = new Rook(new Position(H, 8), false);
        Figure blackBishop1 = new Bishop(new Position(BoardLetters.C, 8), false);
        Figure blackBishop2 = new Bishop(new Position(BoardLetters.F, 8), false);
        Figure blackKnight1 = new Knight(new Position(BoardLetters.B, 8), false);
        Figure blackKnight2 = new Knight(new Position(BoardLetters.G, 8), false);
        Figure blackPawn1 = new Pawn(new Position(BoardLetters.A, 7), false);
        Figure blackPawn2 = new Pawn(new Position(BoardLetters.B, 7), false);
        Figure blackPawn3 = new Pawn(new Position(BoardLetters.C, 7), false);
        Figure blackPawn4 = new Pawn(new Position(BoardLetters.D, 7), false);
        Figure blackPawn5 = new Pawn(new Position(E, 7), false);
        Figure blackPawn6 = new Pawn(new Position(BoardLetters.F, 7), false);
        Figure blackPawn7 = new Pawn(new Position(BoardLetters.G, 7), false);
        Figure blackPawn8 = new Pawn(new Position(H, 7), false);

        // Add all figures to the list
        try {
            // White pieces
            this.addFigure(whiteKing);
            this.addFigure(whiteQueen);
            this.addFigure(whiteRook1);
            this.addFigure(whiteRook2);
            this.addFigure(whiteBishop1);
            this.addFigure(whiteBishop2);
            this.addFigure(whiteKnight1);
            this.addFigure(whiteKnight2);
            this.addFigure(whitePawn1);
            this.addFigure(whitePawn2);
            this.addFigure(whitePawn3);
            this.addFigure(whitePawn4);
            this.addFigure(whitePawn5);
            this.addFigure(whitePawn6);
            this.addFigure(whitePawn7);
            this.addFigure(whitePawn8);

            // Black pieces
            this.addFigure(blackKing);
            this.addFigure(blackQueen);
            this.addFigure(blackRook1);
            this.addFigure(blackRook2);
            this.addFigure(blackBishop1);
            this.addFigure(blackBishop2);
            this.addFigure(blackKnight1);
            this.addFigure(blackKnight2);
            this.addFigure(blackPawn1);
            this.addFigure(blackPawn2);
            this.addFigure(blackPawn3);
            this.addFigure(blackPawn4);
            this.addFigure(blackPawn5);
            this.addFigure(blackPawn6);
            this.addFigure(blackPawn7);
            this.addFigure(blackPawn8);

        } catch(OccupiedSquareException ignore) {}
    }

    public void render() {
        if(moveOfWhite) {
            for (int y = 8; y >= 1; y--) {
                for (int x = 1; x <= 8; x++) {
                    Figure f = findFigureByPosition(new Position(x, y));
                    if (f != null) System.out.print(f.figureChar);
                    else {
                        char square = '⬛'; //,'⬜'
                        if ((x + y) % 2 == 0) square = '⬜';
                        System.out.print(square + " ");
                    }
                }
                System.out.println();
            }
        } else {
            for (int y = 1; y <= 8; y++) {
                for (int x = 8; x >= 1; x--) {
                    Figure f = findFigureByPosition(new Position(x, y));
                    if (f != null) System.out.print(f.figureChar);
                    else {
                        char square = '⬛'; //,'⬜'
                        if ((x + y) % 2 == 0) square = '⬜';
                        System.out.print(square + " ");
                    }
                }
                System.out.println();
            }
        }
    }

    private static <T> boolean containsAny(List<T> container, List<T> elements) {
        for(T e : elements) {
            if(container.contains(e)) return true;
        }
        return false;
    }
}
