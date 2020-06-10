package amjad.fayad.dev.assessment4.models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * ViewModel class for Currency entity
 * Core part of Android Architecture components and used for db communication
 */
public class CurrencyViewModel extends AndroidViewModel {

    private CurrencyRepository repository;
    private LiveData<List<Currency>> allCurrencies;

    public CurrencyViewModel(@NonNull Application application) {
        super(application);

        repository = new CurrencyRepository(application);
        allCurrencies = repository.getAllCurrencies();
    }

    public void create(Currency currency) {
        repository.create(currency);
    }

    public List<Currency> getAllCurrencies() {
        return (List<Currency>) allCurrencies;
    }
}
