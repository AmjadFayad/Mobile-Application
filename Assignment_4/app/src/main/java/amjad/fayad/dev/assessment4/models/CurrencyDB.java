package amjad.fayad.dev.assessment4.models;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Currency.class}, version = 1)
public abstract class CurrencyDB extends RoomDatabase {

    private static CurrencyDB instance;

    abstract CurrencyDAO currencyDAO();

    /**
     * Gets instance of the database if the is one, if not then creates it.
     * @param context app context
     * @return instance of the db
     */
    static synchronized CurrencyDB getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    CurrencyDB.class, "currencydb")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }
}
