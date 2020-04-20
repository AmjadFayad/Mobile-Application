package dev.rodrigomorales.assignment1.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Stay implements Parcelable {

    private Room room;
    private String startStay;
    private String endStay;
    private Integer totalCost;

    public Stay(Room room, String startStay, String endStay, Integer totalCost) {
        this.room = room;
        this.startStay = startStay;
        this.endStay = endStay;
        this.totalCost = totalCost;
    }

    protected Stay(Parcel in) {
        room = in.readParcelable(Room.class.getClassLoader());
        startStay = in.readString();
        endStay = in.readString();
        if (in.readByte() == 0) {
            totalCost = null;
        } else {
            totalCost = in.readInt();
        }
    }

    public static final Creator<Stay> CREATOR = new Creator<Stay>() {
        @Override
        public Stay createFromParcel(Parcel in) {
            return new Stay(in);
        }

        @Override
        public Stay[] newArray(int size) {
            return new Stay[size];
        }
    };

    public Room getRoom() {
        return room;
    }

    public String getStartStay() {
        return startStay;
    }

    public String getEndStay() {
        return endStay;
    }

    public Integer getTotalCost() {
        return totalCost;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(room.getName());
        dest.writeInt(room.getCostPerNight());
        dest.writeString(room.getRoomType());
        dest.writeString(startStay);
        dest.writeString(endStay);
        dest.writeInt(totalCost);
    }
}
