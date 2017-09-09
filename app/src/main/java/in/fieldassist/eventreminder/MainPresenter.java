package in.fieldassist.eventreminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by Reweyou on 9/8/2017.
 */

public class MainPresenter implements PresenterInterface {
    private ViewInterface mainView;
    AppCompatActivity activity;
    SharedPreference sharedPreference;

    public MainPresenter(AppCompatActivity activity,ViewInterface mainView){
        this.activity=activity;
        this.mainView=mainView;
    }
    @Override
    public void validateFields(Calendar c, int id, String message, String date, String time, String strHourMin, String strDate) {

        if(TextUtils.isEmpty(message)) {
            mainView.setMesssageError("Message cannot be empty");
        }
        else if(TextUtils.isEmpty(date)) {
            mainView.setDateError("Date cannot be empty");
        }
        else if(TextUtils.isEmpty(time)) {
           mainView.setTimeError("Time cannot be empty");
        }
        else
        {
             remind(c,id,message,strHourMin,strDate);
        }
        if(mainView!=null){}

    }

    public void remind(Calendar calendar, int id,  String message, String strHourMin, String strDate) {

        Intent alarmintent = new Intent(activity, AlarmReceiver.class);
        alarmintent.putExtra("message", message);
        alarmintent.putExtra("alarmtime", strHourMin);
        alarmintent.putExtra("alarmdate", strDate);
        alarmintent.putExtra("id", id);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(activity, id, alarmintent, PendingIntent.FLAG_ONE_SHOT);

        AlarmManager alarmManager = (AlarmManager) activity.getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        sharedPreference=new SharedPreference();
        sharedPreference.addReminders(activity, new AlarmClass(id, message, strHourMin, strDate, calendar));

        String m="Your reminder has been set"+ String.valueOf(calendar.getTime());
        mainView.showMessage(m);
    }


    @Override public void onDestroy() { mainView = null;
    }

}
