package com.example.ashwini.notificationtest;


import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by kedar on 4/16/17.
 */
public class Network {
    private static final String TAG="network:GET";

    public static void sendGET(String url) {
        //String url = "http://www.google.com/search?q=mkyong";
        try{
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", "Android");

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            Log.d(TAG, response.toString());

        }
        catch(Exception e){
            Log.d(TAG, e.toString());
        }

    }
}
