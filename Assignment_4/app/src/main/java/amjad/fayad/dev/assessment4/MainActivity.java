package amjad.fayad.dev.assessment4;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import amjad.fayad.dev.assessment4.api.APIService;
import amjad.fayad.dev.assessment4.models.Currency;
import amjad.fayad.dev.assessment4.models.CurrencyDB;
import amjad.fayad.dev.assessment4.models.CurrencyViewModel;
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

    private List<Currency> currencies = new ArrayList<>();
    private APIService api;

    public static CurrencyDB db;
    private CurrencyViewModel currencyViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewSetup();
        boolean isConnected = getConnectionState();

        retrofitSetup();
        dbSetup();

        if (isConnected) {
            getCurrenciesFromAPI();
        } else {
            getCurrenciesFromDB();
            if (currencies.isEmpty()) {
                Toast.makeText(getApplicationContext(), "No available currencies", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Performs setup for the recyclerview:
     * Sets layout manager
     */
    private void recyclerViewSetup() {
        rvCurrencies = findViewById(R.id.rvMain);
        rvCurrencies.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    /**
     * Checks if the app is connected
     * @return true (connected) or false (not connected)
     */
    private boolean getConnectionState() {

        ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNet = cm.getActiveNetworkInfo();

        return activeNet != null && activeNet.isConnected();
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
     * Performs setup for the local db
     */
    private void dbSetup() {
        db = Room.databaseBuilder(getApplicationContext(), CurrencyDB.class, "currencydb").build();
        currencyViewModel = new ViewModelProvider(this).get(CurrencyViewModel.class);
    }

    /**
     * Performs the GET request to the API
     * Sets the result to the List of type Currency currencies
     * On failure shows an error message
     */
    private void getCurrenciesFromAPI() {

        Call<List<Currency>> call = api.getCurrencies();
        call.enqueue(new Callback<List<Currency>>() {
            @Override
            public void onResponse(Call<List<Currency>> call, Response<List<Currency>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null && !response.body().isEmpty()) {
                        currencies = response.body();
                        setGapAndImageToCurrencies(currencies);

                        if (adapter == null) {
                            adapter = new CurrencyRVAdapter(currencies);
                            rvCurrencies.setAdapter(adapter);
                        } else {
                            adapter.appendCurrencies(currencies);
                        }

                        saveCurrenciesToDB(currencies);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Currency>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        "Error fetching currencies, please check your network connection",
                        Toast.LENGTH_LONG ).show();
            }
        });
    }

    /**
     * Retrieves currencies stores in local db
     * @return list of currencies
     */
    private void getCurrenciesFromDB() {

        currencies = (List<Currency>) currencyViewModel.getAllCurrencies();

        if (adapter == null) {
            adapter = new CurrencyRVAdapter(currencies);
            rvCurrencies.setAdapter(adapter);
        } else {
            adapter.appendCurrencies(currencies);
        }
    }

    /**
     * Computes gap per currency based on vbdl and real value
     * Attaches the corresponding image to each currency
     * @param currencies to get each currency obj
     */
    private void setGapAndImageToCurrencies(List<Currency> currencies) {
        List<Integer> images = getImages();
        int i = 0;
        for (Currency c : currencies) {

            c.setGap(c.getRealvalue() - c.getVbdl());
            c.setImage(images.get(i));
            i++;
        }
    }

    /**
     * Fetches local images in a list
     * @return list of images
     */
    private List<Integer> getImages() {

        List<Integer> images = new ArrayList<>();

        int usd = R.drawable.dollar;
        images.add(usd);
        int euro = R.drawable.euro;
        images.add(euro);
        int saudi = R.drawable.saudi;
        images.add(saudi);
        int qatar = R.drawable.qatar;
        images.add(qatar);

        return images;
    }

    /**
     * Stores the list of currencies from the API request to the db
     * @param currencies list of Currency objs
     */
    private void saveCurrenciesToDB(List<Currency> currencies) {
        for (Currency c : currencies) {
            if (currencyViewModel.getAllCurrencies().isEmpty()) {
                currencyViewModel.create(c);
            }
        }
    }

    // VOLLEY ALTERNATIVE AS AN HTTP CLIENT //
    /*
    private void getCurrencies() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Utils.BASE_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            parseContent(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        });

        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue.add(jsonArrayRequest);
    }

    private void parseContent(JSONArray jsonArray) throws JSONException {

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            Gson gson = new Gson();
            Currency c = gson.fromJson(obj.toString(), Currency.class);
            currencies.add(c);
        }

        rvCurrencies.setAdapter(new CurrencyRVAdapter(currencies));
        rvCurrencies.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
     */
}
