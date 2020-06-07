package amjad.fayad.dev.assessment4.models;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

public abstract class CurrencyDB extends RoomDatabase {

    private static CurrencyDB instance;

    public abstract CurrencyDAO currencyDAO();

    /**
     * Gets instance of the database if the is one, if not then creates it.
     * @param context app context
     * @return instance of the db
     */
    public static synchronized CurrencyDB getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    CurrencyDB.class, "currencydb")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }
}
