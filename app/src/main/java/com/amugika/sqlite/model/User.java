package com.amugika.sqlite.model;

import android.os.Parcel;
import android.os.Parcelable;

/****************************************************
 * Created by anartzmugika on 1/3/16.
 */
public class User implements Parcelable {

    private String id;
    private String name;
    private String email;
    private String photo_url;
    private String itemurl;
    private int climbs;

    private String userid;

    public User(String id, String name, String email, String photo, int climbs)
    {
        setId(id);
        setName(name);
        setEmail(email);
        setPhoto_url(photo);
        setClimbs(climbs);
    }

    public User(String id, String name, String email, String photo)
    {
        setId(id);
        setName(name);
        setEmail(email);
        setPhoto_url(photo);
    }

    public User(String id, int climbs, String name)
    {
        setId(id);
        setClimbs(climbs);
        setName(name);
        System.out.println(toString() + "\nClimbs: " + getClimbs());
    }

    protected User(Parcel in) {
        readParcel(in);
    }
    public int getClimbs() {
        return climbs;
    }

    public void setClimbs(int climbs) {
        this.climbs = climbs;
    }

    public String getId() {
        return userid;
    }

    public void setId(String id) {
        this.userid = id;
    }

    public String getMendiakId() {
        return id;
    }

    public void setMendiakId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = !name.equals("") ? name : "Ez dago zehazturik";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getItemurl() {
        return itemurl;
    }

    public void setItemurl(String itemurl) {
        this.itemurl = itemurl;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    @Override
    public String toString()
    {
        return "********************************************************************\n" +
            "Person Name: " + this.name + "\n" + "Person Email: " + this.email + "\n" +
            "Person Id: " + this.userid + "\n" +"Person Photo: " + this.photo_url + "\n"+
                "********************************************************************";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    private void readParcel (Parcel in)
    {
        id = in.readString();
        name = in.readString();
        email = in.readString();
        photo_url = in.readString();
        climbs = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(photo_url);
        dest.writeInt(climbs);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };


}
