package com.example.eigenaar.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Game game;
    String[] Tile_name;
    int[] Tile_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize a new game
        game = new Game();
        Tile_name = new String[9];
        Tile_id = new int[9];

        // name and id of tile's
        for (int i = 0; i < 3; i++){                                            // MAAK V 3 EEN GLOBAL VAR ZOALS IN GAME en 9 hierboven en hieronder
            for (int j = 0; j < 3; j++){
                Tile_name[i*3+j] = "button"+i+j;
                int id = getResources().getIdentifier(Tile_name[i*3+j], "id", getPackageName());
                Tile_id[i*3+j] = id;
            }
        }
    }

    public void tileClicked(View view) {
        int id = view.getId();

        // Hebt id, zoek nu positie...
        for (int i = 0; i < 9; i++) {
            if (id == Tile_id[i]) {
                Tile tile = game.draw(i/3, i%3);

                // update tile
                switch(tile) {
                    case CROSS:
                        ((Button) view).setText("X");
                        break;
                    case CIRCLE:
                        ((Button) view).setText("O");
                        break;
                    case INVALID:
                        Toast.makeText(MainActivity.this, "This tile is already used", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }

        // Check if someone has won
        GameState state = game.gameWon();
        switch (state) {
            case DRAW:
                Toast.makeText(MainActivity.this, "No one won the game. A new game will start!", Toast.LENGTH_SHORT).show();
                game = new Game();
                break;
            case PLAYER_ONE:
                Toast.makeText(MainActivity.this, "Player one has won! A new game will start.", Toast.LENGTH_SHORT).show();
                game = new Game();
                break;
            case PLAYER_TWO:
                Toast.makeText(MainActivity.this, "Player two has won! A new game will start.", Toast.LENGTH_SHORT).show();
                game = new Game();
                break;
        }


    }

    public void resetClicked(){
        game = new Game();                  // APP SLUIT... HOE VOORKOMEN?
    }
}
