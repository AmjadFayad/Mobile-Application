package amjad.fayad.dev.assessment4.models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CurrencyDAO {

    /**
     * Insets a Currency obj into the db
     * @param currency
     */
    @Insert
    void createCurrency(Currency currency);

    /**
     * Query to select all currencies in the db
     */
    @Query("SELECT * FROM currency")
    LiveData<List<Currency>> getAllCurrencies();

    /**
     * Obtains a Currency obj from the db by its ID
     * @param currencyId id to search
     * @return LiveData Currency
     */
    @Query("SELECT * FROM currency WHERE localId = :currencyId LIMIT 1")
    LiveData<Currency> getCurrencyById(int currencyId);
}
