package com.amugika.sqlite.model;

import android.os.Parcel;
import android.os.Parcelable;

/**************************************
 * Created by anartzmugika on 23/4/16.
 */
public class Race implements Parcelable{

    private String race_code, race_circle_circuit, race_climb, race_data, race_distance, race_distance_for_location,
                    race_edition, race_finish_herri_kodea, race_finish_latitude, race_finish_longitude,
                    race_lat, race_lng, race_name, race_route_source, race_short_url, race_start_time,
                    race_town, race_track_id, race_type, race_video, race_web;

    public Race() {}

    protected Race(Parcel in) {
        readParcelable(in);
    }

    public static final Creator<Race> CREATOR = new Creator<Race>() {
        @Override
        public Race createFromParcel(Parcel in) {
            return new Race(in);
        }

        @Override
        public Race[] newArray(int size) {
            return new Race[size];
        }
    };

    public String getRace_code() {
        return race_code;
    }

    public void setRace_code(String race_code) {
        this.race_code = race_code;
    }

    public String getRace_circle_circuit() {
        return race_circle_circuit;
    }

    public void setRace_circle_circuit(String race_circle_circuit) {
        this.race_circle_circuit = race_circle_circuit;
    }

    public String getRace_climb() {
        return race_climb;
    }

    public void setRace_climb(String race_climb) {
        this.race_climb = race_climb;
    }

    public String getRace_data() {
        return race_data;
    }

    public void setRace_data(String race_data) {
        this.race_data = race_data;
    }

    public String getRace_distance() {
        return race_distance;
    }

    public void setRace_distance(String race_distance) {
        this.race_distance = race_distance;
    }

    public String getRace_distance_for_location() {
        return race_distance_for_location;
    }

    public void setRace_distance_for_location(String race_distance_for_location) {
        this.race_distance_for_location = race_distance_for_location;
    }

    public String getRace_edition() {
        return race_edition;
    }

    public void setRace_edition(String race_edition) {
        this.race_edition = race_edition;
    }

    public String getRace_finish_herri_kodea() {
        return race_finish_herri_kodea;
    }

    public void setRace_finish_herri_kodea(String race_finish_herri_kodea) {
        this.race_finish_herri_kodea = race_finish_herri_kodea;
    }

    public String getRace_finish_latitude() {
        return race_finish_latitude;
    }

    public void setRace_finish_latitude(String race_finish_latitude) {
        this.race_finish_latitude = race_finish_latitude;
    }

    public String getRace_finish_longitude() {
        return race_finish_longitude;
    }

    public void setRace_finish_longitude(String race_finish_longitude) {
        this.race_finish_longitude = race_finish_longitude;
    }

    public String getRace_lat() {
        return race_lat;
    }

    public void setRace_lat(String race_lat) {
        this.race_lat = race_lat;
    }

    public String getRace_lng() {
        return race_lng;
    }

    public void setRace_lng(String race_lng) {
        this.race_lng = race_lng;
    }

    public String getRace_name() {
        return race_name;
    }

    public void setRace_name(String race_name) {
        this.race_name = race_name;
    }

    public String getRace_route_source() {
        return race_route_source;
    }

    public void setRace_route_source(String race_route_source) {
        this.race_route_source = race_route_source;
    }

    public String getRace_short_url() {
        return race_short_url;
    }

    public void setRace_short_url(String race_short_url) {
        this.race_short_url = race_short_url;
    }

    public String getRace_start_time() {
        return race_start_time;
    }

    public void setRace_start_time(String race_start_time) {
        this.race_start_time = race_start_time;
    }

    public String getRace_town() {
        return race_town;
    }

    public void setRace_town(String race_town) {
        this.race_town = race_town;
    }

    public String getRace_track_id() {
        return race_track_id;
    }

    public void setRace_track_id(String race_track_id) {
        this.race_track_id = race_track_id;
    }

    public String getRace_type() {
        return race_type;
    }

    public void setRace_type(String race_type) {
        this.race_type = race_type;
    }

    public String getRace_video() {
        return race_video;
    }

    public void setRace_video(String race_video) {
        this.race_video = race_video;
    }

    public String getRace_web() {
        return race_web;
    }

    public void setRace_web(String race_web) {
        this.race_web = race_web;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(race_code);
        dest.writeString(race_circle_circuit);
        dest.writeString(race_climb);
        dest.writeString(race_data);
        dest.writeString(race_distance);
        dest.writeString(race_distance_for_location);
        dest.writeString(race_edition);
        dest.writeString(race_finish_herri_kodea);
        dest.writeString(race_finish_latitude);
        dest.writeString(race_finish_longitude);
        dest.writeString(race_lat);
        dest.writeString(race_lng);
        dest.writeString(race_name);
        dest.writeString(race_route_source);
        dest.writeString(race_short_url);
        dest.writeString(race_start_time);
        dest.writeString(race_town);
        dest.writeString(race_track_id);
        dest.writeString(race_type);
        dest.writeString(race_video);
        dest.writeString(race_web);
    }

    public void readParcelable(Parcel in)
    {
        race_code = in.readString();
        race_circle_circuit = in.readString();
        race_climb = in.readString();
        race_data = in.readString();
        race_distance = in.readString();
        race_distance_for_location = in.readString();
        race_edition = in.readString();
        race_finish_herri_kodea = in.readString();
        race_finish_latitude = in.readString();
        race_finish_longitude = in.readString();
        race_lat = in.readString();
        race_lng = in.readString();
        race_name = in.readString();
        race_route_source = in.readString();
        race_short_url = in.readString();
        race_start_time = in.readString();
        race_town = in.readString();
        race_track_id = in.readString();
        race_type = in.readString();
        race_video = in.readString();
        race_web = in.readString();
    }
}
