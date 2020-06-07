package amjad.fayad.dev.assessment4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    private String currencyName;
    private Integer bdlVal;
    private Integer realVal;
    private Integer image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Sets back btn to navbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get Intent data
        getData();

        TextView tvCurrencyName = findViewById(R.id.tv_currency_details);
        tvCurrencyName.setText(currencyName);

        ImageView currencyImage = findViewById(R.id.iv_currency_details);
        Picasso.get().load(image).into(currencyImage);

        EditText currencyInput = findViewById(R.id.et_currency_input);
        Button btnCalculate = findViewById(R.id.btn_calculate);

        LinearLayout resultsLayout = findViewById(R.id.ll_results);
        TextView tvBdl = findViewById(R.id.tv_vbdl_details);
        TextView tvRealVal = findViewById(R.id.tv_real_details);
        TextView tvGap = findViewById(R.id.tv_gap_details);

        btnCalculate.setOnClickListener(v -> {
            String rawInput = currencyInput.getText().toString();

            if (rawInput.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please input a valid amount", Toast.LENGTH_SHORT).show();
            } else {
                resultsLayout.setVisibility(View.VISIBLE);
                setResults(tvBdl, tvRealVal, tvGap);
            }
        });
    }

    /**
     * Obtains intent data
     */
    private void getData() {
        currencyName = getIntent().getStringExtra("name");
        bdlVal = getIntent().getIntExtra("vbdl", 0);
        realVal = getIntent().getIntExtra("real", 0);
        image = getIntent().getIntExtra("image", R.drawable.coin);
    }

    /**
     * Sets values obtained from computeResults() to the xml widgets
     * @param bdl tv
     * @param tvRealVal tv
     * @param gap tv
     */
    private void setResults(TextView bdl, TextView tvRealVal, TextView gap) {
        bdl.setText(getResources().getString(R.string.price_according_to_bdl) + " " + bdlVal);
        tvRealVal.setText(getResources().getString(R.string.real_price) + " " + realVal);
        gap.setText(getResources().getString(R.string.gap) + " " + (realVal - bdlVal));
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
