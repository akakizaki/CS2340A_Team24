package com.example.team24dungeoncrawler;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Player player;

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
                String selectedDifficulty = difficultySpinner.getSelectedItem().toString();

                // Check if the input is not null and has no leading/trailing whitespace
                if (inputText != null && !inputText.trim().isEmpty()) {
                    // Input is valid, instantiate the Player class with the name and difficulty
                    player = new Player(inputText, selectedDifficulty);
                    Toast.makeText(MainActivity.this,
                            "Player created with name: " + player.getName() +
                                    ", Difficulty: " + selectedDifficulty,
                            Toast.LENGTH_SHORT).show();
                } else {
                    // Input is invalid
                    Toast.makeText(MainActivity.this, "Input is invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}