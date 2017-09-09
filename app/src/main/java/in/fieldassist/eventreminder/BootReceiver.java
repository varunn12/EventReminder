package in.fieldassist.eventreminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Reweyou on 9/7/2017.
 */

public class BootReceiver extends BroadcastReceiver {
    SharedPreference sharedPreference;
    List<AlarmClass> alarm;
    String message, title, alarmtime,alarmdate;
    Calendar calendar;
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_BOOT_COMPLETED)) {
                Log.e("Error","On Boot receiver started");
            // Setup alarm
            sharedPreference=new SharedPreference();
            alarm=sharedPreference.getReminders(context);
            for(int i=0;i<alarm.size();i++){
                int id=alarm.get(i).getId();
                message=alarm.get(i).getMessage();
           //     alarmdate=alarm.get(i).getAlarmDate();
             //   alarmtime=alarm.get(i).getAlarmTime();
                calendar=alarm.get(i).getCalendar();

                AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                Intent alarmintent = new Intent(context,
                        AlarmReceiver.class);
                alarmintent.putExtra("message",message);
                alarmintent.putExtra("title",title);
                alarmintent.putExtra("alarmtime",alarmtime);
                alarmintent.putExtra("alarmdate",alarmdate);
                alarmintent.putExtra("id",id);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        context,id , alarmintent, PendingIntent.FLAG_ONE_SHOT);
                Log.e("Calendar", String.valueOf(calendar.getTime()));
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),pendingIntent);
            }
        }
    }
}
