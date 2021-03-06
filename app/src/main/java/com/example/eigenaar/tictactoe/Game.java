package com.example.eigenaar.tictactoe;

import java.io.Serializable;

/**
 * Class for a game.
 */

public class Game implements Serializable{

    // properties
    final private int BOARD_SIZE = 3;
    private Tile[][] board;
    private Boolean playerOneTurn;  // true if player 1's turn, false if player 2's turn
    private int movesPlayed = 0;

    // constructor (given)
    public Game() {
        board = new Tile[BOARD_SIZE][BOARD_SIZE];
        for(int i=0; i<BOARD_SIZE; i++)
            for(int j=0; j<BOARD_SIZE; j++)
                board[i][j] = Tile.BLANK;

        playerOneTurn = true;
    }

    // function which updates a tile if its a valid tile
    public Tile draw(int row, int column) {
        if (board[row][column].equals(Tile.BLANK)) {
            if (playerOneTurn) {
                board[row][column] = Tile.CROSS;
                playerOneTurn = false;
            }
            else {
                board[row][column] = Tile.CIRCLE;
                playerOneTurn = true;
            }
            movesPlayed ++;
        }
        else {
            board[row][column] = Tile.INVALID;
        }
        return board[row][column];
    }

    // function that gets the value of the tile in a specific row and column
    public Tile tileContent(int row, int column){
        return board[row][column];
    }

    public GameState gameWon() {
        // check if it is possible to have a winner
        if (movesPlayed < 5) {
            return GameState.IN_PROGRESS;
        }

        for (int i = 0; i < BOARD_SIZE; i++) {
            // check horizontal
            if (board[i][0].equals(board[i][1]) && board[i][0].equals(board[i][2])){
                if (board[i][0].equals(Tile.CROSS)){
                    return GameState.PLAYER_ONE;
                }
                else if (board[i][0].equals(Tile.CIRCLE)) {
                    return GameState.PLAYER_TWO;
                }
            }
            // check vertical
            if (board[0][i].equals(board[1][i]) && board[0][i].equals(board[2][i])){
                if (board[0][i].equals(Tile.CROSS)){
                    return GameState.PLAYER_ONE;
                }
                else if (board[0][i].equals(Tile.CIRCLE)) {
                    return GameState.PLAYER_TWO;
                }
            }
        }

        // check diagonal
        if ((board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) || (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]))) {
            if (board[1][1].equals(Tile.CROSS)){
                return GameState.PLAYER_ONE;
            }
            else if (board[1][1].equals(Tile.CIRCLE)) {
                return GameState.PLAYER_TWO;
            }
        }
        // check draw
        if (movesPlayed == BOARD_SIZE*BOARD_SIZE){
            return GameState.DRAW;
        }

        return GameState.IN_PROGRESS;
    }
}