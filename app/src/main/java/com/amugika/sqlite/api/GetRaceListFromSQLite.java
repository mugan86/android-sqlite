package com.amugika.sqlite.api;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.LinearLayout;

import com.amugika.sqlite.MainActivity;
import com.amugika.sqlite.adapter.RaceAdapter;
import com.amugika.sqlite.db.RaceDBHelper;
import com.amugika.sqlite.model.Race;

import java.util.ArrayList;

/*************************
 * Created by anartzmugika on 25/2/16.
 */
public class GetRaceListFromSQLite extends AsyncTask<String,String,ArrayList<Race>> {

    private Activity activity;
    private ArrayList<Race> race_list;
    private RaceAdapter adapter;
    private LinearLayout splashLinearLayout, no_splashLinearLayout;
    private RaceDBHelper dbHelper;

    public GetRaceListFromSQLite(Activity activity, ArrayList<Race> race_list, RaceAdapter adapter,
                                 LinearLayout splashLinearLayout, LinearLayout no_splashLinearLayout, RaceDBHelper dbHelper)
    {
        this.activity = activity;
        this.adapter = adapter;
        this.race_list = race_list;
        this.splashLinearLayout = splashLinearLayout;
        this.no_splashLinearLayout = no_splashLinearLayout;
        this.dbHelper = dbHelper;
    }
    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();

        splashLinearLayout.setVisibility(View.VISIBLE);
        no_splashLinearLayout.setVisibility(View.GONE);
    }
    @Override
    protected ArrayList<Race> doInBackground(String ... params) {

        try {
            System.out.println("SQlite");
            return dbHelper.getRacesInfo();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(ArrayList<Race> result) {
        super.onPostExecute(result);

        if (result != null)
        {
            race_list.clear();
            if (dbHelper.getRegisterCount(dbHelper) == result.size())
            {
                /************************************************************
                 * List result
                 */
                System.out.println("Before update: " + race_list.size());

                //Llenar la lista para añadirlo al adaptador
                for (Object race : result)
                    race_list.add((Race)race);

                adapter = new RaceAdapter(race_list, activity);

                ((MainActivity)activity).addAdapterConfig(adapter);
            }
            else
            {
                System.out.println("EZ DITU DENAK ZUZEN KARGATU!!!");
            }

        }
        else
        {
            System.out.println("Not Update");
        }

        splashLinearLayout.setVisibility(View.GONE);
        no_splashLinearLayout.setVisibility(View.VISIBLE);
    }
}
