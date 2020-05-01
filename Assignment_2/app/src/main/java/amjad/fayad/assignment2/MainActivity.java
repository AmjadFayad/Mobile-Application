package amjad.fayad.assignment2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = spinnerSetup();
        editTexts = editTextsSetup();

        tryCount = 0;
    }

    private Spinner spinnerSetup() {
        Spinner spinner = findViewById(R.id.spinnerGameType);
        String[] spinnerOptions = new String[] {"In Order", "Random"};

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerOptions);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(spinnerAdapter);

        return spinner;
    }

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

    public void play(View v) {

        while (tryCount < 5) {

            int[] lotteryNumbers = generateNumbers();
            int[] userNumbers = getEditTextsInput(editTexts);

            String gameType = spinner.getSelectedItem().toString();
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
                        Toast.makeText(getApplicationContext(), "Please try again. You have " + (tryCount+1) + " tries left", Toast.LENGTH_LONG).show();
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
                        Toast.makeText(getApplicationContext(), "Please try again. You have " + (tryCount+1) + " tries left", Toast.LENGTH_LONG).show();
                    }

                    break;
            }

            tryCount++;
        }

    }

    private int[] getEditTextsInput(EditText[] editTexts) {

        int[] userNumbers = new int[6];
        int i = 0;

        for (EditText editText : editTexts) {
            if (editText.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please fill in all 6 numbers", Toast.LENGTH_SHORT).show();
                break;
            } else {

                if (i == 0)
                    userNumbers[i] = Integer.parseInt(editText.getText().toString());

                if (i > 0 && Integer.parseInt(editText.getText().toString()) != userNumbers[i-1])
                    userNumbers[i] = Integer.parseInt(editText.getText().toString());

                i++;
            }
        }

        return userNumbers;
    }

    public void resetGame(View v) {

        for (EditText x : editTexts) {
            x.setText("");
        }

        tryCount = 0;
    }
}
