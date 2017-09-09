package in.fieldassist.eventreminder;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Reweyou on 9/4/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {
    private static final int DAILY_REMINDER_REQUEST_CODE =0;
    private  String message,title, alarmtime,alarmdate;
    private int id;
    SharedPreference sharedPreference;
    @Override
    public void onReceive(Context context, Intent intent) {

        message=intent.getStringExtra("message");
        id=intent.getIntExtra("id",0);
        alarmtime=intent.getStringExtra("alarmtime");
        alarmdate=intent.getStringExtra("alarmdate");

        // showNotification(context,SetReminderActivity.class,title,message);

        sharedPreference = new SharedPreference();


        Intent in=new Intent(context,FullScreen.class);
        in.putExtra("message",message);
        in.putExtra("id",id);
        in.putExtra("alarmtime",alarmtime);
        in.putExtra("alarmdate",alarmdate);
        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(in);
    }




    public static void showNotification(Context context,Class<?> cls,String title,String content)
    {
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent notificationIntent = new Intent(context, cls);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(cls);
        stackBuilder.addNextIntent(notificationIntent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(
                DAILY_REMINDER_REQUEST_CODE,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        Notification notification = builder.setContentTitle(title)
                .setContentText(content).setAutoCancel(true)
                .setSound(alarmSound).setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentIntent(pendingIntent).build();

        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(DAILY_REMINDER_REQUEST_CODE, notification);
    }
}
