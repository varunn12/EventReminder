package in.fieldassist.eventreminder;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import in.fieldassist.eventreminder.databinding.ActivityFullScreenBinding;

public class FullScreen extends AppCompatActivity implements FullInterface {
SharedPreference sharedPreference;
private String message, title, alarmtime,alarmdate;
private int id;
    Ringtone defaultRingtone;
FullScreenPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFullScreenBinding fullbinding= DataBindingUtil.setContentView(this,R.layout.activity_full_screen);

        Uri defaultRintoneUri = RingtoneManager.getActualDefaultRingtoneUri(getApplicationContext(), RingtoneManager.TYPE_RINGTONE);
        defaultRingtone = RingtoneManager.getRingtone(this, defaultRintoneUri);
        defaultRingtone.play();

        Intent intent=getIntent();
        message=intent.getStringExtra("message");
        id=intent.getIntExtra("id",0);
        alarmtime=intent.getStringExtra("alarmtime");
        alarmdate=intent.getStringExtra("alarmdate");
        sharedPreference=new SharedPreference();

        presenter=new FullScreenPresenter(this,this);

        fullbinding.fullMessage.setText(message);
        fullbinding.fullscreenTime.setText(alarmtime);

        presenter.deleteReminder(id);
    }

    public void close(View view){
        defaultRingtone.stop();
        finish();
    }
}
