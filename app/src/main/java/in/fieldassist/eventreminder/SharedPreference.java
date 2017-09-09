package in.fieldassist.eventreminder;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Reweyou on 9/6/2017.
 */

public class SharedPreference {
    public static final String PREFS_NAME = "REMINDER_APP";
    public static final String FAVORITES = "Product_Favorite";

    public SharedPreference() {
        super();
    }

    public void addReminders(Context context, AlarmClass alarm) {
        List<AlarmClass> setalarms = getReminders(context);
        if (setalarms == null) setalarms = new ArrayList<AlarmClass>();
        setalarms.add(alarm);
        saveReminders(context, setalarms);
    }

    public void saveReminders(Context context, List<AlarmClass> alarm) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonAlarm = gson.toJson(alarm);

        editor.putString(FAVORITES, jsonAlarm);

        editor.commit();

    }

    public ArrayList<AlarmClass> getReminders(Context context){
       SharedPreferences settings;
       List<AlarmClass> setalarms;
        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
       if(settings.contains(FAVORITES)){
           String jsonFavorites = settings.getString(FAVORITES, null);
           Gson gson = new Gson();
           AlarmClass[] alarmItems = gson.fromJson(jsonFavorites,
                   AlarmClass[].class);

           setalarms = Arrays.asList(alarmItems);
           setalarms = new ArrayList<AlarmClass>(setalarms);
       } else
           return null;

       return (ArrayList<AlarmClass>) setalarms;
    }

    public void removeReminders(Context context, int id) {
        List<AlarmClass> alarmlist = getReminders(context);

        if (alarmlist != null) {
            for(int i=0; i<alarmlist.size(); i++){
                if(alarmlist.get(i).getId()==id)
                {
                    alarmlist.remove(i);
                }
            }
            saveReminders(context, alarmlist);

        }
    }

}
