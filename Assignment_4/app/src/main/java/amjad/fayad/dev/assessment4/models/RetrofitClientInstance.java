package amjad.fayad.dev.assessment4.models;

import amjad.fayad.dev.assessment4.Utils;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;

    /**
     * Obtains or creates, if null, the retrofit instance
     * @return configured retrofit client
     */
    public static Retrofit getInstance() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Utils.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
