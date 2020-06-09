package amjad.fayad.dev.assessment4.models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import amjad.fayad.dev.assessment4.models.Currency;
import amjad.fayad.dev.assessment4.models.CurrencyRepository;

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

    public LiveData<List<Currency>> getAllCurrencies() {
        return allCurrencies;
    }
}
