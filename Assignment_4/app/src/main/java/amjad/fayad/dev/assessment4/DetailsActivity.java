package amjad.fayad.dev.assessment4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Sets back btn to navbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        EditText currencyInput = findViewById(R.id.et_currency_input);
        Button btnCalculate = findViewById(R.id.btn_calculate);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer rawInput = Integer.parseInt(currencyInput.getText().toString());

                if (rawInput > 0) {

                }
            }
        });
    }

    /**
     * Computes results (BDL, Real Value and Gap) for the respective user input
     * @param vbdl
     * @param realvalue
     * @param amount
     */
    private void computeResults(Integer vbdl, Integer realvalue, Integer amount) {

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
