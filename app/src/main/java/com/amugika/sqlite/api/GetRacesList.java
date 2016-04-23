package com.amugika.sqlite.api;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.LinearLayout;

import com.amugika.sqlite.MainActivity;
import com.amugika.sqlite.adapter.RaceAdapter;
import com.amugika.sqlite.db.RaceDBHelper;
import com.amugika.sqlite.model.Race;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;

/*************************
 * Created by anartzmugika on 25/2/16.
 */
public class GetRacesList extends AsyncTask<String,Integer,ArrayList<Race>> {

    private Activity activity;
    private ArrayList<Race> races_list;
    private RaceAdapter adapter;
    private LinearLayout splashLinearLayout, no_splashLinearLayout;


    public GetRacesList(Activity activity, ArrayList<Race> races_list,
                        RaceAdapter adapter, LinearLayout splashLinearLayout, LinearLayout no_splashLinearLayout)
    {
        this.activity = activity;
        this.races_list = races_list;
        this.adapter = adapter;
        this.splashLinearLayout = splashLinearLayout;
        this.no_splashLinearLayout = no_splashLinearLayout;
    }

    protected void onPreExecute()
    {
        super.onPreExecute();

        splashLinearLayout.setVisibility(View.VISIBLE);
        no_splashLinearLayout.setVisibility(View.GONE);
    }

    @Override
    protected ArrayList<Race> doInBackground(String... params) {
        String json;

        try {
            json = Request.getHttpGETAPI(activity, "http://mugan86.com/serviraces/api/v1/race/get/race_infov4.php");
            System.out.println(json);
            return new Gson().fromJson(json, new TypeToken<ArrayList<Race>>(){}.getType());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(ArrayList<Race> result) {
        super.onPostExecute(result);

        if (result != null)
        {
            RaceDBHelper dbHelper = new RaceDBHelper(activity.getBaseContext());
            dbHelper.addRaces(result);

            for (Race mountain: result)
            {
                races_list.add(mountain);
            }

            System.out.println(races_list.size());

            adapter = new RaceAdapter(races_list, activity);

            ((MainActivity)activity).addAdapterConfig(adapter);
        }

        splashLinearLayout.setVisibility(View.GONE);
        no_splashLinearLayout.setVisibility(View.VISIBLE);

    }

}
