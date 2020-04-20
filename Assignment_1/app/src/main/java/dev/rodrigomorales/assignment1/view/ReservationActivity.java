package dev.rodrigomorales.assignment1.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import dev.rodrigomorales.assignment1.R;
import dev.rodrigomorales.assignment1.models.Room;
import dev.rodrigomorales.assignment1.models.Stay;

public class ReservationActivity extends AppCompatActivity {

    private TextView name, cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Room room = getIntent().getExtras().getParcelable("room");
        int costPerNight = getIntent().getIntExtra("cost",0);
        room.setCostPerNight(costPerNight);

        name = findViewById(R.id.tv_room_name);
        cost = findViewById(R.id.tv_cost_per_night);

        infoSetup(room);

        CalendarView calendar = findViewById(R.id.calendarView);
        EditText daysAmount = findViewById(R.id.et_stay);
        Button submit = findViewById(R.id.btn_submit);

        computeStay(calendar,daysAmount, submit, room);

    }

    /**
     * sets info to texts views
     * @param room obj used to obtain info
     */
    private void infoSetup(Room room) {
        name.setText(room.getName());
        cost.setText("Cost per night: $" + room.getCostPerNight());
    }

    /**
     * Get the initial date for the stay from the calendar view
     * @param calendar to get the day
     * @return string with the date
     */
    private String getDate(CalendarView calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(new Date(calendar.getDate()));
    }

    /**
     * Obtains the amount of days to stay
     * @param daysAmount edit text to get the day
     * @return Integer with the amount of days
     */
    private Integer getStay(EditText daysAmount) {

        Integer totalDays = Integer.parseInt(daysAmount.getText().toString());
        if (totalDays == null) {
            totalDays = 0;
        }
        return totalDays;
    }

    /**
     * Computes the stay
     * @param initialDay to start count
     * @param stayLength to calculate the length of the stay
     * @return end date in string
     */
    private String computeEndOfStay(String initialDay, int stayLength) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();

        try {
            c.setTime(sdf.parse(initialDay));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        c.add(Calendar.DAY_OF_MONTH, stayLength);
        return sdf.format(c.getTime());
    }

    private void computeStay(final CalendarView calendar, final EditText daysAmount, Button submit, final Room room) {

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (daysAmount.getText().toString().trim() == null || daysAmount.getText().toString().isEmpty() || Integer.parseInt(daysAmount.getText().toString()) == 0) {
                    Toast.makeText(getApplicationContext(), "Please enter a stay > 0", Toast.LENGTH_SHORT).show();
                } else {
                    String initStay = getDate(calendar);
                    Integer amountOfDays = getStay(daysAmount);
                    String endStay = computeEndOfStay(initStay, amountOfDays);
                    int totalCost = room.getCostPerNight() * amountOfDays;

                    Stay stay = new Stay(room, initStay, endStay, totalCost);

                    Intent i = new Intent(getApplicationContext(), ConfirmActivity.class);
                    i.putExtra("stay", stay);
                    startActivity(i);
                }
            }
        });
    }

    /**
     * Displays back button on app bar and returns to previous screen
     * @param item menu item
     * @return superclass constructor with item as param
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
