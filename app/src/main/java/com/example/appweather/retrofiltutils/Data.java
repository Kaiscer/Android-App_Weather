package com.example.appweather.retrofiltutils;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

/**
 * El import android.os.Parcelable; es para poder enviar datos de una actividad a otra
 * los datos que se envian son los que se encuentran en el constructor
 * es la mas eficiente pero solo funciona para app desarrolladas en android
 */
public class Data implements Parcelable {

    private double latitude;
    private double longitude;

    public Data(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    protected Data(Parcel in) {
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
