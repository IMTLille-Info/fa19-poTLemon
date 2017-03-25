package potlemon.server.app.tools;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.util.*;

/**
 * Created by Pierre on 25/03/2017.
 */
public class Logger {


    private static final List<LoggerObserver> listeners = new ArrayList<>();

    public static void log(String className, String msg) {
        GregorianCalendar date = new GregorianCalendar();
        String datelog = date.get(Calendar.HOUR) + ":" + date.get(Calendar.MINUTE) + ":" + date.get(Calendar.SECOND);

        String log = datelog + " [" + className + "] " + msg;
        System.out.println(log);

        // notify
        notifyListeners(log);
    }

    private static void notifyListeners(String log) {
        for (LoggerObserver listener :
                listeners) {
            listener.onLog(log);
        }
    }


    public static void addListener(LoggerObserver listener) {
        listeners.add(listener);
    }

    public static void removeListener(LoggerObserver listener) {
        listeners.remove(listener);
    }

}
