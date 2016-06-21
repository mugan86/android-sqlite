package com.amugika.sqlite;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.amugika.sqlite.adapter.GridViewAdapter;
import com.amugika.sqlite.model.ImageItem;
import com.amugika.sqlite.utils.Actions;

import java.util.ArrayList;

/***************************************
 * Created by anartzmugika on 31/5/16.
 ***************************************/
public class PlacesGalleryActivity extends AppCompatActivity {

    private GridView gridView;
    private GridViewAdapter gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        gridView = (GridView) findViewById(R.id.gridView);
        gridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, getData());
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                Toast.makeText(PlacesGalleryActivity.this, "Position: " + position, Toast.LENGTH_LONG).show();
                ImageItem item = (ImageItem) parent.getItemAtPosition(position);

                //Create intent
                Intent intent = new Intent(PlacesGalleryActivity.this, DetailsActivity.class);
                intent.putExtra("title", item.getTitle());
                intent.putExtra("image", Constants.photos[position]);

                //Start details activity
                startActivity(intent);
            }
        });
    }

    // Prepare some dummy data for gridview
    private ArrayList<ImageItem> getData() {
        final ArrayList<ImageItem> imageItems = new ArrayList<>();
        String [] imgs = Constants.photos;
        for (int i = 0; i < imgs.length; i++) {
            Bitmap bitmap = Actions.getBitmapFromURL(imgs[i]);
            imageItems.add(new ImageItem(bitmap, "Image#" + i));
        }
        return imageItems;
    }


}
