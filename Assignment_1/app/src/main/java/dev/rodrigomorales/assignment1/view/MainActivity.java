package dev.rodrigomorales.assignment1.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import dev.rodrigomorales.assignment1.R;
import dev.rodrigomorales.assignment1.view.adapters.RoomsRVAdapter;
import dev.rodrigomorales.assignment1.models.Room;

public class MainActivity extends AppCompatActivity {

    private RecyclerView roomsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        roomsRecyclerView = findViewById(R.id.rv_main);
        List<Room> rooms = generateRooms();

        recyclerViewSetup(rooms);
    }

    private List<Room> generateRooms() {

        List<Room> rooms = new ArrayList<>();

        Room room1 = new Room("Superior Room", 155, "just a room");
        rooms.add(room1);

        Room room2 = new Room("Deluxe Room", 185, "a room but deluxe");
        rooms.add(room2);

        Room room3 = new Room("Junior Suite", 245, "expensive room");
        rooms.add(room3);

        Room room4 = new Room("Suite", 285, "expensive room");
        rooms.add(room4);

        Room room5 = new Room("SPA Suite", 370, "costs 2 kidneys");
        rooms.add(room5);

        return rooms;
    }

    private void recyclerViewSetup(List<Room> rooms) {

        roomsRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        RoomsRVAdapter adapter = new RoomsRVAdapter(rooms);
        roomsRecyclerView.setAdapter(adapter);
    }
}
