package com.amugika.sqlite;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.amugika.sqlite.utils.Actions;
import com.amugika.sqlite.utils.TouchImageView;

/**
 * Created by anartzmugika on 31/5/16.
 */
public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

        String title = getIntent().getStringExtra("title");
        Bitmap bitmap = Actions.getBitmapFromURL(getIntent().getStringExtra("image"));

        TextView titleTextView = (TextView) findViewById(R.id.title);
        titleTextView.setText(title);

        TouchImageView image = (TouchImageView) findViewById(R.id.image);
        image.setImageBitmap(bitmap);

    }
}