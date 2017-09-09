package in.fieldassist.eventreminder;

import java.util.Calendar;

/**
 * Created by Reweyou on 9/8/2017.
 */

public interface PresenterInterface {
    void validateFields(Calendar c, int id, String message, String date, String time, String strHourMin, String strDate);
    void onDestroy();
}
