package Models;

import java.util.Scanner;

public class Human extends Player{

    public Human(String name, Character symbol, int id) {
        super(name, symbol, id);
    }

    @Override
    public Cell makeMove(Player player, Board board) {
        Scanner sc = new Scanner(System.in);
        System.out.println("What's your next move?");
        int row = sc.nextInt();
        int col = sc.nextInt();
        return new Cell(row, col);
    }
}
