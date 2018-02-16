package com.example.eigenaar.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Game game;
    int[] tile_id;
    Button[] button_id;
    final private int BOARD_SIZE = 3;

    // a delay is needed when the game is finished
    Handler setDelay;
    Runnable startDelay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize a new game
        game = new Game();
        tile_id = new int[BOARD_SIZE*BOARD_SIZE];

        // initialize sleeper
        setDelay = new Handler();

        // id of tiles
        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++){
                int id = getResources().getIdentifier("button"+i+j, "id", getPackageName());
                tile_id[i*BOARD_SIZE+j] = id;
            }
        }

        // id of buttons
        button_id = new Button[]{
                findViewById(R.id.button00),
                findViewById(R.id.button01),
                findViewById(R.id.button02),
                findViewById(R.id.button10),
                findViewById(R.id.button11),
                findViewById(R.id.button12),
                findViewById(R.id.button20),
                findViewById(R.id.button21),
                findViewById(R.id.button22)};
    }

    protected void onSaveInstanceState(Bundle outstate){
        super.onSaveInstanceState(outstate);
        outstate.putSerializable("game_out", game);
    }

    public void onRestoreInstanceState (Bundle inState){
        super.onRestoreInstanceState(inState);
        game = (Game) inState.getSerializable("game_out");
        for (int i = 0; i < BOARD_SIZE*BOARD_SIZE; i++) {
            drawReally(button_id[i], game.tileContent(i/3, i%3));
        }

    }

    // function to change the appearance of a tile
    public void drawReally(Button button, Tile tile) {
        switch(tile) {
            case CROSS:
                button.setText("X");
                button.setBackgroundColor(getResources().getColor(R.color.purple));
                break;
            case CIRCLE:
                button.setText("O");
                button.setBackgroundColor(getResources().getColor(R.color.green));
                break;
            case BLANK:
                button.setText("");
                button.setBackgroundColor(getResources().getColor(R.color.gray));
                break;
            case INVALID:
                Toast.makeText(MainActivity.this, "This tile is already used", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    // function that is called when a tile is clicked
    public void tileClicked(View view) {
        int id = view.getId();

        // find the right tile id and update that tile if it's allowed to
        for (int i = 0; i < BOARD_SIZE*BOARD_SIZE; i++) {
            if (id == tile_id[i]) {
                drawReally((Button) view, game.draw(i/3, i%3));
                break;
            }
        }

        // check if someone has won or that there is a draw
        GameState state = game.gameWon();
        switch (state) {
            case DRAW:
                Toast.makeText(MainActivity.this, "No one won the game. A new game will start!", Toast.LENGTH_SHORT).show();
                newGame();
                break;
            case PLAYER_ONE:
                Toast.makeText(MainActivity.this, "Player one has won! A new game will start.", Toast.LENGTH_SHORT).show();
                newGame();
                break;
            case PLAYER_TWO:
                Toast.makeText(MainActivity.this, "Player two has won! A new game will start.", Toast.LENGTH_SHORT).show();
                newGame();
                break;
        }
    }

    // function that is called when a new game should start
    public void newGame(){
        // make all tiles unclickable
        for (int i = 0; i < button_id.length; i++){
            button_id[i].setClickable(false);
        }

        // start new game after a delay
        startDelay = new Runnable() {
            @Override
            public void run() {
                resetClicked(findViewById(R.id.buttonRESET));
            }
        };
        setDelay.postDelayed(startDelay, 2000);
    }

    // function that is called when the reset button is clicked or when a new game should start
    public void resetClicked(View view){
        game = new Game();

        // update GUI
        for (int i = 0; i < BOARD_SIZE*BOARD_SIZE; i++) {
            drawReally(button_id[i], game.tileContent(i/3, i%3));
            button_id[i].setClickable(true);
        }
    }
}