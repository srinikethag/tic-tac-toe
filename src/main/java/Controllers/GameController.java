package Controllers;

import Models.*;

import java.util.ArrayList;
import java.util.Scanner;

public class GameController {

    Game game;
    public BoardController boardController;

    public GameController(Game game){
        this.game = game;
        this.game.gameState = GameState.IN_PROGRESS;
        this.boardController = new BoardController(this.game.board);
    }

    public static Game initializeGame(){

        System.out.println("Enter the board size: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        ArrayList<Player> playerList = new ArrayList<>();
        for(int i=0; i<n-1; i++){
            playerList.add(getPlayerInfoFromUser(i));
        }

        return new Game(n, playerList);
    }

    private static Player getPlayerInfoFromUser(int i){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Player name and the symbol");
        String name = sc.next();
        String symbol = sc.next();
        System.out.println("Is this a bot player?");
        String ans = sc.next();
        if(ans.equalsIgnoreCase("y")){
            System.out.println("Enter the difficulty level for the bot (1/2/3)");
            int val = sc.nextInt();
            BotDifficultyLevel botDifficultyLevel = switch (val) {
                case 1 -> BotDifficultyLevel.EASY;
                case 2 -> BotDifficultyLevel.MEDIUM;
                default -> BotDifficultyLevel.HARD;
            };
            return new Bot(name, symbol.charAt(0), i+1, botDifficultyLevel);
        }

        return new Human(name, symbol.charAt(0), i+1);
    }


    public void makeNextMove(){

        if(boardController.isFull()){
            game.setDraw();
            return;
        }

        // Step 1: Find the player with next move
        int currPlayerIndex = game.getCurrentPlayerIndex();
        Player currPlayer = game.playerList.get(currPlayerIndex);

        // Step 2: Calls the makeMove method for the corresponding player
        System.out.printf("It's %s's move\n", currPlayer.getName());
        makeMoveForCurrPlayer();

        // Step 3: Check all the winning strategies
        postMoveWinnerCheck();

    }

    public void undoLastMove(){

        // step 1: remove from moves list
        Cell undoCell = game.moves.getLast();
        game.moves.remove(undoCell);

        // step 2: update the board without that cell
        Cell cell = game.board.getCells().get(undoCell.getRow()).get(undoCell.getCol());
        cell.setPlayer(null);
        cell.setCellState(CellState.FREE);

        // step 3: update the current player index
        game.currentPlayerIndex--;
        game.currentPlayerIndex = (game.currentPlayerIndex + game.playerList.size() ) % game.playerList.size();

    }

    /**
     * This method makes the next player decide a move and updates the board
     */
    public void makeMoveForCurrPlayer(){
        Player currPlayer = game.playerList.get(game.currentPlayerIndex);
        Cell cell = currPlayer.makeMove(currPlayer, game.board);

        // Update the board with that move and corresponding validation.
        // if it fails again try to make the move
        try{
            boardController.updateBoard(cell, currPlayer);
            game.moves.add(cell);
        } catch (IllegalArgumentException e){
            System.out.println("Please choose a valid cell");
            makeMoveForCurrPlayer();
        }

    }

    public void postMoveWinnerCheck(){
        boolean isWin = game.getWinningStrategies().stream()
                .anyMatch(winningStrategy -> winningStrategy.isWinning(game));

        if(isWin){
            game.setWinner();
        } else {
            game.currentPlayerIndex++;
            // limit only to number of players
            game.currentPlayerIndex %= game.playerList.size();
        }
    }
}
