package com.locationsaz.sps3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class NewGameActivity extends AppCompatActivity {


    String[] roundsCount = {"4","5","6","7","8","9","10"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_game);
        final Spinner spinner = findViewById(R.id.spinner);
        final TextInputEditText textEname = findViewById(R.id.textEname);
        Button start = findViewById(R.id.btnStart);



       // Get Round Count Contents
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, roundsCount);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        // Start Button
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String player1Name = textEname.getText().toString();
                String roundSelectedCount = (String )spinner.getSelectedItem();

                Intent goToGame = new Intent(NewGameActivity.this, MainActivity.class);
                goToGame.putExtra("PlayerName", player1Name);
                goToGame.putExtra("RoundsCount", roundSelectedCount);
                startActivity(goToGame);


            }
        });




    }

}
