package com.example.ashwini.notificationtest;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Environment;
import android.os.PowerManager;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class NetworkIntentService extends IntentService {

    private static final String TAG = "NW Service Statistic";
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "mobilecomputing.networkapp.action.FOO";
    private static final String ACTION_BAZ = "mobilecomputing.networkapp.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "mobilecomputing.networkapp.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "mobilecomputing.networkapp.extra.PARAM2";

    public NetworkIntentService() {
        super("NetworkIntentService");
    }

    private  FileWriter fw;

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, NetworkIntentService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, NetworkIntentService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {


        try {

            File path = Environment.getExternalStoragePublicDirectory
                    (
                            Environment.DIRECTORY_DOWNLOADS + "/log/"

                    );

            File file = new File(path, "network.txt");

            file.createNewFile();

            Log.d(TAG,"File created!"+path);
            fw = new FileWriter(file);

            fw.write("DOZE DEEP MODE ON/OFF");
            fw.write(",");
            fw.write("BYTES SENT");
            fw.write(",");
            fw.write("BYTES RECEIVED");
            fw.write("\n");

        }catch(IOException e){
                e.printStackTrace();
             e.fillInStackTrace();
            }

        try {

            for (int i = 0; i <= 10000; ) {

                Thread.sleep(50);


                //getting device state

                PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
                //Log.d(TAG, "State:" + pm.isDeviceIdleMode());
               // Log.d(TAG, "Power Saver:" + pm.isPowerSaveMode());
                fw.write("m:"+pm.isDeviceIdleMode());
                fw.write(",");

                Calendar c = Calendar.getInstance();
                System.out.println("Current time =&gt; "+c.getTime());

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDate = df.format(c.getTime());
                fw.write("t:"+formattedDate);
                fw.write(",");

                //getting network data


                Network.sendGET("http://kgundlup.pythonanywhere.com/api/json",fw);


                Log.d(TAG, Integer.toString(i));
                Log.d(TAG, "Sent Get Request");
            }
            fw.close();
        }catch(Exception ex) {
                ex.printStackTrace();
            }

    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
