package com.company;

import com.company.core.BoardLetters;
import com.company.core.Position;
import com.company.figures.Figure;
import com.company.figures.figur_impls.*;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private static final char [][] board = {
        {'⬜','⬛','⬜','⬛','⬜','⬛','⬜','⬛'},
        {'⬛','⬜','⬛','⬜','⬛','⬜','⬛','⬜'},
        {'⬜','⬛','⬜','⬛','⬜','⬛','⬜','⬛'},
        {'⬛','⬜','⬛','⬜','⬛','⬜','⬛','⬜'},
        {'⬜','⬛','⬜','⬛','⬜','⬛','⬜','⬛'},
        {'⬛','⬜','⬛','⬜','⬛','⬜','⬛','⬜'},
        {'⬜','⬛','⬜','⬛','⬜','⬛','⬜','⬛'},
        {'⬛','⬜','⬛','⬜','⬛','⬜','⬛','⬜'}
    };

    public static List<Figure> figures = new ArrayList<>();

    static {
        resetBoard();
        render();
        System.out.println(figures.get(17).figureChar);
    }

    public static void resetBoard() {
        // WHITE FIGURES
        Figure whiteRook1 = new Rook(true);
        whiteRook1.position = new Position(BoardLetters.A, 1);
        Figure whiteRook2 = new Rook(true);
        whiteRook2.position = new Position(BoardLetters.H, 1);

        Figure whiteKnight1 = new Knight(true);
        whiteKnight1.position = new Position(BoardLetters.B, 1);
        Figure whiteKnight2 = new Knight(true);
        whiteKnight2.position = new Position(BoardLetters.G, 1);

        Figure whiteBishop1 = new Bishop(true);
        whiteBishop1.position = new Position(BoardLetters.C, 1);
        Figure whiteBishop2 = new Bishop(true);
        whiteBishop2.position = new Position(BoardLetters.F, 1);

        Figure whiteQueen = new Queen(true);
        whiteQueen.position = new Position(BoardLetters.D, 1);

        Figure whiteKing = new King(true);
        whiteKing.position = new Position(BoardLetters.E, 1);

        // BLACK FIGURES

        Figure blackRook1 = new Rook(false);
        blackRook1.position = new Position(BoardLetters.A, 8);
        Figure blackRook2 = new Rook(false);
        blackRook2.position = new Position(BoardLetters.H, 8);

        Figure blackKnight1 = new Knight(false);
        blackKnight1.position = new Position(BoardLetters.B, 8);
        Figure blackKnight2 = new Knight(false);
        blackKnight2.position = new Position(BoardLetters.G, 8);

        Figure blackBishop1 = new Bishop(false);
        blackBishop1.position = new Position(BoardLetters.C, 8);
        Figure blackBishop2 = new Bishop(false);
        blackBishop2.position = new Position(BoardLetters.F, 8);

        Figure blackQueen = new Queen(false);
        blackQueen.position = new Position(BoardLetters.D, 8);

        Figure blackKing = new King(false);
        blackKing.position = new Position(BoardLetters.E, 8);

        // PAWNS
        Figure whitePawn1 = new Pawn(true);
        whitePawn1.position = new Position(BoardLetters.A, 2);
        Figure whitePawn2 = new Pawn(true);
        whitePawn1.position = new Position(BoardLetters.B, 2);
        Figure whitePawn3 = new Pawn(true);
        whitePawn1.position = new Position(BoardLetters.C, 2);
        Figure whitePawn4 = new Pawn(true);
        whitePawn1.position = new Position(BoardLetters.D, 2);
        Figure whitePawn5 = new Pawn(true);
        whitePawn1.position = new Position(BoardLetters.E, 2);
        Figure whitePawn6 = new Pawn(true);
        whitePawn1.position = new Position(BoardLetters.F, 2);
        Figure whitePawn7 = new Pawn(true);
        whitePawn1.position = new Position(BoardLetters.G, 2);
        Figure whitePawn8 = new Pawn(true);
        whitePawn1.position = new Position(BoardLetters.H, 2);

        Figure blackPawn1 = new Pawn(false);
        blackPawn1.position = new Position(BoardLetters.A, 7);
        Figure blackPawn2 = new Pawn(false);
        blackPawn1.position = new Position(BoardLetters.B, 7);
        Figure blackPawn3 = new Pawn(false);
        blackPawn1.position = new Position(BoardLetters.C, 7);
        Figure blackPawn4 = new Pawn(false);
        blackPawn1.position = new Position(BoardLetters.D, 7);
        Figure blackPawn5 = new Pawn(false);
        blackPawn1.position = new Position(BoardLetters.E, 7);
        Figure blackPawn6 = new Pawn(false);
        blackPawn1.position = new Position(BoardLetters.F, 7);
        Figure blackPawn7 = new Pawn(false);
        blackPawn1.position = new Position(BoardLetters.G, 7);
        Figure blackPawn8 = new Pawn(false);
        blackPawn1.position = new Position(BoardLetters.H, 7);

        // adding figures to the board
        figures.add(whiteRook1);
        figures.add(whiteRook2);
        figures.add(whiteKnight1);
        figures.add(whiteKnight2);
        figures.add(whiteBishop1);
        figures.add(whiteBishop2);
        figures.add(whiteQueen);
        figures.add(whiteKing);

        figures.add(blackRook1);
        figures.add(blackRook2);
        figures.add(blackKnight1);
        figures.add(blackKnight2);
        figures.add(blackBishop1);
        figures.add(blackBishop2);
        figures.add(blackQueen);
        figures.add(blackKing);

        figures.add(whitePawn1);
        figures.add(whitePawn2);
        figures.add(whitePawn3);
        figures.add(whitePawn4);
        figures.add(whitePawn5);
        figures.add(whitePawn6);
        figures.add(whitePawn7);
        figures.add(whitePawn8);

        figures.add(blackPawn1);
        figures.add(blackPawn2);
        figures.add(blackPawn3);
        figures.add(blackPawn4);
        figures.add(blackPawn5);
        figures.add(blackPawn6);
        figures.add(blackPawn7);
        figures.add(blackPawn8);
    }

    public static void render() {
        for(Figure f : figures) {
            board[f.position.y-1][f.position.x-1] = f.figureChar;
        }
        for (char[] line : board) {
            for (char cell : line) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }
}
