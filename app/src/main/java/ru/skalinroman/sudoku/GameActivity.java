package ru.skalinroman.sudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    TextView title;
    private GridView myGridView;
    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9;
    private String selectedButton = "n1";
    private Game myGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        myGame = new Game(this);
        getUIItems();

        myGridView.setNumColumns(9);
        myGridView.setEnabled(true);
        myGridView.setAdapter(myGame);

        myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                myGame.setNumber(position, selectedButton);
                if (myGame.checkRepeatedValues(selectedButton)) {
                    Toast.makeText(getApplicationContext(), "You have repeated value", Toast.LENGTH_SHORT).show();
                }
                if (myGame.checkWinner()) {
                    Toast.makeText(getApplicationContext(), "You win!", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }

    private void getUIItems() {
        myGridView = (GridView) findViewById(R.id.field);

        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);
        b5 = (Button) findViewById(R.id.b5);
        b6 = (Button) findViewById(R.id.b6);
        b7 = (Button) findViewById(R.id.b7);
        b8 = (Button) findViewById(R.id.b8);
        b9 = (Button) findViewById(R.id.b9);

        title = (TextView) findViewById(R.id.gameTitle);
    }

    public void setOnClickListener() {
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.b1:
                selectedButton = "n1";
                break;
            case R.id.b2:
                selectedButton = "n2";
                break;
            case R.id.b3:
                selectedButton = "n3";
                break;
            case R.id.b4:
                selectedButton = "n4";
                break;
            case R.id.b5:
                selectedButton = "n5";
                break;
            case R.id.b6:
                selectedButton = "n6";
                break;
            case R.id.b7:
                selectedButton = "n7";
                break;
            case R.id.b8:
                selectedButton = "n8";
                break;
            case R.id.b9:
                selectedButton = "n9";
                break;
        }
    }
}