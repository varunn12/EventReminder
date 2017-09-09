package in.fieldassist.eventreminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

/**
 * Created by Reweyou on 9/8/2017.
 */

public class Presenter {
    private Context context;
    ArrayList<AlarmClass> arrayList;
    private int position;

    public Presenter(Context context, int position){
        this.context=context;
        this.position=position;
    }
    public void onRemove( AlarmClass alarmClass){

            SharedPreference sharedPreference=new SharedPreference();
            sharedPreference.removeReminders(context,alarmClass.getId());

            arrayList=sharedPreference.getReminders(context);
             ReminderListActivity activity = (ReminderListActivity) context;
             activity.refresh(position);

            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Intent alarmintent = new Intent(context,
                    AlarmReceiver.class);
//                alarmintent.putExtra("message",product.getMessage());
//                alarmintent.putExtra("alarmtime",product.getAlarmTime());
//                alarmintent.putExtra("alarmdate",product.getAlarmDate());
//                alarmintent.putExtra("id",product.getId());
            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    context, alarmClass.getId(), alarmintent, PendingIntent.FLAG_ONE_SHOT);

            alarmManager.cancel(pendingIntent);


    }
}
