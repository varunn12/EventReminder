package in.fieldassist.eventreminder;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by Reweyou on 9/8/2017.
 */

public class ReminderListPresenter {
    private  Update update;
    private AppCompatActivity activity;

    public ReminderListPresenter(AppCompatActivity activity, Update update) {
        this.update = update;
        this.activity = activity;
    }
}
