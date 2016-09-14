package com.amugika.sqlite;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/********************
 * Created by anartzmugika on 14/9/16.
 */

public class ShareActivity extends AppCompatActivity {

    private TextView share_textTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_activity);

        share_textTextView = (TextView) findViewById(R.id.share_textTextView);

        Uri uri = getIntent().getData();

        System.out.println(uri.toString());
        String path = uri.toString().replace("http://www.mendiak.mugan86.com/#/", "");

        share_textTextView.setText(path);

    }
}
