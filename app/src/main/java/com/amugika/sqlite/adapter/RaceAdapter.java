package com.amugika.sqlite.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.amugika.sqlite.MainActivity;
import com.amugika.sqlite.R;
import com.amugika.sqlite.model.Race;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by anartzmugika on 25/2/16.
 */
public class RaceAdapter
        extends RecyclerView.Adapter<RaceAdapter.RacesViewHolder>
        implements View.OnClickListener {

    private View.OnClickListener listener;
    private ArrayList<Race> datos;
    private static Activity activity;


    public static class RacesViewHolder
            extends RecyclerView.ViewHolder {

        private TextView txtTitulo;
        private TextView txtSubtitulo;
        private ImageView image_id;
        private TextView altitudeTextView;
        private TextView ourDistanceTextView;
        private TextView prominencetextView;
        private RatingBar ratingBar;
        private ImageView one_hundred_mountainsImageView;
        private ImageView for_children_mountainsImageView;
        private ImageButton action_insideRecyclerViewItemImageButton;

        public RacesViewHolder(View itemView)
        {
            super(itemView);

            txtTitulo = (TextView)itemView.findViewById(R.id.textView);
            txtSubtitulo = (TextView)itemView.findViewById(R.id.textView2);
            image_id = (ImageView) itemView.findViewById(R.id.image_id);
            altitudeTextView = (TextView) itemView.findViewById(R.id.altitudeTextView);
            ourDistanceTextView = (TextView) itemView.findViewById(R.id.ourDistanceTextView);
            prominencetextView = (TextView) itemView.findViewById(R.id.prominencetextView);
            ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
            one_hundred_mountainsImageView = (ImageView) itemView.findViewById(R.id.one_hundred_mountainsImageView);
            for_children_mountainsImageView = (ImageView) itemView.findViewById(R.id.for_children_mountainsImageView);
            action_insideRecyclerViewItemImageButton = (ImageButton) itemView.findViewById(R.id.action_insideRecyclerViewItemImageButton);
        }

        public void bindRace(final Race race) {
            txtTitulo.setText(race.getRace_name());
            txtSubtitulo.setText(race.getRace_data());
            //"https://d30y9cdsu7xlg0.cloudfront.net/png/16025-200.png"

            altitudeTextView.setText("");

            String image = "http://www.mugan86.com/serviraces/img/race/type/" + race.getRace_type() + ".png";


            Picasso.with(activity).load(image).error(R.drawable.ic_map).fit().into(image_id);



            //HIDE - NOT USE IN THIS EXAMPLE
            ratingBar.setVisibility(View.GONE);
            one_hundred_mountainsImageView.setVisibility(View.GONE);
            for_children_mountainsImageView.setVisibility(View.GONE);
            prominencetextView.setVisibility(View.GONE);

            action_insideRecyclerViewItemImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(activity, "Action inside row!!!" + race.getRace_name(), Toast.LENGTH_LONG).show();
                    ((MainActivity)activity).showFilterPopup(v, race);

                }
            });


        }
    }

    public RaceAdapter(ArrayList<Race> datos, Activity activity) {
        this.datos = datos;
        this.activity = activity;
        System.out.println("Datu kopurua: " + this.datos.size());
    }

    @Override
    public RacesViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.race_row, viewGroup, false);

        itemView.setOnClickListener(this);
        RacesViewHolder tvh = new RacesViewHolder(itemView);

        return tvh;
    }

    @Override
    public void onBindViewHolder(RacesViewHolder viewHolder, int pos) {
        Race item = datos.get(pos);

        viewHolder.bindRace(item);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }

    public static String getPointsRespectFivePoints(float points)
    {
        // points ------ 3
        //   x --------- 5

        return String.format("%.02f", (points*5)/3);
    }

}