package com.example.ashwini.notificationtest;


import android.net.TrafficStats;

import android.os.Process;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 *
 * Created by kedar on 4/16/17.
 */
public class Network {
    private static final String TAG="network:GET";

    public static void sendGET(String url, FileWriter fw) {

        try{
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", "Android");

            fw.write("TX:"+TrafficStats.getUidTxBytes(Process.myUid()));
            fw.write(",");



            //getting network data

            int responseCode = con.getResponseCode();

            //Log.d(TAG+" to URL : ", url);
           // Log.d(TAG+" Code : ", Integer.toString(responseCode));

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

           // Log.d(TAG,"RX:"+TrafficStats.getUidRxBytes(Process.myUid()));

            fw.write("RX:"+TrafficStats.getUidRxBytes(Process.myUid()));
            fw.write("\n");
            //print result
            //Log.d(TAG, response.toString());

        }
        catch(Exception e){
            Log.d(TAG, e.toString());
        }

    }
}
