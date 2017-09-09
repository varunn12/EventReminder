package in.fieldassist.eventreminder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class ReminderListActivity extends AppCompatActivity implements Update{
    List<AlarmClass> alarms;
    AlarmAdapter alarmAdapter;
    RecyclerView recyclerView;
    ReminderListPresenter reminderListPresenter;
    SharedPreference sharedPreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        recyclerView=findViewById(R.id.list_product);

        reminderListPresenter =new ReminderListPresenter(this,this);
        sharedPreference=new SharedPreference();
        alarmAdapter = new AlarmAdapter(this,R.layout.alarm_list_item, sharedPreference.getReminders(this));
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(alarmAdapter);
    }


    @Override
    public void refresh(int position) {
        alarmAdapter.deleteItem(position);
    }
}
