package in.fieldassist.eventreminder;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by Reweyou on 9/8/2017.
 */

public class FullScreenPresenter {
    FullInterface full;
    AppCompatActivity activity;
    public FullScreenPresenter(AppCompatActivity activity,FullInterface full)
    {
        this.full=full;
        this.activity=activity;
    }

    public void deleteReminder(int id) {
        SharedPreference sharedPreference = new SharedPreference();
        sharedPreference.removeReminders(activity, id);
    }
}
