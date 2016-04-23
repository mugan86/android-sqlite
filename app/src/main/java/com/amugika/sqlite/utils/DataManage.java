package com.amugika.sqlite.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


import com.amugika.sqlite.Constants;

import java.util.Locale;

/********************************************
 * Created by Anartz Mugika on 30/09/2014.
 */
public class DataManage {

    public static SharedPreferences getPreferencesFile(Context context) {
        // This sample app persists the registration ID in shared preferences, but
        // how you store the regID in your app is up to you.

        return PreferenceManager.getDefaultSharedPreferences(context);
    }
    public static String getPreference(Context context, String propertyName){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(propertyName, "");
    }
    public static void setPreference(Context context, String [] propertyNames
            , String [] propertyValues){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        for (int i = 0; i< propertyNames.length; i ++)
        {
            editor.putString(propertyNames [i], propertyValues [i]);
        }

        editor.commit();
    }



    public static boolean isUpdate(Context context)
    {
        String user_data_json = getPreference(context, Constants.USER_SESSION_DATA);

        return !user_data_json.equals("");
    }





    public static String getLocaleLanguage (Context context)
    {
        String langPref = "Language";
        SharedPreferences prefs = context.getSharedPreferences("LanguageDefaultPreferences", Activity.MODE_PRIVATE);
        return prefs.getString(langPref, "");
    }
    public static void loadLocale(Context context)
    {
        String langPref = "Language";
        SharedPreferences prefs = context.getSharedPreferences("LanguageDefaultPreferences", Activity.MODE_PRIVATE);
        String language = prefs.getString(langPref, "");
        changeLang(language, context);
    }

    public static void changeLang(String lang, Context context)
    {
        if (lang.equalsIgnoreCase(""))
            return;
        Locale myLocale = new Locale(lang);
        saveLocale(lang, context);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }

    private static void saveLocale(String lang, Context context)
    {
        String langPref = "Language";
        SharedPreferences prefs = context.getSharedPreferences("LanguageDefaultPreferences", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(langPref, lang);
        editor.commit();
    }

    public static void saveUserLocationData (double latitude, double longitude, Context mContext)
    {
        String [] propertyNames  = {Constants.CURRENT_USER_LOCATION_LAT, Constants.CURRENT_USER_LOCATION_LON};
        String [] propertyValues = {String.valueOf(latitude), String.valueOf(longitude)};
        DataManage.setPreference(mContext, propertyNames, propertyValues);
    }
}