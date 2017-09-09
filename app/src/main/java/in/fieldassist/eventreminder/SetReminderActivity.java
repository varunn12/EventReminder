package in.fieldassist.eventreminder;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import in.fieldassist.eventreminder.databinding.ActivityMainBinding;

public class SetReminderActivity extends BaseActivity implements ViewInterface {

    private int mYear, mMonth, mDay, mHour, mMin;
    ActivityMainBinding binding;
    private Calendar c, mycalendar;
    SharedPreference sharedPreference;
    MainPresenter presenter;
    private String strHrsToShow, strDate, strHourMin;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.setAlarm.setText("Set Reminder");

        c = Calendar.getInstance();
        sharedPreference = new SharedPreference();

        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMin = c.get(Calendar.MINUTE);
        mycalendar = Calendar.getInstance();
        mycalendar.setTimeInMillis(System.currentTimeMillis());
        id = (int) System.currentTimeMillis();
        presenter=new MainPresenter(this,this);
    }


    public void showList(View view){
        Intent in = new Intent(SetReminderActivity.this, ReminderListActivity.class);
        startActivity(in);
    }


    public void selectDate(View view){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
                        mycalendar.set(Calendar.YEAR, year);
                        mycalendar.set((Calendar.MONTH), monthOfYear);
                        mycalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        strDate = format.format(mycalendar.getTime());
                        binding.inDate.setText(strDate);
                        Log.e("Date which is set", String.valueOf(mycalendar.getTime()));
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void selectTime(View view) {
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, AlertDialog.THEME_HOLO_DARK,
                    new TimePickerDialog.OnTimeSetListener() {
                        String am_pm = "";
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            if (mycalendar.get(Calendar.AM_PM) == Calendar.AM)
                                am_pm = "AM";
                            else if (mycalendar.get(Calendar.AM_PM) == Calendar.PM)
                                am_pm = "PM";
                            mycalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                            mycalendar.set(Calendar.MINUTE, minute);
                            mycalendar.set(Calendar.SECOND, 00);


                            strHrsToShow = String.valueOf(hourOfDay);
                            strHourMin = strHrsToShow + ":" + mycalendar.get(Calendar.MINUTE) + " " + am_pm;

                            binding.inTime.setText(strHourMin);
                            Log.e("My Calendar value", String.valueOf(mycalendar));
                        }
                    }, mHour, mMin, false);
            timePickerDialog.show();
        }


    public void setYourAlarm(View view){
                presenter.validateFields(mycalendar, id, binding.editMessage.getText().toString(),
                binding.inDate.getText().toString(),binding.inTime.getText().toString(),strHourMin,strDate);
        }


    @Override
    public void setDateError(String error) {
     binding.inDate.setError(error);
    }

    @Override
    public void setTimeError(String error) {
    binding.inTime.setError(error);
    }

    @Override
    public void setMesssageError(String error) {
     binding.editMessage.setError(error);
    }

    @Override
    public void showMessage(String message){
        Toast.makeText(SetReminderActivity.this, message, Toast.LENGTH_LONG).show();
    }
}
