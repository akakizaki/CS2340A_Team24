package com.example.team24dungeoncrawler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Player player;
    private String selectedDifficulty;
    private double characterNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText textInput = findViewById(R.id.editTextInput);
        Spinner difficultySpinner = findViewById(R.id.difficultySpinner);
        Button validateButton = findViewById(R.id.buttonValidate);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.difficulty_levels,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(adapter);

        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text from the EditText and selected difficulty from Spinner
                String inputText = textInput.getText().toString();
                selectedDifficulty = difficultySpinner.getSelectedItem().toString();
                RadioGroup characterRadioGroup = findViewById(R.id.characterSelect);
                if (characterRadioGroup.getCheckedRadioButtonId() == R.id.character1) {
                    characterNumber = 1;
                } else if (characterRadioGroup.getCheckedRadioButtonId() == R.id.character2) {
                    characterNumber = 2;
                } else if (characterRadioGroup.getCheckedRadioButtonId() == R.id.character3) {
                    characterNumber = 3;
                }
                // Check if the input is not null and has no leading/trailing whitespace
                if (inputText != null && !(inputText.trim().isEmpty())) {
                    // Input is valid, instantiate the Player class with the name and difficulty
                    player = new Player(inputText, selectedDifficulty);
                    startGame(v);

                } else {
                    // Input is invalid
                    Toast.makeText(MainActivity.this,
                            "Input is invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void startGame(View view) {
        Intent game = new Intent(this, MainGameActivity.class);
        game.putExtra("difficulty", selectedDifficulty);
        game.putExtra("name", player.getName());
        game.putExtra("characterNumber", characterNumber);
        startActivity(game);
        finish();
    }
}