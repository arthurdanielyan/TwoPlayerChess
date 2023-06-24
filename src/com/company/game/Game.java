package com.company.game;


public class Game {

    public static final Board board = new Board();
    private static final MoveReader moveReader = new MoveReader(board);

    public static void start() {
        board.resetBoard();

        moveReader.makeMove("e4");
        moveReader.makeMove("e6");
        moveReader.makeMove("d4");
        moveReader.makeMove("c6");
        moveReader.makeMove("e5");
        moveReader.makeMove("d5");
        moveReader.makeMove("Nf3");
        moveReader.makeMove("h6");
        moveReader.makeMove("Nc3");
        moveReader.makeMove("a6");
        moveReader.makeMove("Bd3");
        moveReader.makeMove("b5");
        moveReader.makeMove("Be3");
        moveReader.makeMove("Bb7");
        moveReader.makeMove("b4");
        moveReader.makeMove("Bxb4");
        moveReader.makeMove("Bd2");
        moveReader.makeMove("Qa5");
        moveReader.makeMove("O-O");
        moveReader.makeMove("Bxc3");
        moveReader.makeMove("Bxc3");
        moveReader.makeMove("Qxc3");
        moveReader.makeMove("Qb1");
        moveReader.makeMove("Qa5");
        moveReader.makeMove("Re1");
        moveReader.makeMove("Nd7");
        moveReader.makeMove("Qb3");
        moveReader.makeMove("O-O-O");
        moveReader.makeMove("a4");
        moveReader.makeMove("Kb8");
        moveReader.makeMove("axb5");
        moveReader.makeMove("Qb6");
        moveReader.makeMove("Qa4");
        moveReader.makeMove("cxb5");
        moveReader.makeMove("Bxb5");
        moveReader.makeMove("Qxb5");
        moveReader.makeMove("Qxb5");
        moveReader.makeMove("axb5");
        moveReader.makeMove("Reb1");
        moveReader.makeMove("Bc6");
        moveReader.makeMove("Ra6");
        moveReader.makeMove("Kc7");
        moveReader.makeMove("Ra7+");
        moveReader.makeMove("Kb6");
        moveReader.makeMove("Rba1");
        moveReader.makeMove("Rb8");
        moveReader.makeMove("R7a6+");
        moveReader.makeMove("Kc7");
        moveReader.makeMove("Ra7+");
        moveReader.makeMove("Rb7");
        moveReader.makeMove("Nd2");
        moveReader.makeMove("Rxa7");
        moveReader.makeMove("Rxa7+");
        moveReader.makeMove("Bb7");
        moveReader.makeMove("Nb3");
        moveReader.makeMove("Ne7");
        moveReader.makeMove("Na5");
        moveReader.makeMove("Rb8");
        moveReader.makeMove("f3");
        moveReader.makeMove("Kb6");
        moveReader.makeMove("c3");
        moveReader.makeMove("Kxa7");
        moveReader.makeMove("Nb3");
        moveReader.makeMove("Rc8");
        moveReader.makeMove("h3");
        moveReader.makeMove("Rxc3");
        moveReader.makeMove("Kh2");
        moveReader.makeMove("Rxb3");
        moveReader.makeMove("g4");
        moveReader.makeMove("Rxf3");
        moveReader.makeMove("Kg2");
        moveReader.makeMove("Rd3");
        moveReader.makeMove("Kf2");
        moveReader.makeMove("Rxd4");
        moveReader.makeMove("Kf3");
        moveReader.makeMove("Nc6");
        moveReader.makeMove("Kg3");
        moveReader.makeMove("g5");
        moveReader.makeMove("h4");
        moveReader.makeMove("gxh4+");
        moveReader.makeMove("Kxh4");
        moveReader.makeMove("Ndxe5");
        moveReader.makeMove("Kh5");
        moveReader.makeMove("Rxg4");
        moveReader.makeMove("Kxh6");
        moveReader.makeMove("Rh4+");
        moveReader.makeMove("Kg7");
        moveReader.makeMove("f5");
        moveReader.makeMove("Kf6");
        moveReader.makeMove("Rh6+");
        moveReader.makeMove("Kg5");
        moveReader.makeMove("Rh7");
        moveReader.makeMove("Kf4");
        moveReader.makeMove("Rh4+");
        moveReader.makeMove("Kg5");
        moveReader.makeMove("Rh7");
        moveReader.makeMove("Kf4");
        moveReader.makeMove("Rh4+");
        moveReader.makeMove("Kg5");

        moveReader.requestMove();

//        System.out.println(board.findFigureByPosition(new Position(A, 8)).possibleMoves());

    }
}
