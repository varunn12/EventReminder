package in.fieldassist.eventreminder;

import java.util.Calendar;

/**
 * Created by Reweyou on 9/8/2017.
 */

public interface ViewInterface {
    void setDateError(String error);
    void setTimeError(String error);
    void setMesssageError(String error);
    void showMessage(String message);

}
