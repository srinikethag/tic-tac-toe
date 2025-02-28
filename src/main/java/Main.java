import Controllers.GameController;
import Models.Board;
import Models.Game;
import Models.GameState;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Game game = GameController.initializeGame();

        GameController gc = new GameController(game);
        gc.boardController.displayBoard();
        while(game.getGameState().equals(GameState.IN_PROGRESS)){

            gc.makeNextMove();
            gc.boardController.displayBoard();
            Scanner sc = new Scanner(System.in);
            System.out.println("Do you want to undo last move?");
            String ans = sc.next();
            if(ans.equalsIgnoreCase("y")){
                gc.undoLastMove();
                System.out.println("Last move is reverted");
                gc.boardController.displayBoard();
            }
        }

        gc.boardController.displayBoard();

        if(game.getGameState().equals(GameState.WON)){
            System.out.println("The winner of the game is " + game.getWinner().getName());
        } else {
            System.out.println("The game has ended in draw");
        }
    }
}
