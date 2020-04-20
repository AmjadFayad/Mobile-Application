package dev.rodrigomorales.assignment1.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.rodrigomorales.assignment1.R;
import dev.rodrigomorales.assignment1.models.Room;
import dev.rodrigomorales.assignment1.view.ReservationActivity;

public class RoomsRVAdapter extends RecyclerView.Adapter<RoomsRVAdapter.RoomsHolder> {

    private List<Room> rooms;

    public RoomsRVAdapter(List<Room> rooms) {
        this.rooms = rooms;
    }

    @NonNull
    @Override
    public RoomsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room, parent,false);
        return new RoomsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomsHolder holder, int position) {

        final Room currentRoom = rooms.get(position);
        holder.bind(currentRoom);
    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }

    class RoomsHolder extends RecyclerView.ViewHolder {

        private TextView roomName;

        public RoomsHolder(@NonNull final View itemView) {
            super(itemView);

            roomName = itemView.findViewById(R.id.tv_room_name);
        }

        void bind(final Room room) {
            roomName.setText(room.getName());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(itemView.getContext(), ReservationActivity.class);
                    i.putExtra("room", room);
                    itemView.getContext().startActivity(i);
                }
            });
        }
    }

    /**
     * In case you want to add to the list of rooms
     * @param rooms to be added
     */
    public void appendRooms(List<Room> rooms) {
        rooms.addAll(rooms);
        notifyDataSetChanged();
    }
}
