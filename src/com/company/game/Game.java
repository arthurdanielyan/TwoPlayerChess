package com.company.game;


public class Game {

    public static final Board board = new Board();
    private static final MoveReader moveReader = new MoveReader(board);

    public static void start() {
        board.resetBoard();

        moveReader.makeMove("e4");
        moveReader.makeMove("e5");
        moveReader.makeMove("Nf3");
        moveReader.makeMove("f5");
        moveReader.makeMove("d3");
        moveReader.makeMove("fxe4");
        moveReader.makeMove("dxe4");
        moveReader.makeMove("d6");
        moveReader.makeMove("Nc3");
        moveReader.makeMove("Be6");
        moveReader.makeMove("Bd3");
        moveReader.makeMove("h6");
        moveReader.makeMove("Be3");
        moveReader.makeMove("Nf6");
        moveReader.makeMove("O-O");
        moveReader.makeMove("Nc6");
        moveReader.makeMove("Bb5");
        moveReader.makeMove("Qd7");
        moveReader.makeMove("Re1");
        moveReader.makeMove("a6");
        moveReader.makeMove("Ba4");
        moveReader.makeMove("b5");
        moveReader.makeMove("Bb3");
        moveReader.makeMove("Bxb3");
        moveReader.makeMove("axb3");
        moveReader.makeMove("b4");
        moveReader.makeMove("Nd5");
        moveReader.makeMove("Nxd5");
        moveReader.makeMove("Qxd5");
        moveReader.makeMove("a5");
        moveReader.makeMove("Nh4");
        moveReader.makeMove("Qf7");
        moveReader.makeMove("Qxc6+");
        moveReader.makeMove("Ke7");
        moveReader.makeMove("Qxa8");
        moveReader.makeMove("g5");
        moveReader.makeMove("Nf5+");
        moveReader.makeMove("Ke6");
        moveReader.makeMove("Qd5+");
        moveReader.makeMove("Kf6");
        moveReader.makeMove("Qxf7+");
        moveReader.makeMove("Kxf7");
        moveReader.makeMove("Rxa5");
        moveReader.makeMove("Bg7");
        moveReader.makeMove("Ra7");
        moveReader.makeMove("Rc8");
        moveReader.makeMove("Nxg7");
        moveReader.makeMove("Kxg7");
        moveReader.makeMove("Rea1");
        moveReader.makeMove("Kf7");
        moveReader.makeMove("Ra8");
        moveReader.makeMove("Rxa8");
        moveReader.makeMove("Rxa8");
        moveReader.makeMove("Ke7");
        moveReader.makeMove("Rb8");
        moveReader.makeMove("Kd7");
        moveReader.makeMove("Rxb4");
        moveReader.makeMove("c6");
        moveReader.makeMove("Ra4");
        moveReader.makeMove("d5");
        moveReader.makeMove("exd5");
        moveReader.makeMove("cxd5");
        moveReader.makeMove("b4");
        moveReader.makeMove("Kc6");
        moveReader.makeMove("Ra5");
        moveReader.makeMove("d4");
        moveReader.makeMove("Bd2");
        moveReader.makeMove("e4");
        moveReader.makeMove("c3");
        moveReader.makeMove("dxc3");
        moveReader.makeMove("bxc3");
        moveReader.makeMove("Kb6");
        moveReader.makeMove("f3");
        moveReader.makeMove("exf3");
        moveReader.makeMove("gxf3");
        moveReader.makeMove("h5");
        moveReader.makeMove("Bxg5");
        moveReader.makeMove("Kc6");
        moveReader.makeMove("f4");
        moveReader.makeMove("Kd6");
        moveReader.makeMove("f5");
        moveReader.makeMove("Kd7");
        moveReader.makeMove("f6");
        moveReader.makeMove("Ke8");
        moveReader.makeMove("b5");
        moveReader.makeMove("Kd8");
        moveReader.makeMove("b6");
        moveReader.makeMove("Kc8");
        moveReader.makeMove("b7+");
        moveReader.makeMove("Kxb7");
        moveReader.makeMove("f7");
        moveReader.makeMove("Kb6");
        moveReader.makeMove("Rf5");
        moveReader.makeMove("h4");
        moveReader.makeMove("f8=Q");
        moveReader.makeMove("h3");
        moveReader.makeMove("Qh6+");
        moveReader.makeMove("Kc7");
        moveReader.makeMove("Qxh3");
        moveReader.makeMove("Kd7");
        moveReader.makeMove("Qh6");
        moveReader.makeMove("Ke8");


        moveReader.requestMove();

//        System.out.println(board.findFigureByPosition(new Position(A, 8)).possibleMoves());

    }
}
