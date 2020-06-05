package amjad.fayad.dev.assessment4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import okhttp3.internal.Util;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    /**
     * Performs the Retrofit setup
     * @return retrofit with base URL
     */
    private Retrofit retrofitSetup() {
        return new Retrofit.Builder()
                .baseUrl(Utils.BASE_URL)
                .build();
    }
}
