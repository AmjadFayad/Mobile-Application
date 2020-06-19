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

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import amjad.fayad.dev.assessment4.models.Currency;
import amjad.fayad.dev.assessment4.models.CurrencyDB;
import amjad.fayad.dev.assessment4.models.CurrencyViewModel;
import amjad.fayad.dev.assessment4.utils.Utils;
import amjad.fayad.dev.assessment4.view.CurrencyRVAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvCurrencies;
    private CurrencyRVAdapter adapter;

    private List<Currency> currencies = new ArrayList<>();

    public static CurrencyDB db;
    private CurrencyViewModel currencyViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewSetup();
        boolean isConnected = getConnectionState();

        dbSetup();

        if (isConnected) {
            getCurrenciesFromAPI();
        } else {
            getCurrenciesFromDB();
            if (currencies == null || currencies.isEmpty()) {
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
     * Performs setup for the local db
     */
    private void dbSetup() {
        db = Room.databaseBuilder(getApplicationContext(), CurrencyDB.class, "currencydb").build();
        currencyViewModel = new ViewModelProvider(this).get(CurrencyViewModel.class);
    }

    /**
     * Performs a Volley request to the API
     */
    private void getCurrenciesFromAPI() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Utils.BASE_URL,
                response -> {
                    try {
                        parseContent(response);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_SHORT).show());

        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue.add(jsonArrayRequest);
    }

    /**
     * Parses response obj
     * @param jsonArray JSON array from response
     * @throws JSONException in case of error
     */
    private void parseContent(JSONArray jsonArray) throws JSONException {

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            Gson gson = new Gson();
            Currency c = gson.fromJson(obj.toString(), Currency.class);
            currencies.add(c);
        }

        setGapAndImageToCurrencies(currencies);
        saveCurrenciesToDB(currencies);

        rvCurrencies.setAdapter(new CurrencyRVAdapter(currencies));
        rvCurrencies.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    /**
     * Computes gap per currency based on vbdl and real value
     * Attaches the corresponding image to each currency
     * @param currencies to get each currency obj
     */
    private void setGapAndImageToCurrencies(List<Currency> currencies) {
        List<Integer> images = setImages();
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
    private List<Integer> setImages() {

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
            if (currencyViewModel.getAllCurrencies().getValue() == null) {
                currencyViewModel.create(c);
            }
        }
    }

    /**
     * Retrieves currencies stores in local db
     * @return list of currencies
     */
    private void getCurrenciesFromDB() {

        currencies = currencyViewModel.getAllCurrencies().getValue();

        if (currencies == null || currencies.isEmpty()) {
            Toast.makeText(getApplicationContext(), "No available currencies", Toast.LENGTH_SHORT).show();
        } else {
            if (adapter == null) {
                adapter = new CurrencyRVAdapter(currencies);
                rvCurrencies.setAdapter(adapter);
            } else {
                adapter.appendCurrencies(currencies);
            }
        }

    }
}
