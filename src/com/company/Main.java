package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println(Game.board.get(0).position.x);
        System.out.println(Game.board.get(0).possibleMoves());
    }
}
