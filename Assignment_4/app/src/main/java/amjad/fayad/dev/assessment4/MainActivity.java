package amjad.fayad.dev.assessment4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import amjad.fayad.dev.assessment4.models.Currency;
import amjad.fayad.dev.assessment4.view.CurrencyRVAdapter;
import okhttp3.internal.Util;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvCurrencies;
    private List<Currency> currencies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }



    private List<Currency> getCurrencies()

    /**
     * Performs setup for the recyclerview
     */
    private void recyclerViewSetup() {

        rvCurrencies = findViewById(R.id.rvMain);
        rvCurrencies.setHasFixedSize(true);

        CurrencyRVAdapter adapter = new CurrencyRVAdapter(currencies);
        rvCurrencies.setAdapter(adapter);

        rvCurrencies.setLayoutManager(new LinearLayoutManager(this));
    }
}
