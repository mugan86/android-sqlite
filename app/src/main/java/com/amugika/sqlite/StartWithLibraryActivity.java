package com.amugika.sqlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import anartzmugika.mylibrary.MyView;
/********************
 * Created by anartzmugika on 23/11/2016.
 * Activity to open custom view in "mylibrary"
 ********************/

public class StartWithLibraryActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(StartWithLibraryActivity.this));

    }
}
