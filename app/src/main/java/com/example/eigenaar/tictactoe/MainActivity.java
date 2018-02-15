package com.example.eigenaar.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Game game;
    String[] Tile_name;
    int[] Tile_id;
    Button[] button_id;

    Handler setDelay;
    Runnable startDelay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize a new game
        game = new Game();
        Tile_name = new String[9];
        Tile_id = new int[9];

        // initialize sleeper
        setDelay = new Handler();

        // name and id of tile's
        for (int i = 0; i < 3; i++){                                            // MAAK V 3 EEN GLOBAL VAR ZOALS IN GAME en 9 hierboven en hieronder
            for (int j = 0; j < 3; j++){
                Tile_name[i*3+j] = "button"+i+j;
                int id = getResources().getIdentifier(Tile_name[i*3+j], "id", getPackageName());
                Tile_id[i*3+j] = id;
            }
        }

        // id button
        // Store all id's only one time (in the same order as the names!)
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
        for (int i = 0; i < 9; i++) {
            drawReally(button_id[i], game.tileContent(i/3, i%3));
        }

    }

    public void drawReally(Button button, Tile tile) {
        // update tile MAAK HIER APARTE FUNCTIE VAN
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

    public void tileClicked(View view) {
        int id = view.getId();

        // Hebt id, zoek nu positie...
        for (int i = 0; i < 9; i++) {
            if (id == Tile_id[i]) {
                drawReally((Button) view, game.draw(i/3, i%3));
                break;
            }
        }

        // Check if someone has won
        GameState state = game.gameWon();
        switch (state) {
            case DRAW:
                Toast.makeText(MainActivity.this, "No one won the game. A new game will start!", Toast.LENGTH_SHORT).show();
                newgame();
                break;
            case PLAYER_ONE:
                Toast.makeText(MainActivity.this, "Player one has won! A new game will start.", Toast.LENGTH_SHORT).show();
                newgame();
                break;
            case PLAYER_TWO:
                Toast.makeText(MainActivity.this, "Player two has won! A new game will start.", Toast.LENGTH_SHORT).show();
                newgame();
                break;
        }
    }

    public void newgame(){
        // make it unclicable
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

    public void resetClicked(View view){
        game = new Game();

        // update GUI
        for (int i = 0; i < 9; i++) {
            drawReally(button_id[i], game.tileContent(i/3, i%3));
            button_id[i].setClickable(true);
        }
    }
}
