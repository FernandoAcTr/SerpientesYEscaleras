package model;

public class Test {

    public static void main(String[] args){

        Board board = new Board();

        System.out.println("Casilla del jugador 1: " + board.getCurrentBox(1));
        System.out.println("Casilla del jugador 2: " + board.getCurrentBox(2));

        board.move(5, 1);
        board.move(8, 2);

        System.out.println("Casilla del jugador 1: " + board.getCurrentBox(1));
        System.out.println("Casilla del jugador 2: " + board.getCurrentBox(2));

        board.move(6, 1);
        board.move(10, 2);

        System.out.println("Casilla del jugador 1: " + board.getCurrentBox(1));
        System.out.println("Casilla del jugador 2: " + board.getCurrentBox(2));

    }
}
