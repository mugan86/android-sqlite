package com.amugika.sqlite.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by anartzmugika on 9/6/16.
 */
public class Valoration implements Parcelable{

    private String added;
    private int id;
    private String text;
    private User user;
    private float value;
    private String itemurl;
    public Valoration()
    {

    }
    protected Valoration(Parcel in) {
        readParcel(in);
    }

    public String getItemurl() {
        return itemurl;
    }

    public void setItemurl(String itemurl) {
        this.itemurl = itemurl;
    }

    public String getAdded() {
        return added;
    }

    public void setAdded(String added) {
        this.added = added;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(added);
        dest.writeInt(id);
        dest.writeString(text);
        dest.writeParcelable(user, flags);
        dest.writeFloat(value);
        dest.writeString(itemurl);
    }

    private void readParcel(Parcel in)
    {
        added = in.readString();
        id = in.readInt();
        text = in.readString();
        user = in.readParcelable(User.class.getClassLoader());
        value = in.readFloat();
        itemurl = in.readString();
    }

    public static final Creator<Valoration> CREATOR = new Creator<Valoration>() {
        @Override
        public Valoration createFromParcel(Parcel in) {
            return new Valoration(in);
        }

        @Override
        public Valoration[] newArray(int size) {
            return new Valoration[size];
        }
    };

    @Override
    public String toString()
    {

        System.out.println("**************************************************************");
        return "Gehitua: " + this.added  +"\n" +
                "Erabiltzailea: " + this.user.getName() + "\n"+
                "Email: " + this.user.getEmail() + "\n" +
                "Puntuazioa: " + this.value + "\n" +
                "Argazkia: " + this.user.getPhoto_url() + "\n";

    }
}
