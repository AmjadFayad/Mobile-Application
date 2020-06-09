package amjad.fayad.dev.assessment4.models;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * Repository class for Currency entity
 * Used for networking, makes easier processing connection to web services
 */
public class CurrencyRepository {

    private CurrencyDAO currencyDAO;
    private LiveData<List<Currency>> allCurrencies;

    /**
     * Obtains the instance of the database
     * Obtains the dao for the currency entity
     * Retrieves the list of currency objs
     * @param application required to get the instance of the db
     */
    CurrencyRepository(Application application) {
        CurrencyDB database = CurrencyDB.getInstance(application);
        currencyDAO = database.currencyDAO();

        allCurrencies = currencyDAO.getAllCurrencies();
    }

    /**
     * Create
     * @param currency
     */
    void create(Currency currency) {
        new InsertCurrencyAsync(currencyDAO).execute(currency);
    }

    LiveData<List<Currency>> getAllCurrencies() {
        return allCurrencies;
    }

    /**
     * Async task class to insert a new Currency obj into the db
     * Required to perform db operations in another thread (not the main)
     */
    private static class InsertCurrencyAsync extends AsyncTask<Currency, Void, Void> {

        private CurrencyDAO currencyDAO;

        InsertCurrencyAsync(CurrencyDAO currencyDAO) {
            this.currencyDAO = currencyDAO;
        }

        @Override
        protected Void doInBackground(Currency... currencies) {
            currencyDAO.createCurrency(currencies[0]);
            return null;
        }
    }
}
