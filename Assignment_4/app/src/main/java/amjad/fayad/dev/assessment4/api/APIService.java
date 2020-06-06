package amjad.fayad.dev.assessment4.api;

import java.util.List;

import amjad.fayad.dev.assessment4.models.Currency;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    /**
     * GET request to obtain a List of type Currency from the repository.
     * @return a Call petition
     */
    @GET("currencies")
    Call<List<Currency>> getCurrencies();
}
