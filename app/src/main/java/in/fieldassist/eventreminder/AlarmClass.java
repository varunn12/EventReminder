package in.fieldassist.eventreminder;

import android.databinding.Bindable;
import android.databinding.BaseObservable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Reweyou on 9/6/2017.
 */

public class AlarmClass extends BaseObservable {
    private int id;
    private String message;
    private String times;
    private String dates;
    private Calendar calendar;


    public AlarmClass(int id, String message,String times,String dates, Calendar calendar) {
        super();
        this.id = id;
        this.message = message;
        this.times=times;
        this.dates=dates;
        this.calendar=calendar;
    }
@Bindable
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
        notifyPropertyChanged(BR.id);
    }
    @Bindable
    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message=message;
        notifyPropertyChanged(BR.message);
    }
@Bindable
public String getTimes(){
        return times;
    }

    public void setTimes(String times){
        this.times=times;
        notifyPropertyChanged(BR.times);
    }
@Bindable public String getDates(){
        return dates;
    }

    public void setDates(String dates){
        this.dates=dates;
        notifyPropertyChanged(BR.dates);
    }
@Bindable
    public Calendar getCalendar(){return calendar;}

    public void setCalendar(Calendar calendar){this.calendar=calendar;
        notifyPropertyChanged(BR.calendar);}
}