package com.amugika.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.amugika.sqlite.adapter.RaceAdapter;
import com.amugika.sqlite.api.GetRaceListFromSQLite;
import com.amugika.sqlite.api.GetRacesList;
import com.amugika.sqlite.db.RaceDBHelper;
import com.amugika.sqlite.model.Race;
import com.amugika.sqlite.utils.Actions;
import com.amugika.sqlite.utils.DateTime;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity{

    RaceDBHelper dbHelper;
    private RecyclerView mountain_listRecyclerView;
    private ArrayList<Race> race_list;
    private RaceAdapter adapter;
    private  LinearLayout splashLinearLayout, no_splashLinearLayout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        race_list = new ArrayList<>();
        mountain_listRecyclerView = (RecyclerView) findViewById(R.id.mountain_listRecyclerView);
        adapter = new RaceAdapter(race_list, MainActivity.this);

        mountain_listRecyclerView = (RecyclerView) findViewById(R.id.mountain_listRecyclerView);

        splashLinearLayout = (LinearLayout) findViewById(R.id.splashLinearLayout);
        no_splashLinearLayout = (LinearLayout) findViewById(R.id.no_splashLinearLayout);


        dbHelper = new RaceDBHelper(getBaseContext());

        int register_totals = dbHelper.getRegisterCount(dbHelper);

        System.out.println("Register Totals: " + register_totals);


        if (DateTime.isFirstWeekDay() || register_totals == 0) { //Actualiza / Añade
           System.out.println("Get Data from Server");

            GetRacesList get_mountains_list = new GetRacesList(MainActivity.this, race_list,
                    adapter, splashLinearLayout, no_splashLinearLayout);
            get_mountains_list.execute();
        }
        else
        { // Si no es primer día o hay registros guardados, abre desde SQLite
            System.out.println("Get Data from SQLite");

            find();
        }


    }

    public void addAdapterConfig(RaceAdapter adapter)
    {

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("DemoRecView", "Pulsado el elemento " + race_list.get(mountain_listRecyclerView.getChildPosition(v)));

                /*Bundle bundle = new Bundle();

                bundle.putString(Constants.MOUNTAIN_ITEM_URL, mountain_list.get(mountain_listRecyclerView.getChildPosition(v)).getItemurl());

                Intent MountainDetailActivityIntent = new Intent(MainActivity.this, MountainDetailActivity.class);
                MountainDetailActivityIntent.putExtras(bundle);
                MountainDetailActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MountainDetailActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(MountainDetailActivityIntent);*/
            }
        });

        mountain_listRecyclerView.setAdapter(adapter);

        mountain_listRecyclerView = Actions.getRecyclerViewConfig(mountain_listRecyclerView, MainActivity.this, true);

        adapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        //Esconder los items del menú, si se quisiera desactivar sería setEnabled en vez de setVisible
        menu.findItem(R.id.action_share).setVisible(false);
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.order_by_distance).setVisible(false);
        menu.findItem(R.id.order_by_altitude).setVisible(false);
        menu.findItem(R.id.order_by_importance).setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.filter_option)
        {
            Intent GalleryIntent = new Intent(MainActivity.this, PlacesGalleryActivity.class);
            startActivity(GalleryIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void find()
    {
        GetRaceListFromSQLite get_race_from_sqlite = new GetRaceListFromSQLite(MainActivity.this, race_list, adapter,
                splashLinearLayout, no_splashLinearLayout, dbHelper);
        get_race_from_sqlite.execute();
    }





}
