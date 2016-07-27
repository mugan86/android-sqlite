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
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amugika.sqlite.adapter.RaceAdapter;
import com.amugika.sqlite.api.GetRaceListFromSQLite;
import com.amugika.sqlite.api.GetRacesList;
import com.amugika.sqlite.db.RaceDBHelper;
import com.amugika.sqlite.model.Race;
import com.amugika.sqlite.model.Review;
import com.amugika.sqlite.model.Valoration;
import com.amugika.sqlite.preferences.SettingsActivity;
import com.amugika.sqlite.utils.Actions;
import com.amugika.sqlite.utils.DateTime;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity{

    RaceDBHelper dbHelper;
    private RecyclerView mountain_listRecyclerView;
    private ArrayList<Race> race_list;
    private RaceAdapter adapter;
    private  LinearLayout splashLinearLayout, no_splashLinearLayout, ratingBarLinearLayout ;
    private int [] valoration_colors;
    private View valorations_views [];
    private ArrayList<Valoration> valorations;
    private Review review;
    private TextView mountain_valorationTextView, valoration_avgTotalTextView, five_points_countTextView,
            four_points_countTextView, three_points_countTextView, two_points_countTextView,
            one_points_countTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        valorations = new ArrayList<>();
        Valoration val = new Valoration();
        val.setValue((float) 2.5);
        val.setText("");
        valorations.add(val);
        val.setValue((float) 4.5);
        val.setText("");
        valorations.add(val);
        val.setValue((float) 1.0);
        val.setText("");
        valorations.add(val);
        val.setValue((float) 2.5);
        val.setText("");
        valorations.add(val);
        val.setValue((float) 2.0);
        val.setText("");
        valorations.add(val);
        val.setValue((float) 4.0);
        val.setText("");
        valorations.add(val);
        val.setValue((float) 0.5);
        val.setText("");
        valorations.add(val);

        System.out.println("Valorations size: " + valorations.size());

        review = new Review(valorations);

        valorations_views = new View[5];
        valorations_views[0] = findViewById(R.id.valoration_five_pointView);
        valorations_views[1] = findViewById(R.id.valoration_four_pointView);
        valorations_views[2] = findViewById(R.id.valoration_three_pointView);
        valorations_views[3] = findViewById(R.id.valoration_two_pointView);
        valorations_views[4] = findViewById(R.id.valoration_one_pointView);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(50, ViewGroup.LayoutParams.WRAP_CONTENT);
        valorations_views[0].setLayoutParams(params);

        params = new RelativeLayout.LayoutParams(120, ViewGroup.LayoutParams.WRAP_CONTENT);
        valorations_views[1].setLayoutParams(params);

        race_list = new ArrayList<>();
        mountain_listRecyclerView = (RecyclerView) findViewById(R.id.mountain_listRecyclerView);
        adapter = new RaceAdapter(race_list, MainActivity.this);

        mountain_valorationTextView = (TextView) findViewById(R.id.mountain_valorationTextView);

        valoration_avgTotalTextView = (TextView) findViewById(R.id.valoration_avgTotalTextView);

        mountain_listRecyclerView = (RecyclerView) findViewById(R.id.mountain_listRecyclerView);

        splashLinearLayout = (LinearLayout) findViewById(R.id.splashLinearLayout);
        no_splashLinearLayout = (LinearLayout) findViewById(R.id.no_splashLinearLayout);
        ratingBarLinearLayout = (LinearLayout) findViewById(R.id.ratingBarsValorations);


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

        mountain_valorationTextView.setText(review.getAvg_points());
        valoration_avgTotalTextView.setText(String.valueOf(review.getValorationsCount()));

        int [][] valorations_count = review.getValuesCount();

        five_points_countTextView = (TextView) findViewById(R.id.five_points_countTextView);
        four_points_countTextView = (TextView) findViewById(R.id.four_points_countTextView);
        three_points_countTextView = (TextView) findViewById(R.id.three_points_countTextView);
        two_points_countTextView = (TextView) findViewById(R.id.two_points_countTextView);
        one_points_countTextView = (TextView) findViewById(R.id.one_points_countTextView);

        five_points_countTextView.setText(String.valueOf(valorations_count[0][1]));
        four_points_countTextView.setText(String.valueOf(valorations_count[1][1]));
        three_points_countTextView.setText(String.valueOf(valorations_count[2][1]));
        two_points_countTextView.setText(String.valueOf(valorations_count[3][1]));
        one_points_countTextView.setText(String.valueOf(valorations_count[4][1]));

    }


    // Display anchored popup menu based on view selected
    public void showFilterPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        // Inflate the menu from xml
        popup.getMenuInflater().inflate(R.menu.popupmenu, popup.getMenu());
        // Setup menu item selection
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_keyword:
                        Toast.makeText(MainActivity.this, "Keyword!", Toast.LENGTH_SHORT).show();
                        Intent SettingsActivityIntent = new Intent (MainActivity.this, SettingsActivity.class);
                        startActivity(SettingsActivityIntent);
                        return true;
                    case R.id.menu_popularity:
                        Toast.makeText(MainActivity.this, "Popularity!", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return false;
                }
            }
        });
        // Handle dismissal with: popup.setOnDismissListener(...);
        // Show the menu
        popup.show();
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
