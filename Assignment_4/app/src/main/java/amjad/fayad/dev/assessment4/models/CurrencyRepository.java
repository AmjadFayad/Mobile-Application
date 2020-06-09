package amjad.fayad.dev.assessment4.models;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CurrencyRepository {

    private CurrencyDAO currencyDAO;
    private LiveData<List<Currency>> allCurrencies;

    CurrencyRepository(Application application) {
        CurrencyDB database = CurrencyDB.getInstance(application);
        currencyDAO = database.currencyDAO();

        allCurrencies = currencyDAO.getAllCurrencies();
    }

    void create(Currency currency) {
        new InsertCurrencyAsync(currencyDAO).execute(currency);
    }

    LiveData<List<Currency>> getAllCurrencies() {
        return allCurrencies;
    }

    private static class InsertCurrencyAsync extends AsyncTask<Currency, Void, Void> {

        private CurrencyDAO currencyDAO;

        public InsertCurrencyAsync(CurrencyDAO currencyDAO) {
            this.currencyDAO = currencyDAO;
        }

        @Override
        protected Void doInBackground(Currency... currencies) {
            currencyDAO.createCurrency(currencies[0]);
            return null;
        }
    }
}
