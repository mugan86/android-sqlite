package com.amugika.sqlite.utils;


import android.app.Activity;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;

import com.amugika.sqlite.MainActivity;
import com.amugika.sqlite.R;

import junit.framework.Assert;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import design.DividerItemDecoration;

/***********************************************************************
 * Created by Anartz Mugika on 19/10/2014.
 **********************************************************************/
public class Actions {


    public static void goToGooglePlayAppPage (Context context, String package_name)
    {
        //((Activity)context).finish();
        System.out.println("Package name: " + package_name);
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="
                    + package_name)));
        } catch (android.content.ActivityNotFoundException anfe) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + package_name)));
        }
        ((Activity)context).overridePendingTransition(0, 0);

    }

    public static void goToSelectUrl (Context context, String url)
    {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
        ((Activity)context).overridePendingTransition(0, 0);
    }

    public static void openPhoneCallActivity (Context context, String telephone)
    {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", telephone, null));
        context.startActivity(intent);
    }

    public static void composeEmail(Context context, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"codesyntax@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

    public static boolean CheckInternetConnection(Context context)
    {
        //Check Internet connection status
        ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService (Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() != null
                && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnected())
        {
            return true;
        }
        else
        {
            //No connection
            return false;
        }
    }

    //Method to get app version name
    public static int getApplicationVersionCode (Context context)
    {
        PackageInfo pInfo = null;
        try {
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return pInfo.versionCode;
    }

    //To Launch Share Intent add inside startActivity(method call);
    public static Intent getDefaultShareIntent(final Context context, String text, Bitmap bm){

        Intent intent = new Intent(Intent.ACTION_SEND);
        if (bm == null)
        {
            intent.setType("text/plain");
        }
        else
        {
            intent.setType("image/*");
        }
        //

        String subject;

        subject = context.getResources().getString(R.string.title_share_app);
        if (text.equals(""))
        {
            text = context.getResources().getString(R.string.title_share_app);
        }
        else
        {
            text = "#mendiak " + text + " @codesyntax";
        }

        if (bm != null)
        {
            // Save this bitmap to a file.
            File cache = context.getExternalCacheDir();
            File sharefile = new File(cache, "mendiak_share.png");
            try {
                FileOutputStream out = new FileOutputStream(sharefile);
                assert bm != null;
                bm.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + sharefile));
        }

        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);

        //Start social networks window
        return Intent.createChooser(intent, "Share");
    }

    public static void shareImage(Context context, Bitmap bm) {


        // Save this bitmap to a file.
        File cache = context.getExternalCacheDir();
        File sharefile = new File(cache, "toshare.png");
        try {
            FileOutputStream out = new FileOutputStream(sharefile);
            bm.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Now send it out to share
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("image/*");
        share.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + sharefile));
        try {
            context.startActivity(Intent.createChooser(share, "Share photo"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendImage()
    {
        Intent shareCaptionIntent = new Intent(Intent.ACTION_SEND);
        shareCaptionIntent.setType("image/*");

        //set photo
        //shareCaptionIntent.setData(examplePhoto);
        //shareCaptionIntent.putExtra(Intent.EXTRA_STREAM, examplePhoto);

        //set caption
        shareCaptionIntent.putExtra(Intent.EXTRA_TEXT, "example caption");
        shareCaptionIntent.putExtra(Intent.EXTRA_SUBJECT, "example caption");

        // constartActivity(Intent.createChooser(shareCaptionIntent,getString(R.string.share))
    }


    public static void restarApp (Context context)
    {
        Intent restart_app_intent = new Intent (context, MainActivity.class);
        restart_app_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(restart_app_intent);
        ((Activity)context).finish();
    }

    public static DisplayMetrics getDisplayMetrics(Context context)
    {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm;
    }

    /*****************************************************************************************
     * 			Pantailaren zabalera lortzeko
     *****************************************************************************************/
    public static int getScreenWidth (Context context)
    {

        DisplayMetrics dm = getDisplayMetrics(context);

        return dm.widthPixels;
    }
    /*****************************************************************************************
     * 			Pantailaren zabalera lortzeko
     *****************************************************************************************/
    public static int getScreenHeigth (Context context)
    {

        DisplayMetrics dm = getDisplayMetrics(context);

        return dm.heightPixels;
    }
    /*****************************************************************************************
     * 			Pantailaren ezaugarriak dentsitatea lortzeko
     *****************************************************************************************/
    public static String getWindowsDensity(Context context)
    {

        DisplayMetrics dm = getDisplayMetrics(context);

        int densityDpi = dm.densityDpi;

        /************************************************************************************
         PANTAILAREN DENTSITATEAREN ARABERA (densityDpi), irudi bat edo beste hartuko du
         ldpi: desde 100 hasta 150
         mdpi: desde 150 hasta 200
         hdpi: desde 200 hasta 250
         xhdpi: desde 250 hasta 300
         ************************************************************************************/
        //Toast.makeText(context, "Density: "+densityDpi, Toast.LENGTH_LONG).show();

        String irudiMota = "";
        if (densityDpi >= 100 && densityDpi < 150)
        {
            irudiMota = "ldpi";
        }
        else if (densityDpi >= 150 && densityDpi < 200)
        {
            irudiMota = "mdpi";
        }
        else if (densityDpi >= 200 && densityDpi < 250)
        {
            irudiMota = "hdpi";
        }
        else if (densityDpi >= 250 )
        {
            irudiMota = "xhdpi";
        }
        //Toast.makeText(context, "Irudi mota: "+ irudiMota, Toast.LENGTH_LONG).show();
        return irudiMota;
    }



    public static void hideLinearToolbarLayout (LinearLayout toolbarLinearLayout)
    {
        toolbarLinearLayout.setVisibility(View.GONE);
    }

    public static List<Address> getLocationData (Context context, double latitude, double longitude)
    {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());

        try {
            return geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        /*address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
        String city = addresses.get(0).getLocality();
        String state = addresses.get(0).getAdminArea();
        String country = addresses.get(0).getCountryName();
        String postalCode = addresses.get(0).getPostalCode();
        String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
        return address;*/
    }

    public static String getAdminArea(Context context, double lat, double lng)
    {
        List<Address> address = getLocationData(context, lat, lng);
        return address.get(0).getAdminArea();
    }

    public static String setFormatInTimeHourMin (int time_unit)
    {
        if (time_unit < 10)
        {
            return  "0" + time_unit;
        }
        return String.valueOf(time_unit);
    }

    public static String textFromResources(Context context, int string_text)
    {
        return context.getResources().getString(string_text);
    }

    /*public static void sendTrackViewToGoogleAnalytics(String text)
    {
        try
        {
            GoogleAnalyticsApplication.getInstance().trackScreenView(text);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }*/

    public static void showSnackBarMessage (View v, String message, Context context, int duration)
    {

        /*****************************************************************
         * DURATION:
         *
         * -2: LENGTH_INDEFINITE
         * -1: LENGTH_SHORT
         * 0:  LENGTH_LONG
         *
         ******************************************************************/

        Snackbar.make(v, message, duration)
                .setAction("ITXI", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                })
                .setActionTextColor(context.getResources().getColor(R.color.White))
                .show();
    }
    //Load dinamically drawable after pass drawable name
    public static int getDrawable(Context context, String name)
    {
        Assert.assertNotNull(context);
        Assert.assertNotNull(name);

        return context.getResources().getIdentifier(name,
                "drawable", context.getPackageName());
    }


    public static int getAltitudeColor(Context context, String name)
    {
        if (name.equals("altuera4")) return context.getResources().getColor(R.color.altuera4);
        else if (name.equals("altuera3")) return context.getResources().getColor(R.color.altuera3);
        else if (name.equals("altuera2")) return context.getResources().getColor(R.color.altuera2);
        return context.getResources().getColor(R.color.altuera1);
    }

    public static int getMarkerResourceId(int altitude)
    {
        if (altitude < 1000)
        {
            return R.drawable.mountain_green;
        }
        else if (altitude>= 1000 && altitude <1400) {
            return R.drawable.mountain_blue;
        }
        return R.drawable.mountain_red;
    }


    public static int getUseZoomInMap(float max_distance)
    {
        max_distance = max_distance / 1000;

        System.out.println("Max distance in kms: " + max_distance);
        if (max_distance < 34) {
            return 10;
        } else if (max_distance > 24 && max_distance < 48) {
            return 9;
        } else if (max_distance > 48 && max_distance < 128) {
            return 8;
        } else if (max_distance > 128 && max_distance < 400) {
            return 7;
        } else if (max_distance > 400 && max_distance < 600) {
            return 6;
        } else if (max_distance > 600 && max_distance < 1300) {
            return 5;
        } else if (max_distance > 1300 && max_distance < 2500) {
            return 4;
        } else if (max_distance > 2500 && max_distance < 4000) {
            return 3;
        }
        return 2;
    }


    public static void openStreetViewLocation(Context context, String lat, String lng)
    {
        Intent streetView = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("google.streetview:cbll="+lat +","+ lng + "&cbp=1,90,,0,1.0&mz=6"));
        context.startActivity(streetView);
    }

    public static void openGoogleMapsWithSelectLocation (Context context, String lat, String lng)
    {
        String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr="+lat+","+
                lng);
        System.out.println("Google maps helbidea: " + uri);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        context.startActivity(intent);
        ((AppCompatActivity)context).overridePendingTransition(0, 0);
    }

    public static float getDistance(double our_lat, double our_lng, double destination_lat, double destination_lng)
    {
        Location location_our_point = new Location("our_location");
        location_our_point.setLatitude(our_lat);
        location_our_point.setLongitude(our_lng);

        Location location_destination_point = new Location("destination_location");
        location_destination_point.setLatitude(destination_lat);
        location_destination_point.setLongitude(destination_lng);

        return location_our_point.distanceTo(location_destination_point);
    }


    public static int getRandomNumber(int limit)
    {
        Random rand = new Random();
        return rand.nextInt(limit);
    }

    public static RecyclerView getRecyclerViewConfig(RecyclerView recyclerView, Context context, boolean nested_scrollview)
    {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setLayoutManager(new GridLayoutManager(context, 1));
        if (!nested_scrollview)
        {
            recyclerView.setNestedScrollingEnabled(false);
        }


        recyclerView.addItemDecoration(
                new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return recyclerView;
    }

    public static ProgressDialog startProgressDialog(Activity activity)
    {
        ProgressDialog progressBarDialog =new ProgressDialog(activity);
        progressBarDialog.setMessage("Datuak kargatzen...");
        progressBarDialog.setCancelable(false);
        progressBarDialog.show();
        return progressBarDialog;
    }

    public static int getProgress(int current_position, int total_items)
    {
        return (int) ((current_position / (float) total_items) * 100);
    }

    public static void showProgress(ProgressDialog progressDialog, int progress)
    {
        progressDialog.setProgress(progress);
        if(progress == 100)
        {
            progressDialog.dismiss();
            progressDialog.cancel();
        }
    }

    public static void showBaseActivity(Context context)
    {
        ActivityManager am = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);

        int sizeStack =  am.getRunningTasks(2).size();

        System.out.println(sizeStack + "Size stack");
        for(int i = 0;i < sizeStack;i++){

            ComponentName cn = am.getRunningTasks(2).get(i).topActivity;
            System.out.println(cn.getClassName());
        }
    }

    public static String quitNullValueInString (JSONObject json, String label)
    {
        String testua;
        if (json.isNull(label))
        {
            testua = "";
        }
        else
        {
            try
            {
                testua = json.getString(label);
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                return "";
            }
        }
        return testua;
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            java.net.URL url = new java.net.URL(src);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getTwoDecimal(double value)
    {
        return new DecimalFormat("#.00").format(value);
    }


    public static String checkIfNull(String value)
    {
        if (value != null) return value;
        return "";
    }

}