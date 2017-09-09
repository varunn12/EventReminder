package in.fieldassist.eventreminder;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Reweyou on 9/6/2017.
 */

public class AlarmListAdapter extends ArrayAdapter<AlarmClass> {

    private Context context;
    List<AlarmClass> alarm;
    SharedPreference sharedPreference;

    public AlarmListAdapter(@NonNull Context context, List<AlarmClass> alarm) {
        super(context, R.layout.alarm_list_item, alarm);
        this.context=context;
        this.alarm=alarm;
        sharedPreference=new SharedPreference();
    }



    private class ViewHolder {
        TextView txtID;
        TextView txtMessage;
        TextView txtAlarmTime;
        TextView txtAlarmDate;
        TextView rem_reminder;
        ImageView favoriteImg;
    }

    @Override
    public int getCount() {
        return alarm.size();
    }

    @Override
    public AlarmClass getItem(int position) {
        return alarm.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.alarm_list_item, null);
            holder = new ViewHolder();
            holder.txtID = (TextView) convertView
                    .findViewById(R.id.txt_id);
            holder.txtMessage = (TextView) convertView
                    .findViewById(R.id.txt_message);
            holder.txtAlarmTime = (TextView) convertView
                    .findViewById(R.id.txt_alarm_time);
            holder.txtAlarmDate = (TextView) convertView
                    .findViewById(R.id.txt_alarm_date);
            holder.favoriteImg = (ImageView) convertView
                    .findViewById(R.id.imgbtn_favorite);
            holder.rem_reminder=(TextView)convertView.
                    findViewById(R.id.remove);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final AlarmClass product = (AlarmClass) getItem(position);
        holder.txtID.setText(String.valueOf(product.getId()));
        holder.txtMessage.setText(product.getMessage());
        //holder.txtAlarmTime.setText(product.getAlarmTime() + "");
       // holder.txtAlarmDate.setText(product.getAlarmDate());

        holder.rem_reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreference sharedPreference=new SharedPreference();
                sharedPreference.removeReminders(context,product.getId());
                AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                Intent alarmintent = new Intent(context,
                        AlarmReceiver.class);
//                alarmintent.putExtra("message",product.getMessage());
//                alarmintent.putExtra("title","Test");
//                alarmintent.putExtra("alarmtime",product.getAlarmTime());
//                alarmintent.putExtra("alarmdate",product.getAlarmDate());
//                alarmintent.putExtra("id",product.getId());
                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        context, product.getId(), alarmintent, PendingIntent.FLAG_ONE_SHOT);

                alarmManager.cancel(pendingIntent);
            }
        });


        return convertView;
    }

    /*Checks whether a particular product exists in SharedPreferences*/
    public boolean checkFavoriteItem(AlarmClass checkProduct) {
        boolean check = false;
        List<AlarmClass> favorites = sharedPreference.getReminders(context);
        if (favorites != null) {
            for (AlarmClass product : favorites) {
                if (product.equals(checkProduct)) {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }

    @Override
    public void add(AlarmClass alarms) {
        super.add(alarms);
        alarm.add(alarms);
        notifyDataSetChanged();
    }

    @Override
    public void remove(AlarmClass alarms) {
        super.remove(alarms);
        alarm.remove(alarms);
        notifyDataSetChanged();
    }

    public void refreshEvents(List<AlarmClass> alarm) {
        this.alarm.clear();
        this.alarm.addAll(alarm);
        notifyDataSetChanged();
    }
}
