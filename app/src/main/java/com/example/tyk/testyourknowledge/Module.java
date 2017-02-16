package com.example.tyk.testyourknowledge;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Axa on 14/02/2017.
 */

public class Module implements Parcelable{
    private String id;
    private String name;

    public Module(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Module(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Module{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
