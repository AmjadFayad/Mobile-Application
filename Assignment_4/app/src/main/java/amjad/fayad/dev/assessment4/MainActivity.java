package amjad.fayad.dev.assessment4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import amjad.fayad.dev.assessment4.api.APIService;
import amjad.fayad.dev.assessment4.models.Currency;
import amjad.fayad.dev.assessment4.models.RetrofitClientInstance;
import amjad.fayad.dev.assessment4.view.CurrencyRVAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private List<Currency> currencies;
    private APIService api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofitSetup();
        getCurrencies();
        recyclerViewSetup();
    }

    /**
     * Performs Httpclient setup for GET request
     */
    private void retrofitSetup() {
        Retrofit retrofit = RetrofitClientInstance.getInstance();
        api = retrofit.create(APIService.class);
    }

    /**
     * Performs the GET request to the API
     * Sets the result to the List of type Currency currencies
     * On failure shows an error message
     */
    private void getCurrencies() {

        Call<List<Currency>> call = api.getCurrencies();
        call.enqueue(new Callback<List<Currency>>() {
            @Override
            public void onResponse(Call<List<Currency>> call, Response<List<Currency>> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null && !response.body().isEmpty()) {
                        currencies = response.body();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Currency>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error fetching currencies, please check your network connection", Toast.LENGTH_LONG ).show();
            }
        });
    }

    /**
     * Performs setup for the recyclerview:
     * Sets:
     * Layout Manager
     * Sets RecyclerView Adapter - custom CurrencyRVAdapter passing the currencies list
     */
    private void recyclerViewSetup() {

        RecyclerView rvCurrencies = findViewById(R.id.rvMain);
        rvCurrencies.setHasFixedSize(true);

        CurrencyRVAdapter adapter = new CurrencyRVAdapter(currencies);
        rvCurrencies.setAdapter(adapter);

        rvCurrencies.setLayoutManager(new LinearLayoutManager(this));
    }
}
