package amjad.fayad.dev.assessment4;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import amjad.fayad.dev.assessment4.api.APIService;
import amjad.fayad.dev.assessment4.models.Currency;
import amjad.fayad.dev.assessment4.utils.Utils;
import amjad.fayad.dev.assessment4.view.CurrencyRVAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvCurrencies;
    private CurrencyRVAdapter adapter;

    private List<Currency> currencies;
    private APIService api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewSetup();

        retrofitSetup();
        getCurrencies();
    }

    /**
     * Performs setup for the recyclerview:
     * Sets layout manager
     */
    private void recyclerViewSetup() {
        RecyclerView rvCurrencies = findViewById(R.id.rvMain);
        rvCurrencies.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Performs Httpclient setup for GET request
     */
    private void retrofitSetup() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

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

                        if (adapter == null) {
                            adapter = new CurrencyRVAdapter(currencies);
                        } else {
                            adapter.appendCurrencies(currencies);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Currency>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error fetching currencies, please check your network connection", Toast.LENGTH_LONG ).show();
            }
        });
    }
}
