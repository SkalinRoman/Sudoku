package ru.skalinroman.sudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button bContinue;
    Button bNewGame;
    Button bAbout;
    Button bExit;

    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getUIItems();
    }

    public void getUIItems() {
        bContinue = (Button) findViewById(R.id.bContinue);
        bNewGame = (Button) findViewById(R.id.bNewGame);
        bAbout = (Button) findViewById(R.id.bAbout);
        bExit = (Button) findViewById(R.id.bExit);

        title = (TextView) findViewById(R.id.title);
    }

    public void continueGame(View view) {

    }

    public void newGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void about(View view) {
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }

    public void exitGame(View view) {
        finish();
    }
}