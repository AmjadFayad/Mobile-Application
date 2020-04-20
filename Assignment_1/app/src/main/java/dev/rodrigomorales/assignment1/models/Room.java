package dev.rodrigomorales.assignment1.models;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

public class Room implements Parcelable {

    private String name;
    private Integer costPerNight;
    private String roomType;

    public Room(String name, Integer costPerNight, String roomType) {
        this.name = name;
        this.costPerNight = costPerNight;
        this.roomType = roomType;
    }

    protected Room(Parcel in) {
        name = in.readString();
        if (in.readByte() == 0) {
            costPerNight = null;
        } else {
            costPerNight = in.readInt();
        }
        roomType = in.readString();
    }

    public static final Creator<Room> CREATOR = new Creator<Room>() {
        @Override
        public Room createFromParcel(Parcel in) {
            return new Room(in);
        }

        @Override
        public Room[] newArray(int size) {
            return new Room[size];
        }
    };

    public String getName() {
        return name;
    }

    public Integer getCostPerNight() {
        return costPerNight;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setCostPerNight(Integer costPerNight) {
        this.costPerNight = costPerNight;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(costPerNight);
        dest.writeString(roomType);
    }
}
