package amjad.fayad.assignment2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static EditText[] editTexts;
    private Spinner spinner;
    private int tryCount;
    private int[] lotteryNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = spinnerSetup();
        editTexts = editTextsSetup();
        tryCount = 5;

        lotteryNumbers = generateNumbers();

        Button btnPlay = findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean validation = validateInput(editTexts);
                if (validation) {
                    play(lotteryNumbers);
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid input. Fill in each space with a number ranging from 1 - 42", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Performs setup for the spinner
     * 2 String options
     * Adapter and layout
     * @return a spinner with its setup
     */
    private Spinner spinnerSetup() {
        Spinner spinner = findViewById(R.id.spinnerGameType);
        String[] spinnerOptions = new String[] {"In Order", "Random"};

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerOptions);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(spinnerAdapter);

        return spinner;
    }

    /**
     * Gets all edit texts in the parent view
     * @return Array of Edit Texts
     */
    private EditText[] editTextsSetup() {
        EditText[] editTexts = new EditText[6];
        LinearLayout holderView = findViewById(R.id.etHolder);

        for (int i = 0; i < 6; i++) {

            View v = holderView.getChildAt(i);
            if (v instanceof EditText) {
                editTexts[i] = (EditText) v;
            }
        }

        return editTexts;
    }

    /**
     * Generates an array of 6 random integers between 1 and 42
     * @return array of 6 ints
     */
    private int[] generateNumbers() {
        Random r = new Random();
        int[] nums = new int[6];

        for (int i = 0; i < 6; i++) {
            int x = r.nextInt(42);

            if (i == 0) {
                nums[i] = x;
            }

            if (i > 0 && x != nums[i-1]){
                nums[i] = x;
            }
        }

        return nums;
    }

    /**
     * Validates input from Edit Texts
     * Not empty and not greater than 42
     * @param editTexts to be validated
     * @return a boolean, true for valid input and false for invalid
     */
    private boolean validateInput(EditText[] editTexts) {

        boolean validation = true;

        for (EditText x : editTexts) {
            if (x.getText().toString().trim().isEmpty() || Integer.parseInt(x.getText().toString()) > 42) {
                validation = false;
            }
        }

        return validation;
    }

    /**
     * Obtains input from Edit Texts
     * @param editTexts to obtain numbers
     * @return int array with the numbers from the Edit Texts
     */
    private int[] getEditTextsInput(EditText[] editTexts) {

        int[] userNumbers = new int[6];
        int i = 0;

        for (EditText editText : editTexts) {

            if (i == 0)
                userNumbers[i] = Integer.parseInt(editText.getText().toString());

            if (i > 0 && Integer.parseInt(editText.getText().toString()) != userNumbers[i-1])
                userNumbers[i] = Integer.parseInt(editText.getText().toString());

            i++;
        }

        return userNumbers;
    }

    /**
     *
     * @param lotteryNumbers to compare with the
     */
    private void play(int[] lotteryNumbers) {

        while (tryCount > 0) {

            String gameType = spinner.getSelectedItem().toString();
            int[] userNumbers = getEditTextsInput(editTexts);

            int correctNumbers;

            switch (gameType) {
                case "In Order" :
                    correctNumbers = 0;

                    for (int i = 0; i < 6; i++) {
                        if (lotteryNumbers[i] == userNumbers[i]) {
                            correctNumbers++;
                        }
                    }

                    if (correctNumbers == 6) {
                        Toast.makeText(getApplicationContext(), "You guessed all numbers! You win $100,000!!!", Toast.LENGTH_LONG).show();
                    } else if (correctNumbers == 5) {
                        Toast.makeText(getApplicationContext(), "You guessed 5! You win $25,000!!!", Toast.LENGTH_LONG).show();
                    } else if (correctNumbers == 4) {
                        Toast.makeText(getApplicationContext(), "You guessed 4! You win $5,000!!!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Please try again. You have " + (5-(tryCount+1)) + " tries left", Toast.LENGTH_LONG).show();
                    }

                    break;

                case "Random" :

                    correctNumbers = 0;

                    for (int i = 0; i < 6; i++) {
                        if (lotteryNumbers[i] == userNumbers[i]) {
                            correctNumbers++;
                        }
                    }

                    if (correctNumbers == 6) {
                        Toast.makeText(getApplicationContext(), "You guessed all numbers! You win $50,000!!!", Toast.LENGTH_LONG).show();
                    } else if (correctNumbers == 5) {
                        Toast.makeText(getApplicationContext(), "You guessed 5! You win $25,000!!!", Toast.LENGTH_LONG).show();
                    } else if (correctNumbers == 4) {
                        Toast.makeText(getApplicationContext(), "You guessed 4! You win $5,000!!!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Please try again. You have " + (5-(tryCount+1)) + " tries left", Toast.LENGTH_LONG).show();
                    }

                    break;
            }

            tryCount--;
        }
    }

    /**
     * Reset for Button
     * @param v associated with the button
     */
    public void resetGame(View v) {
        resetGame();
    }

    /**
     * Clears all Edit Text fields and resets try count to 5
     */
    private void resetGame() {
        for (EditText x : editTexts) {
            x.setText("");
        }

        lotteryNumbers = generateNumbers();
        tryCount = 5;
    }
}
